package daniel.algorithmdeepdive.pattern.search.twopointer.opposite.practice;

/// ## TwoSum 실행 흐름 관찰
/// ### 목표
/// - left/right 포인터가 어떻게 움직이는가?
/// - 왜 정렬이 필요한가?
/// - 중복 값이 있으면 어떻게 되는가?
public class TwoSumPractice {

	public static void main(String[] args) {
		basicFlow();
		duplicates();
	}

	/// 정렬된 배열에서 정상 동작
	static void basicFlow() {
		int[] arr = {1, 3, 5, 7, 9, 11};
		int target = 14;

		System.out.printf("배열: ");
		printArray(arr);
		System.out.printf("목표: %d\n\n", target);

		int left = 0;
		int right = arr.length - 1;
		int step = 0;

		while (left < right) {
			step++;
			int sum = arr[left] + arr[right];
			System.out.printf("[%d단계] ", step);

			if (sum == target) {
				break;
			} else if (sum < target) {
				left++;
			} else {
				right--;
			}
		}
		System.out.println();
	}

	static void duplicates() {
		int[] arr = {1, 2, 2, 3, 4, 4, 5};
		int target = 6; // 2+4=6 여러 조합 존재
		System.out.printf("배열: ");
		printArray(arr);
		System.out.printf("목표: %d\n\n", target);

		int left = 0;
		int right = arr.length - 1;
		int found = 0;

		while (left < right) {
			int sum = arr[left] + arr[right];

			if (sum == target) {
				found++;
				left++;
				right--;
			} else if (sum < target) {
				left++;
			} else {
				right--;
			}
		}
		System.out.printf("총 %d개 조합 발견\n\n", found);
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
