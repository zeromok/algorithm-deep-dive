package daniel.algorithmdeepdive.pattern.bruteforce;

/// # 완전탐색 패턴 V0 - 3중 루프
/// ## 언제 쓰는가?
/// - "3중 루프 (작은 데이터셋, N <= 100)" → LoopV0
///
/// ## 핵심 원리
/// - N개 중 3개를 선택하는 모든 조합을 중첩 루프로 탐색
/// - 간단하고 직관적인 구현
///
/// ## 시간 복잡도: O(N³)
/// ## 공간 복잡도: O(1)
///
/// ## 학습 포인트
/// - 중첩 루프를 통한 완전탐색
/// - 인덱스 범위 설정 (중복 방지)
public class LoopV0 {

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
