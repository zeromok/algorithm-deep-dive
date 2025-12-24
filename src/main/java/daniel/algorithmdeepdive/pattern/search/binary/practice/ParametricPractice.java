package daniel.algorithmdeepdive.pattern.search.binary.practice;

import daniel.algorithmdeepdive.pattern.search.binary.Parametric;

/**
 * Parametric Search 실험장
 *
 * <h3>실험 목적</h3>
 * - 결정 함수(validator)가 어떻게 동작하는지 관찰
 * - "조건을 만족하는 최댓값"이 왜 찾아지는지 이해
 * - 백준 1654 스타일 문제 시뮬레이션
 */
public class ParametricPractice {

	public static void main(String[] args) {
		// 백준 1654 스타일: K개를 만들 수 있는 최대 길이
		int[] cables = {802, 743, 457, 539};
		int k = 11;

		System.out.println("랜선 길이: [802, 743, 457, 539]");
		System.out.println("목표 개수: " + k);
		System.out.println();

		long answer = traceParametric(cables, k, 1, 802);

		System.out.println();
		System.out.println("=== 최종 답: " + answer + " ===");
	}

	private static long traceParametric(int[] cables, int k, long min, long max) {
		long left = min, right = max, answer = min - 1;
		int step = 0;

		System.out.println("--- Parametric Search ---");

		while (left <= right) {
			long mid = left + (right - left) / 2;

			// 결정 함수: mid 길이로 k개 이상 만들 수 있나?
			long count = 0;
			for (int cable : cables) {
				count += cable / mid;
			}

			boolean canMake = count >= k;

			System.out.printf("Step %d: left=%d, mid=%d, right=%d\n",
					++step, left, mid, right);
			System.out.printf("  → mid=%d로 자르면 %d개 생성 (목표: %d개)\n", mid, count, k);

			if (canMake) {
				System.out.println("  → 조건 만족! answer=" + mid + ", 더 큰 값 탐색 (left=mid+1)");
				answer = mid;
				left = mid + 1;
			} else {
				System.out.println("  → 조건 불만족! 더 작은 값 탐색 (right=mid-1)");
				right = mid - 1;
			}
		}

		System.out.println("→ 종료 (left > right)");
		return answer;
	}
}
