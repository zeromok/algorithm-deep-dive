package daniel.algorithmdeepdive.pattern.bruteforce;


/// # 완전탐색 패턴 V1 - 범용 N중 루프
/// ## V0 대비 개선
/// - 3중 루프 → N중 루프로 범용화
/// - 재귀를 통한 동적 중첩 깊이
///
/// ## 시간 복잡도: O(N^r) (r = 선택 개수)
/// ## 공간 복잡도: O(r)
///
/// ## 학습 포인트
/// - 재귀를 통한 동적 루프 생성
/// - 범용적인 완전탐색 패턴
public class LoopV1 {

	/// N개 중 r개를 선택하는 모든 조합 탐색
	///
	/// @param arr 배열
	/// @param r 선택할 개수
	/// @param target 목표 값
	/// @return target 이하 최댓값
	public static int findMaxSumUnder(int[] arr, int r, int target) {
		return recursive(arr, r, target, 0, 0, 0);
	}

	private static int recursive(int[] arr, int r, int target,
		int start, int depth, int currentSum) {
		// 종료 조건
		if (depth == r) {
			return currentSum <= target ? currentSum : 0;
		}

		if (start >= arr.length) {
			return 0;
		}

		int maxSum = 0;
		for (int i = start; i < arr.length; i++) {
			int sum = recursive(arr, r, target, i + 1, depth + 1, currentSum + arr[i]);
			maxSum = Math.max(maxSum, sum);
		}

		return maxSum;
	}
}
