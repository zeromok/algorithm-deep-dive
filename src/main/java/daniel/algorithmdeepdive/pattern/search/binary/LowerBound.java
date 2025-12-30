package daniel.algorithmdeepdive.pattern.search.binary;

/// ## Lower Bound: target 이상인 값이 처음 나타나는 위치
/// ### 언제 쓰는가?
/// - "X 이상인 첫 번째 원소는?" → LowerBound
/// ### 수학적 정의
/// `min{i | arr[i] >= target}`
/// ### 반환값
/// - 0 ~ arr.length (모두 작으면 arr.length 반환)
/// ### 핵심 차이
/// - ExactSearch: left <= right, 못 찾으면 -1
/// - LowerBound: left < right, 항상 유효한 위치 반환
/// ### 실험
/// @see daniel.algorithmdeepdive.pattern.search.binary.practice.LowerBoundPractice
public class LowerBound {

	public static int search(int[] arr, int target) {
		int left = 0, right = arr.length;

		while (left < right) {
			int mid = left + (right - left) / 2;

			if (arr[mid] >= target) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}
}
