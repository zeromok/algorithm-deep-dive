package daniel.algorithmdeepdive.pattern.sort.comparison.enhanced.quicksort;

import java.util.Arrays;

public class QuickSort {

	public static void main(String[] args) {
		int[] arr = {30, 50, 17, 40, 88, 15, 44, 55, 22, 11, 66, 13, 85};
		quickSort(arr, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));
	}

	// 1. 성능 (Time Complexity)
	// - partition() 성능: O(n) (피벗 기준 전수 조사)
	// - 평균/최선 시간 복잡도: O(n log n)
	// - 최악 시간 복잡도: O(n^2) (피벗이 한쪽으로 극단적으로 쏠릴 때)
	// - 정렬된 배열에서 첫 번째 원소를 피벗으로 잡으면 최악의 성능을 보임

	// 2. 알고리즘 특성
	// - 분할 정복(Divide and Conquer): 문제를 작게 쪼개어 재귀적으로 해결
	// - 제자리 정렬(In-place Sort): 추가 메모리 공간이 거의 필요 없음
	// - 불안정 정렬(Unstable Sort): 동일한 값들의 상대적 순서가 보장되지 않음
	public static void quickSort(int[] arr, int low, int high) {
		// 1. 종료 조건: 정렬할 범위가 1개 이하일 때
		if (low < high) {
			// 2. 분할을 통해 피벗의 위치를 결정
			int pivot = partition(arr, low, high);

			// 3. 피벗을 기준으로 왼쪽과 오른쪽을 각각 재귀적으로 정렬
			quickSort(arr, low, pivot - 1);  // 왼쪽 부분배열
			quickSort(arr, pivot + 1, high); // 오른쪽 부분배열
		}
	}

	private static int partition(int[] arr, int low, int high) {
		int pivot = arr[low]; // 맨 왼쪽 요소를 피벗으로 설정
		int left = low + 1;
		int right = high;

		while (left <= right) {
			// 피벗보다 큰 값을 찾을 때까지 left 증가
			while (left <= high && arr[left] <= pivot) {
				left++;
			}
			// 피벗보다 작은 값을 찾을 때까지 right 감소
			while (right > low && arr[right] >= pivot) {
				right--;
			}

			// 아직 서로 엇갈리지 않았다면 두 요소의 위치를 바꿈
			if (left < right) {
				swap(arr, left, right);
			}
		}

		// 4. 피벗(low)을 제자리(right)로 옮김
		swap(arr, low, right);

		// 확정된 피벗의 위치를 반환
		return right;
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
