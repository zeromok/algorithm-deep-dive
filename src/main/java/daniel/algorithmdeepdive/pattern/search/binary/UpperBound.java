package daniel.algorithmdeepdive.pattern.search.binary;

/// ## Upper Bound: target 초과인 값이 처음 나타나는 위치
/// ### 언제 쓰는가?
/// - "X 초과인 첫 번째 원소는?" → UpperBound
/// ### 수학적 정의
/// `min{i | arr[i] > target}`
/// ### LowerBound와의 차이
/// - LowerBound: `arr[mid] >= target`
/// - UpperBound: `arr[mid] > target` (부등호만 다름!)
/// ### 실험
/// @see daniel.algorithmdeepdive.pattern.search.binary.practice.LowerBoundPractice
public class UpperBound {

	public static int search(int[] arr, int target) {
		int left = 0, right = arr.length;

		while (left < right) {
			int mid = left + (right - left) / 2;

			if (arr[mid] > target) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}
}
