package daniel.algorithmdeepdive.pattern.dp;

public class LCS {

	public static void main(String[] args) {
		String str1 = "ABCBDAB";
		String str2 = "BDCABA";

		findLCS(str1, str2);
	}

	private static void findLCS(String s1, String s2) {
		int m = s1.length();
		int n = s2.length();

		// 1. DP 테이블 생성
		int[][] dp = new int[m + 1][n + 1];

		// 2. 테이블 채우기
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) { // 두 문자가 같은 경우
					// 왼쪽 대각선 값에 1을 더함
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else { // 두 문자가 다른 경우
					// 위쪽이나 왼쪽 값 중 더 큰 값을 가져옴
					dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
				}
			}
		}

		System.out.println("LCS의 길이: " + dp[m][n]);
	}
}
