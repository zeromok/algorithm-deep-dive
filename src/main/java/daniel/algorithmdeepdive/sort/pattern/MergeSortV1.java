package daniel.algorithmdeepdive.sort.pattern;


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
		// 임시 배열에 복사 (값 복사-깊은 복사, 객체 복사-얕은 복사)
		System.arraycopy(arr, left, temp, left, right - left + 1);

		int i = left;
		int j = mid + 1;
		int k = left;

		// 병합
		while (i <= mid && j <= right) {
			if (temp[i] <= temp[j]) {
				arr[k++] = temp[i++];
			} else {
				arr[k++] = temp[j++];
			}
		}

		// 남은 원소 처리
		while (i <= mid) {
			arr[k++] = temp[i++];
		}
	}
}
