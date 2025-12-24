package daniel.algorithmdeepdive.pattern.search.binary;

/**
 * Lower Bound: target 이상인 값이 처음 나타나는 위치
 *
 * <h3>수학적 정의</h3>
 * min{i | arr[i] >= target}
 *
 * <h3>사용 상황</h3>
 * - "X 이상인 첫 번째 원소는?"
 * - "정렬된 배열에 X를 삽입할 위치는?"
 * - 범위 쿼리의 시작점
 *
 * <h3>반환값</h3>
 * - 0 ~ arr.length (모두 작으면 arr.length 반환)
 *
 * <h3>핵심 차이</h3>
 * - ExactSearch: left <= right, 못 찾으면 -1
 * - LowerBound: left < right, 항상 유효한 위치 반환
 *
 * <h3>실험</h3>
 * @see daniel.algorithmdeepdive.pattern.search.binary.practice.LowerBoundPractice
 */
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
