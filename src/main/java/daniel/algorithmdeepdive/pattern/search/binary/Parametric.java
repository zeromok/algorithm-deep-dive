package daniel.algorithmdeepdive.pattern.search.binary;

public class Parametric {

	/// 최댓값 찾기 - 조건을 만족하는 값 중 가장 큰 값을 탐색한다.\
	/// 상태: (만족, 만족, ..., 만족(정답), 불만족, 불만족)
	/// @param min 탐색 범위의 하한 (가능한 최소 정답)
	/// @param max 탐색 범위의 상한 (가능한 최대 정답)
	/// @return 조건을 만족하는 최대값. 만족하는 값이 없으면 초기값(0) 반환
	public static int parametricSearchMax(int min, int max) {
		int left = min, right = max, answer = 0;

		while (left <= right) {
			int mid = left + (right - left) / 2;

			if (isValid(mid)) { // 조건을 만족하면
				answer = mid; // 일단 정답으로 기록하고
				left = mid + 1; // 더 큰 값이 있는지 오른쪽을 탐색
			} else {
				right = mid - 1; // 조건을 만족하지 않으면 범위를 줄임
			}
		}
		return answer;
	}

	/// 최솟값 찾기 - 조건을 만족하는 값 중 가장 작은 값을 탐색\
	/// 상태: (불만족, 불만족, 만족(정답), 만족, ..., 만족)
	/// @param min 탐색 범위의 하한 (가능한 최소 정답)
	/// @param max 탐색 범위의 상한 (가능한 최대 정답)
	/// @return 조건을 만족하는 최솟값. 만족하는 값이 없으면 초기값(0) 반환
	public static int parametricSearchMin(int min, int max) {
		int left = min, right = max, answer = 0;

		while (left <= right) {
			int mid = left + (right - left) / 2;

			if (isValid(mid)) { // 조건을 만족하면
				answer = mid; // 일단 정답으로 기록하고
				right = mid - 1; // 더 작은 값이 있는지 왼쪽을 탐색
			} else {
				left = mid + 1; // 조건을 만족하지 않으면 범위를 키움
			}
		}
		return answer;
	}



	static boolean isValid(int value) {
		// TODO: 여기에 검증 로직 작성
		return true;
	}
}
