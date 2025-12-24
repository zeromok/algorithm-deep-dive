package daniel.algorithmdeepdive.pattern.search.binary;

/**
 * Upper Bound: target 초과인 값이 처음 나타나는 위치
 *
 * <h3>수학적 정의</h3>
 * min{i | arr[i] > target}
 *
 * <h3>사용 상황</h3>
 * - "X 초과인 첫 번째 원소는?"
 * - 범위 쿼리의 끝점 (exclusive)
 * - 중복 원소의 마지막 다음 위치
 *
 * <h3>LowerBound와의 차이</h3>
 * - LowerBound: arr[mid] >= target
 * - UpperBound: arr[mid] > target (부등호만 다름!)
 *
 * <h3>실험</h3>
 * @see daniel.algorithmdeepdive.pattern.search.binary.practice.LowerBoundPractice
 */
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
