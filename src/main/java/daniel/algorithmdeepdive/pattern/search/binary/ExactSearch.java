package daniel.algorithmdeepdive.pattern.search.binary;

/// ## 정확히 일치하는 값 찾기
/// ### 언제 쓰는가?
/// - "정렬된 배열에 X가 존재하나?" → ExactSearch
/// ### 반환값
/// - 찾으면: 인덱스
/// - 못 찾으면: -1
/// ### 실험
/// @see daniel.algorithmdeepdive.pattern.search.binary.practice.ExactSearchPractice
public class ExactSearch {

	public static int search(int[] arr, int target) {
		int left = 0, right = arr.length - 1;

		while (left <= right) {
			int mid = left + (right - left) / 2;

			if (arr[mid] == target) {
				return mid;
			} else if (arr[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return -1;
	}
}
