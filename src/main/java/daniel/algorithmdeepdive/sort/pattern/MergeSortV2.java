package daniel.algorithmdeepdive.sort.pattern;

import java.util.Comparator;

/// # 병합 정렬 V2 - 실무
///
/// V1 대비 개선 사항:
/// - 제네릭 지원 (모든 타입 정렬 가능)
/// - Comparator 사용 (정렬 기준 커스터 마이징)
/// - 안정 정렬 보장
/// - TimSort 스타일 최적화 (작은 배열은 삽입 정렬)
public class MergeSortV2 {
	private static final int INSERTION_SORT_THRESHOLD = 16;

	public static <T extends Comparable<? super T>> void sort(T[] arr) {
		sort(arr, Comparator.naturalOrder());
	}

	/// Comparator 를 사용한 배열 정렬
	public static <T> void sort(T[] arr, Comparator<? super T> comp) {
		if (arr == null || arr.length < 2) {
			return;
		}

		@SuppressWarnings("unchecked")
		T[] temp = (T[])new Object[arr.length];

		mergeSort(arr, temp, 0, arr.length - 1, comp);
	}

	private static <T> void mergeSort(T[] arr, T[] temp, int left, int right, Comparator<? super T> comp) {
		// 작은 배열은 삽입 정렬 (최적화)
		if (right - left < INSERTION_SORT_THRESHOLD) {
			insertionSort(arr, left, right, comp);
			return;
		}

		if (left < right) {
			int mid = (left + right) / 2;

			mergeSort(arr, temp, left, mid, comp);
			mergeSort(arr, temp, mid + 1, right, comp);

			// 이미 정렬되어 있으면 병합 생략 (최적화)
			if (comp.compare(arr[mid], arr[mid + 1]) <= 0) {
				return;
			}

			merge(arr, temp, left, mid, right, comp);
		}
	}

	private static <T> void merge(T[] arr, T[] temp, int left, int mid, int right, Comparator<? super T> comp) {
		System.arraycopy(arr, left, temp, left, right - left + 1);

		int i = left;
		int j = mid + 1;
		int k = left;
		while (i <= mid && j <= right) {
			if (comp.compare(temp[i], temp[j]) <= 0) {
				arr[k++] = temp[i++];
			} else {
				arr[k++] = temp[j++];
			}
		}

		while (i <= mid) {
			arr[k++] = temp[i++];
		}
	}

	private static <T> void insertionSort(T[] arr, int left, int right, Comparator<? super T> comp) {

		for (int i = left; i <= right; i++) {
			T key = arr[i];
			int j = i - 1;

			while (j >= left && comp.compare(arr[j], key) > 0) {
				arr[j + 1] = arr[j--];
			}
			arr[j + 1] = key;
		}
	}
}
