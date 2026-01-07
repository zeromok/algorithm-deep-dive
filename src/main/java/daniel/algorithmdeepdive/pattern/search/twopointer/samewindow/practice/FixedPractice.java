package daniel.algorithmdeepdive.pattern.search.twopointer.samewindow.practice;

/// ## 고정 크기 윈도우 실행 흐름 관찰
/// ### 목표
/// - 윈도우가 어떻게 슬라이딩 하는가?
/// - 매번 합을 다시 계산하지 않는 이유는?
public class FixedPractice {

	public static void main(String[] args) {
		slidingProcess();
		compareWithBruteforce();
	}

	static void slidingProcess() {
		System.out.println("=== 윈도우 슬라이딩 ===");
		int[] arr = {1, 3, 2, 5, 1, 3, 4};
		int k = 3;

		System.out.printf("배열: ");
		printArray(arr);
		System.out.printf("윈도우 크기: %d\n\n", k);

		// 첫 윈도우 (윈도우 안 요소의 합)
		int windowSum = 0;
		for (int i = 0; i < k; i++) {
			windowSum += arr[i];
		}

		int maxSum = windowSum;

		// 슬라이딩
		for (int right = k; right < arr.length; right++) {
			int left = right - k + 1; // 슬라이딩 후 윈도우의 첫 요소
			int oldLeft = left - 1; // 윈도우의 첫 요소 (슬라이딩 되면서 제거)

			windowSum = windowSum - arr[oldLeft] + arr[right];

			maxSum = Math.max(maxSum, windowSum);
		}
		System.out.printf("\n최대 합: %d\n\n", maxSum);
	}

	static void compareWithBruteforce() {
		System.out.println("=== 완전탐색 vs 슬라이딩 ===");
		int[] arr = {1, 3, 2, 5, 1, 3, 4};
		int k = 3;

		System.out.printf("배열: ");
		printArray(arr);
		System.out.printf("윈도우 크기: %d\n\n", k);

		// 완전탐색
		System.out.println("완전탐색: ");
		int operation1 = 0;
		int maxSum1 = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length - k; i++) {
			int sum = 0;
			for (int j = i; j < i + k; j++) {
				sum += arr[j];
				operation1++;
			}
			maxSum1 = Math.max(maxSum1, sum);
		}
		System.out.printf("연산 횟수: %d\n", operation1);
		System.out.printf("최대 합: %d\n\n", maxSum1);

		// 슬라이딩
		System.out.println("슬라이딩: ");
		int operation2 = 0;
		int maxSum2 = Integer.MIN_VALUE;
		int windowSum = 0;
		for (int i = 0; i < k; i++) {
			windowSum += arr[i];
		}

		for (int right = k; right < arr.length; right++) {
			int removeIdx = right - k;
			windowSum = windowSum - arr[removeIdx] + arr[right];
			operation2 += 2; // 빼기 1번, 더하기 1번
			maxSum2 = Math.max(maxSum2, windowSum);
		}
		System.out.printf("연산 횟수: %d\n", operation2);
		System.out.printf("최대 합: %d\n\n", maxSum2);

		System.out.printf("효율 개선: %d → %d 연산 (%.1f배)\n",
			operation1, operation2, (double) operation1 / operation2);
	}

	static void printArray(int[] arr) {
		System.out.print("[");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
			if (i < arr.length - 1) System.out.print(", ");
		}
		System.out.print("]\n");
	}

	static void printWindow(int[] arr, int left, int right) {
		System.out.print("[");
		for (int i = left; i <= right; i++) {
			System.out.print(arr[i]);
			if (i < right) System.out.print(", ");
		}
		System.out.print("]");
	}
}
