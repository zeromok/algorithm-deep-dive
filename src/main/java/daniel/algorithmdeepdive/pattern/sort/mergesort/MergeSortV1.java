package daniel.algorithmdeepdive.pattern.sort.mergesort;


/// # 병합 정렬 V1 - 성능 최적화
///
/// V0 대비 개선 사항:
/// - 전역 임시 배열 재사용 (메모리 할당 최소화)
/// - 불필요한 주석 제거
/// - 인덱스 검증 로직 추가
///
/// 시간 복잡도: O(N log N)
/// 공간 복잡도: O(N)
public class MergeSortV1 {

	private static int[] temp; // 재사용 임시 배열

	public static void mergeSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int[] temp = new int[arr.length];
		recursive(arr, 0, arr.length - 1);
		temp = null;
	}

	private static void recursive(int[] arr, int left, int right) {
		// 종료 조건
		if (left > right) {
			// 분할
			int mid = (left + right) / 2;

			// 재귀
			recursive(arr, left, mid);
			recursive(arr, mid + 1, right);

			// 병합
			merge(arr, left, mid, right);
		}
	}

	private static void merge(int[] arr, int left, int mid, int right) {
		// 임시 배열에 복사 (왜? 병합 과정에서 원본 데이터를 덮어쓴다.)
		// temp 에서 읽고 arr에 쓴다.
		System.arraycopy(arr, left, temp, left, right - left + 1);

		// i: 왼쪽 배열의 요소를 가리키는 포인터
		// j: 오른쪽 배열의 요소를 가리키는 포인터
		// k: 병합 결과가 쓰여질 위치 포인터
		int i = left, j = mid + 1, k = left;

		// 병합: 이미 정렬된 두개의 배열을 병합
		// 두 배열의 요소들을 비교하면서 arr 에 복사한다.
		while (i <= mid && j <= right) {
			if (temp[i] <= temp[j]) {
				arr[k++] = temp[i++];
			} else {
				arr[k++] = temp[j++];
			}
		}

		// 남은 원소 처리
		// 왜 왼쪽 기준?: 오른쪽 배열(j~right)이 남았다는건 -> 이미 arr 배열 안 올바른 위치에 있다는 뜻
		while (i <= mid) {
			arr[k++] = temp[i++];
		}
	}
}
