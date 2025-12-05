package daniel.algorithmdeepdive.pattern.dp;

/// # DP 패턴 V2 - 반복문 (Bottom-up DP)
/// ## 핵심 원리
/// - 작은 문제부터 큰 문제로 순차적 해결
/// - 재귀 호출 스택 오버헤드 없음
/// - Bottom-up 방식: 기저 조건부터 시작
///
/// ## 시간 복잡도: O(N)
/// ## 공간 복잡도: O(N) - DP 테이블
///
/// ## V1 대비 개선
/// - 재귀 스택 오버헤드 제거
/// - 반복문으로 명시적 순서 제어
/// - 스택 오버플로우 위험 없음
///
/// ## 학습 포인트
/// - Bottom-up DP의 구조
/// - 반복문으로 DP 테이블 채우는 순서
/// - Top-down vs Bottom-up 비교
///
/// ## 실무 적용
/// - 대부분의 DP 문제에서 선호되는 방식
/// - 명확한 계산 순서가 필요한 경우
/// - 스택 오버플로우를 피해야 하는 경우
public class BasicDPV2 {

	/// 반복문 방식 (Bottom-up)
	///
	/// @param n 계산할 인덱스
	/// @return n번째 피보나치 수
	public static int fibonacci(int n) {
		// 종료 조건
		if (n <= 1) {
			return n;
		}

		// DP 테이블 생성
		int[] dp = new int[n + 1];

		// 종료 조건 초기화
		dp[0] = 0;
		dp[1] = 1;

		// 점화식
		for (int i = 2; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}

		return dp[n];
	}
}
