package daniel.algorithmdeepdive.pattern.search.twopointer.samewindow;

/// ## 같은 방향 투포인터 (슬라이딩 윈도우)
/// ### 핵심 원리
/// - `left`, `right` 모두 왼쪽에서 시작
/// - `right`: 확장
/// - `left`: 축소
/// ### 두 가지 변형
/// 1. 고정 크기: 윈도우 크기 k 고정
/// 2. 가변 크기: 조건에 따라 동적 변화
/// ### 시간 복잡도: O(N)
public class SameWindow {

	/// 크기 k 부분배열의 최대 합 (고정 크기)
	public static int fixedMaxSum(int[] arr, int k) {
		if (arr.length < k) {
			return -1;
		}

		// 첫 윈도우 합
		int windowSum = 0;
		for (int i = 0; i < k; i++) {
			windowSum += arr[i];
		}

		int maxSum = windowSum;

		for (int right = k; right < arr.length; right++) {
			int left = right - k;
			windowSum = windowSum - arr[left] + arr[right];
			maxSum = Math.max(maxSum, windowSum);
		}
		return maxSum;
	}

	/// 합이 target 이상인 최소 길이 (가변 크기)
	public static int variableMinLength(int[] arr, int target) {
		int left = 0;
		int sum = 0;
		int minLen = Integer.MAX_VALUE;

		for (int right = 0; right < arr.length; right++) {
			sum += arr[right];

			while (sum >= target) {
				minLen = Math.min(minLen, right - left + 1);
				sum -= arr[left];
				left++;
			}
		}
		return minLen == Integer.MAX_VALUE ? 0 : minLen;
	}

	/// 중복 없는 최장 부분배열 (가변 크기)
	public static int variableMaxLengthUnique(int[] arr) {
		boolean[] seen = new boolean[100_001];
		int left = 0;
		int maxLen = 0;

		for (int right = 0; right < arr.length; right++) {
			// 중복 발생 시 left 이동
			while (seen[arr[right]]) {
				seen[arr[left]] = false;
				left++;
			}

			seen[arr[right]] = true;
			maxLen = Math.max(maxLen, right - left + 1);
		}
		return maxLen;
	}
}
