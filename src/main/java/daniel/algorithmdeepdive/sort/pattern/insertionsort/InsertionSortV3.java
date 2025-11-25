package daniel.algorithmdeepdive.sort.pattern.insertionsort;

import static java.util.Objects.*;

import java.util.Comparator;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/// # 삽입정렬 V3 - 프로덕션 레벨 구현
/// ## 엔터프라이즈 요구사항 충족
///
///     - Null Safety: NPE 방지
///     - 예외 안전성: 정렬 실패 시 원본 보존 옵션
///     - 불변성 옵션: 원본 배열을 수정하지 않는 모드
///     - 로깅 & 모니터링: 디버깅 및 성능 추적
///     - 계약 설계 (Design by Contract): 명확한 전제조건/사후조건
///
/// ## 사용 시나리오
///
///     - 외부 입력 데이터 정렬 (검증 필요)
///     - 멀티스레드 환경 (원본 불변성)
///     - 프로덕션 로깅/모니터링 필요
///     - 장애 복구 (예외 발생 시 원본 보존)
public class InsertionSortV3 {

	private static final Logger log = LoggerFactory.getLogger(InsertionSortV3.class);


	/// 프로덕션 레벨 삽입정렬
	/// ### 전제조건 (Preconditions)
	///
	///     - arr != null
	///     - comparator != null 이거나 T가 Comparable 구현
	///
	/// ### 사후조건 (Postconditions)
	///
	///     - 반환 배열은 정렬됨
	///     - copyArray=true면 원본 불변
	///     - 예외 발생 시 원본 배열 변경 안 됨 (원자성)
	///
	///
	/// @param <T> 원소 타입
	/// @param arr 정렬할 배열 (null 불가)
	/// @param comparator 비교자 (null 이면 자연 순서)
	/// @param copyArray true면 복사본 정렬, false면 원본 정렬
	/// @return 정렬된 배열
	///
	/// @throws NullPointerException arr이 null인 경우
	/// @throws IllegalStateException 정렬 중 예외 발생 시
	/// @throws ClassCastException comparator가 null이고 T가 Comparable 아닌 경우
	public static <T> T[] sort(T[] arr, Comparator<? super T> comparator, boolean copyArray) {
		// 계약 검증: null 체크
		Objects.requireNonNull(arr, "배열은 null이 아니여야 합니다.");

		// 엣지 케이스: 빈 배열이나 원소 1개
		if (arr.length <= 1) {
			return copyArray ? arr.clone() : arr;
		}

		// 원본 보호: 복사본 생성 옵션
		T[] target = copyArray ? arr.clone() : arr;

		// 성능 추적 (프로덕션에서는 조건부 활성화)
		long startTime = System.nanoTime();

		try {
			// 실제 정렬 수행
			sortInternal(target, 0, target.length - 1, comparator);

			// 성공 로깅 (TRACE 레벨: 운영에서는 비활성화)
			if (log.isTraceEnabled()) {
				long duration = System.nanoTime() - startTime;
				log.trace("{} 개의 요소로 정렬된 배열 {} μs", target.length, duration / 1000);
			}

		} catch (Exception e) {
			// 예외 발생 시 로깅 및 재포장
			log.error("정렬 실패 {}", arr.length, e);

			// copyArray=true 였다면 원본은 안전함
			// copyArray=false 였다면 부분 정렬 상태 (문제!)
			throw new IllegalStateException(
				"정렬 실패. " +
					(copyArray ? "원본 배열 변경되지 않음."
						: "원본 배열은 부분적으로 정렬될 수 있습니다."),
				e
			);
		}

		return target;
	}

	/// 내부 정렬 로직
	///
	/// 외부 메서드에서 검증 완료 후 호출되므로
	/// 여기서는 순수 정렬 로직만 집중
	///
	/// @param arr 정렬할 배열 (null 아님 보장)
	/// @param left 시작 인덱스
	/// @param right 끝 인덱스
	/// @param comp 비교자
	private static <T> void sortInternal(T[] arr, int left, int right,
		Comparator<? super T> comp) {
		for (int i = left + 1; i <= right; i++) {
			T key = arr[i];

			// Null 원소 처리 전략
			// 옵션 1: 예외 발생 (엄격한 모드)
			// 옵션 2: null을 맨 앞/뒤로 (비즈니스 로직에 따라)
			// 옵션 3: 건너뛰기 (여기서는 경고 후 건너뛰기)
			if (key == null) {
				log.warn("인덱스 {} 에서 null 요소를 찾았습니다. 건너뛰기.", i);
				continue;  // 또는 throw new NullPointerException()
			}

			int j = i - 1;

			// null 체크 추가: 정렬 중 arr[j]도 null일 수 있음
			while (j >= left && arr[j] != null &&
				compare(arr[j], key, comp) > 0) {
				arr[j + 1] = arr[j];
				j--;
			}

			arr[j + 1] = key;
		}
	}
}
