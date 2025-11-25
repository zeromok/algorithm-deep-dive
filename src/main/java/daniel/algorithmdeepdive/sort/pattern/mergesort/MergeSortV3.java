package daniel.algorithmdeepdive.sort.pattern.mergesort;

import java.util.Comparator;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/// # 병합 정렬 V3 - 병렬 처리 버전
///
/// Fork/Join Framework 를 사용한 멀티 스레드 병합 정렬
/// 대용량 데이터(10만 개 이상)에서 효율적
///
/// V2 대비 개선사항:
/// - 병렬 처리로 멀티코어 활용
/// - 임계값 이하는 순차 처리 (오버헤드 방지)
public class MergeSortV3 {

	private static final int PARALLEL_THRESHOLD = 10000;
	private static final int SEQUENTIAL_THRESHOLD = 16;

	public static <T extends Comparable<? super T>> void sort(T[] arr) {
		sort(arr, Comparator.naturalOrder());
	}

	public static <T> void sort(T[] arr, Comparator<? super T> comp) {
		if (arr == null || arr.length < 2) {
			return;
		}

		@SuppressWarnings("unchecked")
		T[] temp = (T[])new Object[arr.length];

		ForkJoinPool pool = ForkJoinPool.commonPool();
		pool.invoke(new MergeSortTask<>(arr, temp, 0, arr.length - 1, comp));
	}

	private static class MergeSortTask<T> extends RecursiveAction {

		private final T[] arr;
		private final T[] temp;
		private final int left;
		private final int right;
		private final Comparator<? super T> comp;

		public MergeSortTask(T[] arr, T[] temp, int left, int right, Comparator<? super T> comp) {
			this.arr = arr;
			this.temp = temp;
			this.left = left;
			this.right = right;
			this.comp = comp;
		}

		@Override
		protected void compute() {
			int size = right - left + 1;

			// 작은 범위는 순차 처리
			if (size < PARALLEL_THRESHOLD) {
				sequentialSort(arr, temp, left, right, comp);
				return;
			}

			// 병렬 처리
			int mid = left + (left + right) / 2;

			MergeSortTask<T> leftTask = new MergeSortTask<>(arr, temp, left, mid, comp);
			MergeSortTask<T> rightTask = new MergeSortTask<>(arr, temp, mid + 1, right, comp);

			invokeAll(leftTask, rightTask);

			merge(arr, temp, left, mid, right, comp);
		}

		private void sequentialSort(T[] arr, T[] temp, int left, int right, Comparator<? super T> comp) {
			if (right - left < SEQUENTIAL_THRESHOLD) {
				insertionSort(arr, left, right, comp);
				return;
			}

			if (left < right) {
				int mid = left + (left + right) / 2;
				sequentialSort(arr, temp, left, mid, comp);
				sequentialSort(arr, temp, mid + 1, right, comp);
				merge(arr, temp, left, mid, right, comp);
			}
		}

		private void merge(T[] arr, T[] temp, int left, int mid, int right, Comparator<? super T> comp) {

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
}
