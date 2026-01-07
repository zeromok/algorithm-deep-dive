package daniel.algorithmdeepdive.pattern.search.twopointer.opposite.practice;

import java.util.Arrays;

/// ## ThreeSum 실행 흐름 관찰
/// ### 관찰 목표
/// - threeSum 이 twoSum 의 반복임을 이해
/// - 중복 제거 로직의 필요성 이해
public class ThreeSumPractice {

	public static void main(String[] args) {
		basicFlow();
		duplicateHandling();
	}

	/// 기본 흐름
	static void basicFlow() {
		int[] arr = {-4, -1, 0, 1, 2, 3};
		Arrays.sort(arr);

		System.out.printf("배열: ");
		printArray(arr);
		System.out.println("목표: 합이 0이 되는 요소 쌍의 조합 찾기\n");

		int count = 0;
		for (int i = 0; i < arr.length - 2; i++) {
			int target = -arr[i];

			int left = i + 1;
			int right = arr.length - 1;
			while (left < right) {
				int sum = arr[left] + right;

				if (sum == target) {
					count++;
					left++;
					right--;
				} else if (sum < right) {
					left++;
				} else {
					right--;
				}
			}
		}
		System.out.printf("\n총 %d개 조합\n\n", count);
	}

	/// 실험 2: 중복 제거 필요성
	static void duplicateHandling() {
		System.out.println("=== 실험 2: 중복 제거 ===");
		int[] arr = {-1, -1, 0, 1, 1, 2};
		Arrays.sort(arr);

		System.out.printf("배열: ");
		printArray(arr);
		System.out.println("목표: 합이 0 (중복 값 존재)\n");

		System.out.println("[중복 제거 없이]");
		countWithoutSkip(arr);

		System.out.println("\n[중복 제거 있음]");
		countWithSkip(arr);
	}

	static void countWithoutSkip(int[] arr) {
		int count = 0;
		for (int i = 0; i < arr.length - 2; i++) {
			int left = i + 1;
			int right = arr.length - 1;
			int target = -arr[i];

			while (left < right) {
				int sum = arr[left] + arr[right];
				if (sum == target) {
					count++;
					System.out.printf("  [%d, %d, %d]\n",
						arr[i], arr[left], arr[right]);
					left++;
					right--;
				} else if (sum < target) {
					left++;
				} else {
					right--;
				}
			}
		}
		System.out.printf("총 %d개 (중복 포함)\n", count);
	}

	static void countWithSkip(int[] arr) {
		int count = 0;
		for (int i = 0; i < arr.length - 2; i++) {
			// 첫 번째 수 중복 제거
			if (i > 0 && arr[i] == arr[i - 1]) {
				continue;
			}

			int left = i + 1;
			int right = arr.length - 1;
			int target = -arr[i];

			while (left < right) {
				int sum = arr[left] + arr[right];
				if (sum == target) {
					count++;
					System.out.printf("  [%d, %d, %d]\n",
						arr[i], arr[left], arr[right]);

					// 나머지 두 수 중복 제거
					while (left < right && arr[left] == arr[left + 1]) left++;
					while (left < right && arr[right] == arr[right - 1]) right--;

					left++;
					right--;
				} else if (sum < target) {
					left++;
				} else {
					right--;
				}
			}
		}
		System.out.printf("총 %d개 (고유한 조합만)\n", count);
	}

	static void printArray(int[] arr) {
		System.out.print("[");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
			if (i < arr.length - 1) System.out.print(", ");
		}
		System.out.print("]\n");
	}
}
