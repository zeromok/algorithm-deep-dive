package daniel.algorithmdeepdive.pattern.sort.comparison.bubblesort;

import java.util.Arrays;

public class BubbleSort {

	public static void main(String[] args) {
		int[] arr = {30, 20, 40, 35, 5, 10, 45, 50, 25, 15};
		// bubbleSort(arr);
		advanced_bubbleSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	// 시간복잡도: O(n^2)
	// 안정 정렬이다.
	// 제자리 정렬 알고리즘이다.
	// 개선할 여지가 있다.
	private static void bubbleSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) { // n-1번 반복
			// 왼쪽에서 오른쪽으로 진행 (가장 뒤 큰 값부터)
			for (int j = 0; j < arr.length - 1; j++) {
				if (arr[j] > arr[j + 1]) { // 왼쪽 데이터가 오른쪽 데이터보다 큰 경우
					swap(arr, j, j + 1);
				}
			}
			// 오른쪽에서 왼쪽으로 진행 (가장 앞 작은 값부터)
			// for (int j = arr.length - 1; j > 0; j--) {
			// 	if (arr[j - 1] > arr[j]) {
			// 		swap(arr, j - 1, j);
			// 	}
			// }
		}
	}

	private static void advanced_bubbleSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			boolean sorted = true;
			for (int j = 0; j < (arr.length - 1) - i; j++) {
				if (arr[j] > arr[j + 1]) {
					swap(arr, j, j + 1);
					sorted = false;
				}
			}
			if (sorted) {
				break;
			}
		}
	}

	private static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}
