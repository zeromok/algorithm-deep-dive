package daniel.algorithmdeepdive.pattern.search.binary;

import java.util.function.Predicate;

/**
 * 파라메트릭 서치 (Parametric Search)
 *
 * <h3>핵심 개념</h3>
 * - 최적화 문제를 결정 문제로 변환하여 이분탐색 적용
 * - "X가 가능한가?"를 판단하는 validator 함수가 핵심
 *
 * <h3>사용 상황</h3>
 * - "조건을 만족하는 최댓값/최솟값" 구하기
 * - 답의 범위가 넓어서 완전탐색 불가능
 * - isValid(x) 함수를 O(N) 이하로 구현 가능
 *
 * <h3>처음 배운 문제</h3>
 * - 백준 1654 (랜선 자르기):
 *   <a href="https://github.com/zeromok/ps-baekjoon-1654-parametric-search">학습 과정</a>
 * - 블로그: <a href="https://b-mokk.tistory.com/84">파라메트릭 서치란?</a>
 *
 * <h3>실험</h3>
 * @see daniel.algorithmdeepdive.pattern.search.binary.practice.ParametricPractice
 */
public class Parametric {

	/**
	 * 최댓값 찾기 - 조건을 만족하는 값 중 가장 큰 값을 탐색
	 *
	 * <p>상태 분포: (만족, 만족, ..., 만족(정답), 불만족, 불만족)</p>
	 *
	 * @param min 탐색 범위의 하한 (가능한 최소 정답)
	 * @param max 탐색 범위의 상한 (가능한 최대 정답)
	 * @param validator 조건 검증 함수
	 * @return 조건을 만족하는 최대값. 만족하는 값이 없으면 min-1 반환
	 */
	public static int findMax(int min, int max, Predicate<Integer> validator) {
		int left = min, right = max, answer = min - 1;

		while (left <= right) {
			int mid = left + (right - left) / 2;

			if (validator.test(mid)) {
				answer = mid;
				left = mid + 1; // 더 큰 값 탐색
			} else {
				right = mid - 1;
			}
		}
		return answer;
	}

	/**
	 * 최솟값 찾기 - 조건을 만족하는 값 중 가장 작은 값을 탐색
	 *
	 * <p>상태 분포: (불만족, 불만족, 만족(정답), 만족, ..., 만족)</p>
	 *
	 * @param min 탐색 범위의 하한
	 * @param max 탐색 범위의 상한
	 * @param validator 조건 검증 함수
	 * @return 조건을 만족하는 최솟값. 만족하는 값이 없으면 max+1 반환
	 */
	public static int findMin(int min, int max, Predicate<Integer> validator) {
		int left = min, right = max, answer = max + 1;

		while (left <= right) {
			int mid = left + (right - left) / 2;

			if (validator.test(mid)) {
				answer = mid;
				right = mid - 1; // 더 작은 값 탐색
			} else {
				left = mid + 1;
			}
		}
		return answer;
	}

	/**
	 * Long 타입 최댓값 찾기 (큰 범위 대응)
	 */
	public static long findMaxLong(long min, long max, Predicate<Long> validator) {
		long left = min, right = max, answer = min - 1;

		while (left <= right) {
			long mid = left + (right - left) / 2;

			if (validator.test(mid)) {
				answer = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return answer;
	}
}
