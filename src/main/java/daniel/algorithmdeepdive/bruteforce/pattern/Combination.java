package daniel.algorithmdeepdive.bruteforce.pattern;

import java.util.ArrayList;
import java.util.List;

/// # 완전탐색 패턴 - 조합 (Combination)
/// ## 핵심 원리
/// - N 개 중 R 개를 선택하는 모든 조합 탐색
/// ## 시간 복잡도:
/// ## 공간 복잡도:
public class Combination {

	public static List<int[]> generate(int[] arr, int r) {
		List<int[]> result = new ArrayList<>();
		generateCombination(arr, r, 0, new int[r], 0, result);
		return result;
	}

	public static void generateCombination(int[] arr, int r, int start, int[] curr, int depth, List<int[]> result) {
		// r 개 선택 완료
		if (depth == r) {
			result.add(curr.clone());
			return;
		}

		// 재귀: 모든 가능한 선택 시도
		for (int i = start; i < arr.length; i++) {
			curr[depth] = arr[i];
			generateCombination(arr, r, i + 1, curr, depth + 1, result);
		}
	}

	/// 배열 안 3개 원소의 합 <= target 최적값 찾기
	public static int findMaxSumUnder(int[] arr, int target, int start, int depth, int currentSum) {
		if (depth == 3) {
			if (currentSum <= target) {
				return currentSum;
			}
			return 0;
		}

		if (start >= arr.length) {
			return 0;
		}

		int maxSum = 0;
		for (int i = start; i < arr.length; i++) {
			int sum = findMaxSumUnder(arr, target, i + 1, depth, currentSum + arr[i]);
			maxSum = Math.max(maxSum, sum);
		}

		return maxSum;
	}
}
