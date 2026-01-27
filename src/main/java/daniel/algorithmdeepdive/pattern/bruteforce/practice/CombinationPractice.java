package daniel.algorithmdeepdive.pattern.bruteforce.practice;

import java.util.Arrays;

public class CombinationPractice {
	public static void main(String[] args) {
		int[] arr1 = new int[] {5, 3, 7, 3, 1}; // 조합 추출용 배열
		int r = 3; // 선택할 요소 개수
		recursionCombination(arr1, r, 0, new int[r], 0);

		int maxSumUnder = findMaxSumUnderWithPruning(arr1, 10, 0, 0, 0);
		System.out.println(maxSumUnder);
	}

	/// 배열에서 특정 개수(r)의 요소를 선택하는 모든 조합(Combination)을 생성하여 출력한다.
	/// <p>이 메서드는 중복을 허용하지 않는 않는 조합을 찾기 위해 백트래킹 방식을 사용하며,
	/// 각 단계에서 선택된 요소는 {@code combi} 배열에 저장된다.
	/// </p>
	/// @param arr 조합을 추출할 원본 배열
	/// @param r 선택할 요소의 개수
	/// @param start 탐색을 시작할 배열의 인덱스 (중복 선택 방지)
	/// @param combi 현재까지 선택된 요소들을 임시 저장하는 배열
	/// @param depth 현재까지 선택된 요소의 개수 (재귀 호출 깊이)
	public static void recursionCombination(int[] arr, int r, int start, int[] combi, int depth) {
		// 종료 조건: r 개 선택 완료
		if (depth == r) {
			System.out.println("조합 생성 -> " + Arrays.toString(combi));
			return;
		}

		for (int i = start; i < arr.length; i++) { // 현재 위치부터 배열 끝까지 탐색
			combi[depth] = arr[i]; // 현재 깊이에 요소 저장
			recursionCombination(arr, r, i + 1, combi, depth + 1); // 다음 요소 선택을 위한 재귀 호출
		}
	}

	/// 배열 내 3개 원소의 합이 `target` 이하인 조합 중 최댓값을 찾는다. (가지치기 적용)
	///
	/// 탐색 과정에서 합계가 이미 `target`을 초과하거나, 정확히 `target`과 일치하는 경우
	/// 불필요한 재귀 호출을 중단하여 성능을 최적화한다.
	///
	///
	/// @param arr        원소들이 포함된 정수 배열
	/// @param target     합계의 상한선 (목표 값)
	/// @param start      탐색을 시작할 배열의 인덱스
	/// @param depth      현재까지 선택된 원소의 개수 (3개 선택 시 종료)
	/// @param currentSum 현재까지 선택된 원소들의 합계
	/// @return           조건을 만족하는 합의 최댓값. 만족하는 조합이 없으면 0을 반환.
	public static int findMaxSumUnderWithPruning(int[] arr, int target, int start, int depth, int currentSum) {
		// 가지치기 1: 이미 target 을 초과한 경우, 더 이상의 탐색은 무의미 (0 반환으로 무시)
		if (currentSum > target) {
			return 0;
		}

		// 가지치기 2: 정확히 target 과 일치하고 3개를 골랐다면 최적해이므로 즉시 반환
		if (currentSum == target && depth == 3) {
			return target;
		}

		// 종료 조건: 3개 선택이 완료된 경우 현재 합 반환
		if (depth == 3) {
			return currentSum;
		}

		// 종료 조건: 배열 끝까지 탐색했으나 3개를 채우지 못한 경우
		if (start >= arr.length) {
			return 0;
		}

		int maxSum = 0;
		for (int i = start; i < arr.length; i++) {
			// 재귀를 통해 얻은 결과 중 최댓값을 지속적으로 갱신
			int sum = findMaxSumUnderWithPruning(arr, target, i + 1, depth + 1, currentSum + arr[i]);
			maxSum = Math.max(maxSum, sum);

			// (선택사항) 만약 이미 maxSum 이 target 에 도달했다면 더 이상 루프를 돌 필요가 없음
			if (maxSum == target) break;
		}

		return maxSum;
	}
}
