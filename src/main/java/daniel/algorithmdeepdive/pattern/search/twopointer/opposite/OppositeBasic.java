package daniel.algorithmdeepdive.pattern.search.twopointer.opposite;

/// ## 반대 방향 투포인터 - 기본
/// ### 전제 조건
/// - 배열은 정렬되어 있어야 함
/// ### 핵심 원리
/// - `left`=0, `right`=끝에서 시작
/// - `sum < target` → `left++` (더 큰 값 필요)
/// - `sum > target` → `right--` (더 작은 값 필요)
/// - `sum == target` → 발견
/// ### 시간 복잡도
/// - O(N): 각 포인터가 한 번씩만 이동
/// - 정렬 필요시 O(N log N)
/// ### 왜 정렬이 필요한가?
/// - `arr[left]`는 증가, `arr[right]`는 감소 보장
/// - 이 단조성이 있어야 "되돌아가지 않는다" 성립
public class OppositeBasic {

	/// 두 수의 합이 `target`인지 확인
	/// @param arr 정렬된 배열
	/// @param target 목표 합
	/// @return 존재 여부
	public static boolean twoSum(int[] arr, int target) {
		int left = 0;
		int right = arr.length - 1;
		while (left < right) {
			int sum = arr[left] + arr[right];

			if (sum == target) {
				return true;
			} else if (sum < target) {
				left++;
			} else {
				right--;
			}
		}
		return false;
	}

	/// 두 수의 인덱스 반환
	/// @param arr 정렬된 배열
	/// @param target 목표 합
	/// @return [left, right] 인덱스 쌍, 없으면 null
	public static int[] twoSumIndices(int[] arr, int target) {
		int left = 0;
		int right = arr.length - 1;

		while (left < right) {
			int sum = arr[left] + arr[right];

			if (sum == target) {
				return new int[]{left, right};
			} else if (sum < target) {
				left++;
			} else {
				right--;
			}
		}
		return null;
	}

	/// 두 수의 차이가 `target`인지 확인
	/// @param arr 정렬된 배열
	/// @param target 목표 차이
	/// @return 존재 여부
	public static boolean twoDifference(int[] arr, int target) {
		int left = 0;
		int right = 1;

		while (right < arr.length) {
			int diff = arr[right] - arr[left];

			if (diff == target) {
				return true;
			} else if (diff < target) {
				right++; // 차이를 더 크게
			} else {
				left++; // 차이를 더 작게
				if (left == right) right++; // 같아지면 right 이동
			}
		}
		return false;
	}
}
