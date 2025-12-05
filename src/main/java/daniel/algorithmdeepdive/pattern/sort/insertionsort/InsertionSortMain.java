package daniel.algorithmdeepdive.pattern.sort.insertionsort;

import java.util.Arrays;
import java.util.Comparator;

public class InsertionSortMain {

	public static void main(String[] args) {
		Integer[] num = {5, 4, 3, 2, 1, 0, 9, 6, 7, 8};

		// System.out.println("V0 정렬 전: " + Arrays.toString(num));
		// InsertionSortV0 insertionSortV0 = new InsertionSortV0();
		// insertionSortV0.insertionSort(num);
		// System.out.println("V0 정렬 후: " + Arrays.toString(num));


		// System.out.println("V1 정렬 전: " + Arrays.toString(num));
		// InsertionSortV1 insertionSortV1 = new InsertionSortV1();
		// insertionSortV1.binaryInsertionSort(num);
		// System.out.println("V1 정렬 후: " + Arrays.toString(num));

		// System.out.println("V2 정렬 전: " + Arrays.toString(num));
		// InsertionSortV2.sort(num);
		// System.out.println("V2 정렬 후: " + Arrays.toString(num));

		System.out.println("V3 정렬 전: " + Arrays.toString(num));
		InsertionSortV3.sort(num, Comparator.comparingInt(o -> o), false);
		System.out.println("V3 정렬 후: " + Arrays.toString(num));
	}
}
