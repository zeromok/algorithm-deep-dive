package daniel.algorithmdeepdive.pattern.sort.selectionsort;

import java.util.Arrays;

public class SelectionSort {

	public static void main(String[] args) {
		int[] arr = {30, 20, 40, 35, 5, 10, 45, 50, 25, 15};
		selectionSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	// 배열의 크기가 작을 경우 효과적이다.
	// 시간복잡도: O(n^2)
	// 요소의 순서에 민감하지 않다.
	// 제자리 정렬 알고리즘이다.
	// 불안정 정렬이다.
	private static void selectionSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) { // n-1번 반복
			int min = i; // 미정렬 배열의 첫번째 원소를 최소값으로 지정
			for (int j = i + 1; j < arr.length; j++) { // i~n-1 번째 중 최소값 찾기
				if (arr[min] > arr[j]) {
					min = j;
				}
			}
			swap(arr, i, min); // 미정렬 배열의 첫 번째 원소와 최솟값 위치 교환
		}
	}

	private static void swap(int[] arr, int i, int min) {
		int temp = arr[i];
		arr[i] = arr[min];
		arr[min] = temp;
	}
}
