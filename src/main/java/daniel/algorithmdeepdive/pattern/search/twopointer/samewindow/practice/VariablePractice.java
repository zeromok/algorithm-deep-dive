package daniel.algorithmdeepdive.pattern.search.twopointer.samewindow.practice;

/// ## 가변 윈도우 실행 흐름 관찰
/// ### 목표
/// - 윈도우가 언제 확장되고 언제 축소되는가?
/// - left 가 움직이는 조건 이해
public class VariablePractice {

	public static void main(String[] args) {
		growAndShrink();
		uniqueElements();
	}

	/// 합이 target 이상인 가장 짧은 부분 배열의 길이를 반환한다.
	private static void growAndShrink() {
		System.out.println("=== 윈도우 확장/축소 ===");
		int[] arr = {2, 1, 5, 2, 3, 2};
		int target = 7;

		System.out.printf("배열: ");
		printArray(arr);
		System.out.printf("목표 합: %d 이상\n\n", target);

		int left = 0;
		int currentSum = 0;
		int minLen = Integer.MAX_VALUE;

		for (int right = 0; right < arr.length; right++) {
			// 윈도우 확장
			currentSum += arr[right];

			// 조건 만족 시 윈도우 축소 (최소 길이 갱신)
			while (currentSum >= target) {
				minLen = Math.min(minLen, right - left + 1);

				// 윈도우 왼쪽 끝 값을 빼고 포인터 이동
				currentSum -= arr[left];
				left++;
			}
		}
		System.out.printf("최소 길이: %d\n\n",
			minLen == Integer.MAX_VALUE ? 0 : minLen);
	}

	/// 중복 없는 최장 부분배열의 길이
	static void uniqueElements() {
		System.out.println("=== 중복 없는 최장 부분배열 ===");
		int[] arr = {1, 2, 3, 2, 1, 4, 5};

		System.out.printf("배열: ");
		printArray(arr);
		System.out.println("\n");

		// 특정 숫자가 현재 윈도우에 포함되어 있는지 확인하는 "체크 리스트" (배열 인덱스 활용)
		// 범위가 더 넓거나 음수가 포함되는 경우 Set 을 사용해야 한다.
		boolean[] seen = new boolean[100_001];
		int left = 0;
		int maxLen = 0;
		for (int right = 0; right < arr.length; right++) {
			int value = arr[right];

			// 중복 처리
			if (seen[value]) {
				while (seen[value]) {
					seen[arr[left]] = false;
					left++;
				}
			}

			// 윈도우 갱신
			seen[value] = true;
			int len = right - left + 1;
			maxLen = Math.max(len, maxLen);
		}
		System.out.printf("최대 길이: %d\n\n", maxLen);
	}

	static void printArray(int[] arr) {
		System.out.print("[");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
			if (i < arr.length - 1) System.out.print(", ");
		}
		System.out.print("]");
	}
}
