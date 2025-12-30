package daniel.algorithmdeepdive.pattern.greedy;

/// ## 그리디 패턴 - 큰 단위 우선 사용
/// ### 언제 쓰는가?
/// - "최소 개수로 목표 달성" → 그리디
///
/// ### 핵심 원리
/// - 최소 개수로 목표를 달성하려면 큰 단위를 최대한 많이 사용
///
/// ### 왜 큰 단위를 먼저?
/// - 5를 1개 줄이면 3을 2개 추가해야함 (5 = 3 * 2 - 1)
/// - 총 개수: -1 + 2 = +1 증가
/// 따라서, 큰 단위를 많이 쓸수록 총 개수 감소
///
/// ### 시간 복잡도: O(N/large)
/// ### 공간 복잡도: O(1)
public class LargestDenominationFirst {

	/// # 큰 단위 우선 그리디로 최소 개수 계산
	public static int solve(int target, int large, int small) {
		// 최적화: 큰 단위로 나누어 떨어지면 즉시 반환
		if (target % large == 0) {
			return target / large;
		}

		// 큰 단위를 최대한 많이 사용
		for (int useLarge = target / large; useLarge >= 0; useLarge--) {
			int remaining = target - (useLarge * large);

			if (remaining % small == 0) {
				int useSmall = remaining / small;
				return useLarge + useSmall;
			}
		}

		// 정확하지 않으면?
		return -1;
	}
}
