package daniel.algorithmdeepdive.pattern.dp.practice;

import java.util.HashMap;
import java.util.Map;

/// ## DP 실행 흐름 관찰
/// ### 실험 목적
/// - V0: 중복 계산이 어떻게 발생하는지 관찰
/// - V1: 메모이제이션이 중복 계산을 어떻게 방지하는지 관찰
/// - V2: Bottom-up으로 테이블을 채우는 과정 관찰
public class DPPractice {
	private static int callCount = 0;
	private static Map<Integer, Integer> callMap = new HashMap<>();

	public static void main(String[] args) {
		int n = 5;

		System.out.println("=== 피보나치 f(" + n + ") 계산 ===\n");

		// V0: 중복 계산 관찰
		System.out.println("--- V0: 재귀만 (중복 계산) ---");
		callCount = 0;
		callMap.clear();
		int result0 = fibonacciV0(n);
		System.out.println("결과: " + result0);
		System.out.println("총 호출 횟수: " + callCount);
		System.out.println("중복 호출: f(3)=" + callMap.getOrDefault(3, 0) + "번, f(2)=" + callMap.getOrDefault(2, 0) + "번");
		System.out.println();

		// V1: 메모이제이션 관찰
		System.out.println("--- V1: 메모이제이션 (중복 방지) ---");
		callCount = 0;
		callMap.clear();
		int[] memo = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			memo[i] = -1;
		}
		memo[0] = 0;
		memo[1] = 1;
		int result1 = fibonacciV1(n, memo);
		System.out.println("결과: " + result1);
		System.out.println("총 호출 횟수: " + callCount);
		System.out.println("메모이제이션으로 중복 계산 방지!");
		System.out.println();

		// V2: Bottom-up 관찰
		System.out.println("--- V2: Bottom-up (반복문) ---");
		int[] dp = new int[n + 1];
		dp[0] = 0;
		dp[1] = 1;
		System.out.println("dp[0] = 0");
		System.out.println("dp[1] = 1");
		for (int i = 2; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
			System.out.println("dp[" + i + "] = dp[" + (i - 1) + "] + dp[" + (i - 2) + "] = " + dp[i]);
		}
		System.out.println("결과: " + dp[n]);
	}

	private static int fibonacciV0(int n) {
		callCount++;
		callMap.put(n, callMap.getOrDefault(n, 0) + 1);

		if (n <= 1) {
			return n;
		}

		return fibonacciV0(n - 1) + fibonacciV0(n - 2);
	}

	private static int fibonacciV1(int n, int[] memo) {
		callCount++;

		if (n <= 1) {
			return memo[n];
		}

		if (memo[n] != -1) {
			System.out.println("  메모이제이션 hit: f(" + n + ") = " + memo[n]);
			return memo[n];
		}

		int result = fibonacciV1(n - 1, memo) + fibonacciV1(n - 2, memo);
		memo[n] = result;
		System.out.println("  계산 완료: f(" + n + ") = " + result + " (저장)");
		return result;
	}
}

