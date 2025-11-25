package daniel.algorithmdeepdive.sort.pattern.mergesort;

import java.util.Arrays;

public class MergeSortMain {
	public static void main(String[] args) {
		Integer[] num = {5, 4, 3, 2, 1, 0, 9, 6, 7, 8};


		// long startV0 = System.nanoTime();
		// MergeSortV0.mergeSort(num);
		// long endV0 = System.nanoTime();
		// System.out.println(Arrays.toString(num) + " " +(endV0 - startV0) + " ms");

		// long startV1 = System.nanoTime();
		// MergeSortV1.mergeSort(num);
		// long endV1 = System.nanoTime();
		// System.out.println(Arrays.toString(num) + " " +(endV1 - startV1) + " ms");

		// long startV2 = System.nanoTime();
		// MergeSortV2.sort(num);
		// long endV2 = System.nanoTime();
		// System.out.println(Arrays.toString(num) + " " +(endV2 - startV2) + " ms");

		long startV3 = System.nanoTime();
		MergeSortV3.sort(num);
		long endV3 = System.nanoTime();
		System.out.println(Arrays.toString(num) + " " +(endV3 - startV3) + " ms");
	}
}
