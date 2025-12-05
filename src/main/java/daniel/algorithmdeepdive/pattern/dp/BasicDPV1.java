package daniel.algorithmdeepdive.pattern.dp;

/// # DP 패턴 V1 - 재귀 + 메모이제이션 (Top-down DP)
/// ## 핵심 원리
/// - 재귀 호출 구조 유지
/// - 메모이제이션으로 중복 계산 방지
/// - Top-down 방식: 큰 문제를 작은 문제로 분해
///
/// ## 시간 복잡도: O(N)
/// ## 공간 복잡도: O(N) - 메모이제이션 테이블 + 재귀 스택
///
/// ## V0 대비 개선
/// - 중복 계산 제거로 지수 시간 → 선형 시간
/// - 메모이제이션 테이블로 계산 결과 저장
///
/// ## 학습 포인트
/// - 메모이제이션의 동작 원리
/// - Top-down DP의 구조
/// - 재귀 + 캐싱 패턴
///
/// ## 실무 적용
/// - 복잡한 점화식이 있는 경우
/// - 필요한 부분만 계산하는 경우
/// - Top-down이 더 직관적인 경우
public class BasicDPV1 {

	/// 메모이제이션 - 배열
	/// @param n 계산할 인덱스
	/// @return n번째 피보나치 수
	public static int fibonacci(int n) {
		// 배열 크기: n + 1(0부터 n까지 저장)
		int[] memo = new int[n + 1];

		// 미계산 상태 표시: -1로 초기화
		// -1이 아니면 이미 계산된 값
		for (int i = 0; i <= n; i++) {
			memo[i] = -1;
		}

		// 종료 조건 초기화
		memo[0] = 0; // f(0) = 0
		if (n >= 1) {
			memo[1] = 1; // f(1) = 1
		}

		return recursive(n, memo);
	}

	/// 재귀 함수
	///
	/// @param n 현재 계산할 인덱스
	/// @param memo 메모이제이션 배열
	/// @return n번째 피보나치 수
	private static int recursive(int n, int[] memo) {
		// 1. 종료 조건 확인
		if (n <= 1) {
			return memo[n];
		}

		// 2. 메모이제이션 체크
		if (memo[n] != -1) {
			return memo[n]; // 계산된 값이 있으면 바로 반환 (중복 계산 방지)
		}

		// 3. 점화식 적용: 큰 문제를 작은 문제로 나누어 해결
		// f(n) = f(n - 1) + f(n - 2)
		int result = recursive(n - 1, memo) + recursive(n - 2, memo);

		// 4. 계산 결과 저장
		memo[n] = result;

		return result;
	}
}
