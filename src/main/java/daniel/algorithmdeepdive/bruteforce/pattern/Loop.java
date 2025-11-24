package daniel.algorithmdeepdive.bruteforce.pattern;

/// # 완전탐색 패턴 - 3개 원소 조함
/// ## 핵심 원리:
/// - N 개 중 3개를 선택하는 모든 조합을 탐색
///
/// ## 시간 복잡도: O(N^3)
/// ## 공간 복잡도: O(1)
///
/// ## 적용 조건
/// - N <= 100 (3중 루프 감당 가능)
/// - 모든 조합을 확인해야 함
public class Loop {

	/// 3개 원소 합이 target 이하이면서 최댓값 찾기
	public static int findMaxSumUnder(int[] arr, int target) {
		int maxSum = 0;

		for (int i = 0; i < arr.length - 2; i++) {
			for (int j = i + 1; j < arr.length - 1; j++) {
				for (int k = j + 1; k < arr.length; k++) {
					int sum = arr[i] + arr[j] + arr[k];

					if (sum <= target && sum > maxSum) {
						maxSum = sum;
					}
				}
			}
		}

		return maxSum;
	}
}
