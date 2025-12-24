package daniel.algorithmdeepdive.pattern.search.binary.practice;

import daniel.algorithmdeepdive.pattern.search.binary.LowerBound;
import daniel.algorithmdeepdive.pattern.search.binary.UpperBound;

import java.util.Arrays;

/**
 * LowerBound vs UpperBound 비교 실험
 *
 * <h3>실험 목적</h3>
 * - LowerBound와 UpperBound의 차이 이해
 * - "arr[mid] >= target" vs "arr[mid] > target" 차이 관찰
 * - 중복 원소가 있을 때 어떻게 다른지 확인
 */
public class LowerBoundPractice {

	public static void main(String[] args) {
		int[] arr = {1, 3, 3, 3, 5, 7, 9};

		System.out.println("배열: " + Arrays.toString(arr));
		System.out.println();

		// 실험 1: 중복 값
		compareWithUpperBound(arr, 3);

		// 실험 2: 없는 값
		compareWithUpperBound(arr, 4);

		// 실험 3: 범위 밖
		compareWithUpperBound(arr, 10);
	}

	private static void compareWithUpperBound(int[] arr, int target) {
		System.out.println("=== target: " + target + " ===");

		int lower = traceLowerBound(arr, target);
		System.out.println();

		int upper = traceUpperBound(arr, target);
		System.out.println();

		// 결과 비교
		System.out.println("[결과 비교]");
		System.out.println("LowerBound: " + lower +
				(lower < arr.length ? " (arr[" + lower + "]=" + arr[lower] + ")" : " (범위 밖)"));
		System.out.println("UpperBound: " + upper +
				(upper < arr.length ? " (arr[" + upper + "]=" + arr[upper] + ")" : " (범위 밖)"));

		if (lower < arr.length && arr[lower] == target) {
			System.out.println("개수: " + (upper - lower));
		} else {
			System.out.println("target이 배열에 없음");
		}
		System.out.println();
	}

	private static int traceLowerBound(int[] arr, int target) {
		int left = 0, right = arr.length;
		int step = 0;

		System.out.println("--- LowerBound (arr[mid] >= target) ---");

		while (left < right) {
			int mid = left + (right - left) / 2;

			System.out.printf("Step %d: left=%d, mid=%d, right=%d, arr[mid]=%d\n",
					++step, left, mid, right, arr[mid]);

			if (arr[mid] >= target) {
				System.out.println("→ arr[mid] >= target, right=mid");
				right = mid;
			} else {
				System.out.println("→ arr[mid] < target, left=mid+1");
				left = mid + 1;
			}
		}
		System.out.println("→ 종료 (left == right)");
		return left;
	}

	private static int traceUpperBound(int[] arr, int target) {
		int left = 0, right = arr.length;
		int step = 0;

		System.out.println("--- UpperBound (arr[mid] > target) ---");

		while (left < right) {
			int mid = left + (right - left) / 2;

			System.out.printf("Step %d: left=%d, mid=%d, right=%d, arr[mid]=%d\n",
					++step, left, mid, right, arr[mid]);

			if (arr[mid] > target) {
				System.out.println("→ arr[mid] > target, right=mid");
				right = mid;
			} else {
				System.out.println("→ arr[mid] <= target, left=mid+1");
				left = mid + 1;
			}
		}
		System.out.println("→ 종료 (left == right)");
		return left;
	}
}
