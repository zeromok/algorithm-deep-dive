package daniel.algorithmdeepdive.pattern.search.twopointer.samewindow;

/// ## 고정 크기 슬라이딩 윈도우
/// ### 핵심 원리
/// - 윈도우 크기는 고정
/// - `right`를 1칸 이동하면 `left`도 1칸 이동
/// - 윈도우가 배열을 따라 "슬라이딩"
/// ### 시간 복잡도
/// - O(N): 각 원소를 한 번씩만 처리
/// ### 전형적인 문제
/// - 크기 k인 부분배열의 최대 합
/// - 연속된 k개 원소의 평균
public class FixedWindow {

	/// 크기 k 부분배열의 최대 합
	/// @param arr 정수 배열
	/// @param k 윈도우 크기
	/// @return 최대 합
	public static int maxSum(int[] arr, int k) {
		if (arr.length < k) {
			return -1;
		}

		// 첫 윈도우 합 계산
		int windowSum = 0;
		for (int i = 0; i < k; i++) {
			windowSum += arr[i];
		}

		int maxSum = windowSum;

		// 원도우 슬라이딩
		for (int right = k; right < arr.length; right++) {
			int left = right - k;
			windowSum = windowSum - arr[left] + arr[right]; // 이전 범위의 첫 요소를 빼고 새 범위를 확장하여 합계 갱신
			maxSum = Math.max(maxSum, windowSum);
		}

		return maxSum;
	}

	/// 크기 k 부분배열의 평균 배열 (이동 평균)
	/// @param arr 정수 배열
	/// @param k 윈도우 크기
	/// @return 각 윈도우의 평균 배열
	public static double[] movingAverage(int[] arr, int k) {
		if (arr.length < k) {
			return new double[0];
		}

		double[] result = new double[arr.length - k + 1];

		// 첫 윈도우 합
		int windowSum = 0;
		for (int i = 0; i < k; i++) {
			windowSum += arr[i];
		}
		result[0] = (double)windowSum / k;

		// 슬라이딩
		for (int right = k; right < arr.length; right++) {
			int left = right - k;
			windowSum = windowSum - arr[left] + arr[right];
			result[right - k + 1] = (double) windowSum / k;
		}

		return result;
	}
}
