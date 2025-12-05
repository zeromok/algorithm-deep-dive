package daniel.algorithmdeepdive.pattern.sort.insertionsort;

import static java.util.Objects.*;

import java.util.Arrays;
import java.util.Comparator;

/// # 삽입정렬 V2 - 실무 최적화 버전
///
/// ## 실무 적용 패턴
/// Java의 `Arrays.sort()`, `TimeSort` 등 실제 프로덕션 알고리즘들은
/// 작은 부분 배열에 대해 삽입정렬을 사용한다.
///
/// ## 주요 특징
/// - 제네릭 지원: 모든 타입의 객체 정렬 가능
/// - Comparator 지원: 유연한 정렬 기준
/// - 부분 배열 정렬: 하이브리드 정렬의 서브루틴으로 사용
/// - 안정성 보장: 같은 값의 순서 유지
public class InsertionSortV2 {

	/// ### 삽입정렬 임계값
	/// #### 경험적 연구 결과:
	/// - 7~16 사이가 최적 (CPU 캐시 라인 고려)
	/// - JDK TimSort: 32 사용
	/// - JDK DualPivotQuicksort: 47 사용
	/// #### 근거:
	/// - 작은 배열: 삽입정렬의 O(n^2)이 실제로는 매우 빠름 (상수가 작음)
	/// - 큰 배열: QuickSort 등의 O(n log n) 이점이 드러남
	/// - 캐시 지역성: 작은 데이터는 L1 캐시에 상주
	private static final int INSERTION_SORT_THRESHOLD = 10;


	/// 제네릭 삽입정렬 - 부분 배열 버전
	///
	/// 이 메서드는 QuickSort, MergeSort 등의 재귀 알고리즘에서
	/// 작은 부분 배열을 만났을 때 호출됨
	///
	/// @param <T> 정렬할 원소의 타입
	/// @param arr 정렬할 배열
	/// @param left 정렬 시작 인덱스 (포함)
	/// @param right 정렬 끝 인덱스 (포함)
	/// @param comparator 비교자 (null 이면 자연 순서)
	///
	/// @throws NullPointerException arr이 null인 경우
	/// @throws ClassCastException comparator가 null 이고 T가 Comparable이 아닌 경우
	public static <T> void sort(T[] arr, int left, int right,
		Comparator<? super T> comparator) {
		// 방어적 검증: 잘못된 범위 조기 반환
		if (left < 0 || right >= arr.length || left >= right) {
			return;
		}

		// left+1부터 시작: arr[left..left]는 이미 정렬됨
		for (int i = left + 1; i <= right; i++) {
			T key = arr[i];
			int j = i - 1;

			// Comparator 또는 Comparable 사용
			// compare() 헬퍼 메서드가 null 처리
			while (j >= left && compare(arr[j], key, comparator) > 0) {
				arr[j + 1] = arr[j];
				j--;
			}

			arr[j + 1] = key;
		}
	}

	/**
	 * 전체 배열 정렬 편의 메서드
	 *
	 * @param <T> 원소 타입
	 * @param arr 정렬할 배열
	 * @param comparator 비교자
	 */
	public static <T> void sort(T[] arr, Comparator<? super T> comparator) {
		if (arr == null || arr.length <= 1) {
			return;
		}
		sort(arr, 0, arr.length - 1, comparator);
	}

	/// 자연 순서로 정렬 (Comparable 사용)
	///
	/// @param <T> Comparable을 구현한 타입
	/// @param arr 정렬할 배열
	public static <T extends Comparable<? super T>> void sort(T[] arr) {
		sort(arr, null);
	}

	/// 비교 헬퍼 메서드
	///
	/// Comparator가 주어지면 사용하고, 없으면 자연 순서(Comparable) 사용
	///
	/// @param <T> 원소 타입
	/// @param a 첫 번째 원소
	/// @param b 두 번째 원소
	/// @param comparator 비교자 (null 가능)
	/// @return a < b면 음수, a == b면 0, a > b면 양수
	@SuppressWarnings("unchecked")
	private static <T> int compare(T a, T b, Comparator<? super T> comparator) {
		if (comparator != null) {
			return comparator.compare(a, b);
		}
		// comparator가 null이면 Comparable로 캐스팅
		// ClassCastException은 호출자에게 전파 (의도된 동작)
		return ((Comparable<? super T>) a).compareTo(b);
	}

	/// 하이브리드 정렬 전략 (실무 패턴)
	///
	/// 작은 배열은 삽입정렬, 큰 배열은 다른 알고리즘 사용
	/// 이것이 JDK Arrays.sort()의 핵심 전략
	///
	/// @param <T> 원소 타입
	/// @param arr 정렬할 배열
	/// @param comparator 비교자
	public static <T> void hybridSort(T[] arr, Comparator<? super T> comparator) {
		if (arr == null || arr.length <= 1) {
			return;
		}

		if (arr.length <= INSERTION_SORT_THRESHOLD) {
			// 작은 배열: 삽입정렬 직접 사용
			sort(arr, 0, arr.length - 1, comparator);
		} else {
			// 큰 배열: 다른 정렬 알고리즘 (QuickSort, MergeSort 등)
			// 여기서는 Arrays.sort() 호출
			// 실제 구현에서는 커스텀 QuickSort/MergeSort가 들어감
			Arrays.sort(arr, comparator);
		}
	}
}
