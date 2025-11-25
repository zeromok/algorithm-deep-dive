package daniel.algorithmdeepdive.sort.pattern.mergesort;


/// # 병합 정렬 V0 - 기본 구현
/// 목적: 병합 정렬의 핵심 원리를 단계별로 이해
///
/// 핵심 개념:
/// - 분할 정복(Divide & Conquer) 알고리즘
/// - 안정 정렬(Stable Sort) 보장
/// - 재귀적 구조
///
/// 알고리즘 흐름:
/// 1. 분할(Divide): 배열을 절반으로 나눔
/// 2. 정복(Conquer): 각 부분을 재귀적으로 정렬
/// 3. 병합(Merge): 정렬된 두 부분을 하나로 합침
///
/// 시간 복잡도: O(N log N) - 최선/평균/최악 모두 통일
///
/// 공간 복잡도: O(N) - 임시 배열 필요
///
/// 장점:
/// - 안정 정렬 (같은 값의 순서 유지)
/// - 예측 가능한 성능 (입력 상태 무관)
/// - 대용량 데이터에 적합
///
/// 단점:
/// - 추가 메모리 필요 (임시 배열)
/// - 작은 데이터에는 오버헤드
///
/// 실무 응용:
/// - 외부 정렬 (메모리에 안 들어오는 데이터)
/// - 안정성이 중료한 정렬 (주문 내역, 로그 등)
/// - Java 의 Collections.sort() 내부 구현 (TimeSort 의 기반)
public class MergeSortV0 {

	/// 진입점
	public static void mergeSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		recursive(arr, 0, arr.length - 1);
	}

	/// 재귀적 병합 정렬
	///
	/// 동작 원리:
	/// 1. 배열을 절반으로 분할 (mid 기준)
	/// 2. 왼쪽 절반 재귀 정렬
	/// 3. 오른쪽 절반 재귀 정렬
	/// 4. 정렬된 두 부분 병합
	/// @param arr 정렬할 배열
	/// @param left 정렬할 범위의 시작 인덱스
	/// @param right 정렬할 범위의 끝 인덱스
	private static void recursive(int[] arr, int left, int right) {
		// 종료 조건: 정렬할 범위에 원소가 2개 이상 있을 때만 실행
		if (left < right) {
			// 분할: 배열을 둘로 나눌 중간 인덱스 계산
			int mid = (left + right) / 2;

			// 재귀 호출: 왼쪽/오른쪽을 재귀적으로 정렬
			recursive(arr, left, mid);
			recursive(arr, mid + 1, right);

			// 병합: 정렬된 두 부분을 하나로 합침
			merge(arr, left, mid, right);
		}
	}

	/// 정렬된 두 부분 배열을 병합
	///
	/// 전제 조건:
	/// - `arr[left ~ mid]`: 이미 정렬됨
	/// - `arr[mid + 1 ~ right]`: 이미 정렬됨
	///
	/// 목표:
	/// - `arr[left ~ right]`: 전체 정렬
	/// @param arr 정렬할 배열
	/// @param left 병합할 왼쪽 범위 시작 인덱스
	/// @param mid 왼쪽 범위 끝 인덱스
	/// @param right 병합할 오른쪽 범위 끝 인덱스
	private static void merge(int[] arr, int left, int mid, int right) {
		// 1. 임시 배열 크기 계산
		int n1 = mid - left + 1; // 왼쪽 범위: arr[left] ~ arr[mid], 원소 개수: mid - left + 1 왜? 인덱스는 0 부터 시작
		int n2 = right - mid; // 오른쪽 범위: arr[mid + 1] ~ arr[right], 원소 개수: right - (mid + 1) + 1

		// 2. 임시 배열 생성
		// 왜 임시 배열이 필요한가? 원본 배열에서 직접 비교 시 덮어쓰기 문제 발생
		int[] L = new int[n1];
		int[] R = new int[n2];

		// 3. 데이터 복사 왼쪽/오른쪽
		for (int i = 0; i < n1; i++) {
			L[i] = arr[left + i];
		}
		for (int i = 0; i < n2; i++) {
			R[i] = arr[mid + 1 + i];
		}

		// 4. 병합
		// i: L 배열의 현재 위치
		// j: R 배열의 현재 위치
		int i = 0, j = 0;
		// k: 배열에 결과를 넣을 위치
		// left 부터 시작하는 이유: [left ~ right] 구간만 정렬한다. 다른 부분은 신경쓸 필요 없다.
		int k = left;
		while (i < n1 && j < n2) { // L과 R 둘 다 남은 원소가 있을 때만 실행

			// L의 현재 원소 <= R의 현재 원소
			// 안정 정렬 보장: 크기가 같으면 왼쪽(먼저 입력된) 것을 선택
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				i++;
			} else {
				arr[k] = R[j];
				j++;
			}
			k++; // 배열의 다음 위치로 이동 -> 왼쪽부터 순서대로 채워감
		}

		// 5. 남은 원소 처리
		// R의 모든 원소를 다 썼을 때(j >= n2) -> L의 남은 원소들을 복사
		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}
		// L의 모든 원소를 다 썼을 때(i >= n1) -> R의 남은 원소들을 복사
		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;
		}
		// 왜 그대로 복사해도 될까?
		// 남은 원소들을 이미 정렬된 상태, L/R 의 모든 원소보다 크다. -> 순서대로 뒤에 붙임
	}
}
