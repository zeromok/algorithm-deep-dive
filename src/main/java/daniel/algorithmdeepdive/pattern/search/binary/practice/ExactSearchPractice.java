package daniel.algorithmdeepdive.pattern.search.binary.practice;

import java.util.Arrays;

/**
 * ExactSearch 실험장
 *
 * <h3>실험 목적</h3>
 * - 이분탐색의 실행 흐름 관찰
 * - left, mid, right가 어떻게 움직이는지 추적
 * - "못 찾는 경우"는 언제 종료되는지 이해
 */
public class ExactSearchPractice {

	public static void main(String[] args) {
		int[] arr = {1, 3, 5, 7, 9, 11, 13};

		System.out.println("배열: " + Arrays.toString(arr));
		System.out.println();

		// 실험 1: 있는 값
		trace(arr, 7);

		// 실험 2: 없는 값 (중간)
		trace(arr, 6);

		// 실험 3: 없는 값 (범위 밖)
		trace(arr, 15);
	}

	private static void trace(int[] arr, int target) {
		int left = 0, right = arr.length - 1;
		int step = 0;

		System.out.println("=== target: " + target + " ===");

		while (left <= right) {
			int mid = left + (right - left) / 2;

			System.out.printf("Step %d: left=%d, mid=%d, right=%d, arr[mid]=%d\n",
					++step, left, mid, right, arr[mid]);

			if (arr[mid] == target) {
				System.out.println("→ 찾음! index=" + mid);
				System.out.println();
				return;
			} else if (arr[mid] < target) {
				System.out.println("→ arr[mid] < target, 오른쪽 탐색 (left = mid+1)");
				left = mid + 1;
			} else {
				System.out.println("→ arr[mid] > target, 왼쪽 탐색 (right = mid-1)");
				right = mid - 1;
			}
		}
		System.out.println("→ 못 찾음 (left > right)");
		System.out.println();
	}
}
