package daniel.algorithmdeepdive.pattern.bruteforce;

import java.util.ArrayList;
import java.util.List;

/// # 완전탐색 패턴 - 조합 (Combination)
/// ## 언제 쓰는가?
/// - "N개 중 R개를 선택하는 모든 경우" → Combination
///
/// ## 핵심 원리
/// - N 개 중 R 개를 선택하는 모든 조합을 재귀적으로 생성
/// - 백트래킹 구조를 활용하되, 가지치기는 없음 (순수 완전탐색)
///
/// ## 시간 복잡도: O(C(N,R)) = O(N! / (R! * (N-R)!))
///   - 예: C(10,3) = 120, C(20,5) = 15,504
///
/// ## 공간 복잡도: O(R)
///   - 재귀 호출 스택 깊이 = R
///   - 결과 저장 공간 제외 시
public class Combination {

	/// 패턴 1: 모든 조합 생성 (범용)
	/// 사용: 모든 조합을 리스트로 받음
	public static List<int[]> generate(int[] arr, int r) {
		List<int[]> result = new ArrayList<>();
		generateCombination(arr, r, 0, new int[r], 0, result);
		return result;
	}

	/// 재귀를 사용한 조합 생성
	/// - 구조: 백트래킹 구조
	/// - 가지치기: 없음 (순수 완전탐색)
	public static void generateCombination(int[] arr, int r, int start, int[] curr, int depth, List<int[]> result) {
		// 종료 조건: r 개 선택 완료
		if (depth == r) {
			result.add(curr.clone());
			return;
		}

		// 재귀: 모든 가능한 선택 시도
		for (int i = start; i < arr.length; i++) {
			curr[depth] = arr[i];
			generateCombination(arr, r, i + 1, curr, depth + 1, result);
			// 암묵적 되돌림: curr[depth]는 다음 루프에서 덮어써짐
		}
	}

	/// 패턴 2: 배열 안 3개 원소의 합 <= target 최적값 찾기
	/// 사용: 3개 선택, target 이하 최댓값
	public static int findMaxSumUnder(int[] arr, int target, int start, int depth, int currentSum) {
		// 종료 조건
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

	/// 패턴 4: 백트래킹
	/// 사용: 조기 종료 조건이 명확한 경우
	/// 차이점: 불필요한 경로를 depth = 3 전에 차단
	public static int findMaxSumUnderWithPruning(int[] arr, int target, int start, int depth, int currentSum) {
		// 가지치기 1: 이미 target 초과 → 더 탐색 불필요
		if (currentSum > target) {
			return 0;
		}

		// 가지치기 2: 정확히 target → 최적해 발견
		if (currentSum == target && depth == 3) {
			return target;
		}

		// 종료 조건: 3개 선택 완료
		if (depth == 3) {
			return currentSum;
		}

		// 배열 끝 도달
		if (start >= arr.length) {
			return 0;
		}

		int maxSum = 0;
		for (int i = start; i < arr.length; i++) {
			int sum = findMaxSumUnderWithPruning(arr, target, i + 1, depth + 1, currentSum + arr[i]);
			maxSum = Math.max(maxSum, sum);
		}

		return maxSum;
	}
}
