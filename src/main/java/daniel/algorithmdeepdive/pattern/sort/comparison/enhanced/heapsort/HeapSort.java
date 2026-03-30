package daniel.algorithmdeepdive.pattern.sort.comparison.enhanced.heapsort;

import java.util.Arrays;

public class HeapSort {

	public static void main(String[] args) {
		int[] arr = {60, 20, 70, 10, 80, 30, 50, 40};
		heapSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	// 1. 성능 (Time Complexity)
	// - heapify() 성능: O(log n) (트리의 높이만큼 내려감)
	// - 전체 시간 복잡도: 항상 O(n log n) (최악, 최선, 평균 동일)
	// - 퀵 정렬보다 느릴 수 있지만, 최악의 경우에도 성능이 보장됨

	// 2. 알고리즘 특성
	// - 완전 이진 트리 기반: 배열을 트리처럼 해석하여 정렬
	// - 제자리 정렬(In-place Sort): 추가적인 배열 생성 없이 원본 배열 내에서 정렬
	// - 불안정 정렬(Unstable Sort): 힙 구성 과정에서 동일한 값의 순서가 뒤바뀔 수 있음
	// - 데이터의 상태와 무관하게 일정한 성능을 내는 것이 장점
	private static void heapSort(int[] arr) {
		int n = arr.length;

		// 1. 초기 힙 구성 (Build Max Heap)
		// 자식이 있는 노드(n/2 - 1)부터 루트(0)까지 heapify를 수행합니다.
		for (int i = n / 2 - 1; i >= 0; i--) {
			heapify(arr, n, i);
		}

		// 2. 힙에서 하나씩 추출하여 정렬
		for (int i = n - 1; i > 0; i--) {
			// 현재 루트(최대값)를 배열의 끝으로 보냅니다.
			swap(arr, 0, i);

			// 나머지 요소들을 다시 최대 힙 구조로 만듭니다. (크기를 줄여가며)
			heapify(arr, i, 0);
		}
	}

	// 힙 구조를 유지하는 핵심 메서드
	private static void heapify(int[] arr, int n, int i) {
		int largest = i;     // 부모 노드
		int left = 2 * i + 1;  // 왼쪽 자식
		int right = 2 * i + 2; // 오른쪽 자식

		// 왼쪽 자식이 부모보다 크다면 largest 갱신
		if (left < n && arr[left] > arr[largest]) {
			largest = left;
		}

		// 오른쪽 자식이 현재 largest 보다 크다면 갱신
		if (right < n && arr[right] > arr[largest]) {
			largest = right;
		}

		// largest 가 부모 노드가 아니라면 교환 발생
		if (largest != i) {
			swap(arr, i, largest);

			// 교환된 자식 노드에 대해 다시 heapify 수행 (재귀)
			heapify(arr, n, largest);
		}
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
