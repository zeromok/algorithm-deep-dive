package daniel.algorithmdeepdive.pattern.search.twopointer.samewindow;

/// ## 가변 크기 슬라이딩 윈도우
/// ### 핵심 원리
/// - `right`는 계속 확장
/// - 조건 위반 시 `left` 이동하며 축소
/// - 윈도우 크기가 동적으로 변함
/// ### 시간 복잡도
/// - O(N): 각 원소는 최대 2번 방문
/// ### 전형적인 문제
/// - 합이 `target` 이상인 최소 길이
/// - 중복 없는 최장 부분배열
public class VariableWindow {


	/// 합이 `target` 이상인 최소 길이
	/// @param arr 정수 배열
	/// @param target 목표 합
	/// @return 최소 길이, 없으면 0
	public static int minLength(int[] arr, int target) {
		int left = 0;
		int sum = 0;
		int minLen = Integer.MAX_VALUE;

		for (int right = 0; right < arr.length; right++) {
			sum += arr[right];

			// 조건 만족하면 left 이동하며 최소화
			while (sum >= target) {
				minLen = Math.min(minLen, right - left + 1);
				sum -= arr[left];
				left++;
			}
		}
		return minLen == Integer.MAX_VALUE ? 0 : minLen;
	}

	/// 중복 없는 최장 부분배열 길이
	/// @param arr 정수 배열
	/// @return 최대 길이
	public static int maxLengthUnique(int[] arr) {
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

	/// 최대 부분합 (카데인 알고리즘)
	/// @param arr 정수 배열
	/// @return 최대 부분합
	public static int maxSubarraySum(int[] arr) {
		int maxSum = arr[0];
		int currentSum = arr[0];

		for (int i = 1; i < arr.length; i++) {
			currentSum = Math.max(arr[i], currentSum + arr[i]);
			maxSum = Math.max(maxSum, currentSum);
		}

		return maxSum;
	}
}
