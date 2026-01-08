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

	/// 두 수의 합
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

	/// 서로 다른 세 수의 합
	public static int threeSum(int[] arr) {
		int count = 0;

		for (int i = 0; i < arr.length - 1; i++) {

			// 중복 제거
			if (i > 0 && arr[i] == arr[i - 1]) {
				continue;
			}

			int left = 0;
			int right = arr.length - 1;
			int target = -arr[i];

			while (left < right) {
				int sum = arr[left] + arr[right];

				if (sum == target) {
					count++;

					// 중복 건너 뛰기
					while (left < right && arr[left] == arr[left + 1])
						left++;
					while (left < right && arr[right] == arr[right - 1])
						right--;

					left++;
					right++;
				} else if (sum < target) {
					left++;
				} else {
					right--;
				}
			}
		}
		return count;
	}

	/// 홀짝 분리
	public static int[] separateOddEven(int[] arr) {
		int left = 0;
		int right = arr.length - 1;

		while (left < right) {
			if (arr[left] % 2 == 1) {
				left++;
			} else if (arr[right] % 2 == 0) {
				right--;
			} else {
				// left가 짝수, right가 홀수 → 교환
				int temp = arr[left];
				arr[left] = arr[right];
				arr[right] = temp;
				left++;
				right--;
			}
		}
		return arr;
	}
}
