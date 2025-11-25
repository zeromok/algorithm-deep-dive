package daniel.algorithmdeepdive.sort.pattern.insertionsort;

/// # 삽입정렬 V1 - 이진 삽입 정렬 (Binary Insertion Sort)
///
/// ## 최적화 전략
/// 정렬된 영역에서 삽입 위치를 찾을 때 선형 탐색(O(N)) 대신 이진 탐색(O(log N)) 을 사용하여 비교 횟수를 줄임
///
/// ## V0 와의 차이점
/// V0: 선형 탐색으로 삽입 위치 찾기 -> O(N) 비교
/// V1: 이진 탐색으로 삽입 위치 찾기 -> O(log N) 비교
/// 단, 원소 이동은 여전히 O(N) (배열의 특성상 불가피)
///
/// ## 시간 복잡도
/// - 비교 연산: O(N log N)
/// - 이동 연산: O(N^2)
/// - 전체: O(N^2) (이동이 지배적)
///
/// ## 언제 사용하는가?
/// - 비교 연산이 매우 비싼 경우 (복잡한 객체, 긴 문자열)
/// - 이동 연산은 빠르지만 비교가 느린 경우
/// - Ex. 커스텀 `compareTo()` 가 복잡한 도메인 객체
public class InsertionSortV1 {

	/// 이진 삽입 정렬
	///
	/// @param arr 정렬할 정수 배열
	public void binaryInsertionSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int key = arr[i];

			// 핵심: 이진 탐색으로 삽입 위치를 O(log n)에 찾기
			// 정렬된 영역 arr[0..i-1]에서 key가 들어갈 위치 탐색
			int insertPos = binarySearch(arr, 0, i - 1, key);

			// System.arraycopy: 네이티브 메서드로 빠른 배열 복사
			// arr[insertPos..i-1]을 arr[insertPos+1..i]로 이동
			// 파라미터: (원본, 시작위치, 목적지, 목적시작, 길이)
			System.arraycopy(arr, insertPos, arr, insertPos + 1, i - insertPos);

			// 찾은 위치에 key 삽입
			arr[insertPos] = key;
		}
	}

	/// ### 이진 탐색으로 삽입 위치 찾기
	///
	/// #### 목표
	/// 정렬된 배열 `arr[left..right]`에서 `key`가 삽입될 위치를 찾기
	///
	/// #### 반환값
	/// `key`보다 큰 첫 번째 원소의 인덱스 (즉, `key`가 삽입되어야 할 위치)
	///
	/// @param arr 정렬된 배열
	/// @param left 탐색 시작 인덱스
	/// @param right 탐색 끝 인덱스
	/// @param key 삽입할 값
	/// @return 삽입 위치 인덱스
	private int binarySearch(int[] arr, int left, int right, int key) {
		// 표준 이진 탐색 변형
		// "key 보다 큰 첫 번째 원소"를 찾는 low_bound 구현
		while (left <= right) {
			int mid = left + (right - left) / 2; // 오버플로우 방지: (left + right) / 2 대신 사용

			if (arr[mid] > key) {
				right = mid - 1; // key가 더 작으면 왼쪽 절반 탐색
			} else {
				// arr[mid] <= key면 오른쪽 절반 탐색
				// 같은 값이 있어도 오른쪽으로 이동 (안정성 보장)
				left = mid + 1;
			}
		}

		// 루프 종료 시 left가 삽입 위치
		return left;
	}
}
