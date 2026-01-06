package daniel.algorithmdeepdive.pattern.search.twopointer.opposite;

import java.util.Arrays;

/// ## 반대 방향 투포인터 - 응용
/// ### OppositeBasic 확장
/// - `twoSum`을 반복 적용
/// - 중복 제거 로직 추가
/// ### 핵심 아이디어
/// - `threeSum`: 첫 번째 수 고정 + 나머지는 `twoSum`
/// - `fourSum`: 두 번째 수까지 고정 + 나머지는 `twoSum`
public class OppositeAdvanced {

	/// 세 수의 합이 0인 조합 개수
	/// @param arr 정수 배열 (정렬됨)
	/// @return 조합 개수
	public static int threeSum(int[] arr) {
		Arrays.sort(arr);
		int count = 0;

		// 첫 번째 수 고정
		for (int i = 0; i < arr.length - 2; i++) {
			// 중복 제거
			if (i > 0 && arr[i] == arr[i - 1]) {
				continue;
			}

			// 나머지 두 수는 투포인터로
			int left = i + 1;
			int right = arr.length - 1;
			int target = -arr[i]; // arr[i] + left + right = 0

			while (left < right) {
				int sum = arr[left] + arr[right];

				if (sum == target) {
					count++;

					// 중복 건너뛰기
					while (left < right && arr[left] == arr[left + 1]) left++;
					while (left < right && arr[right] == arr[right - 1]) right--;

					left++;
					right--;
				} else if (sum < target) {
					left++;
				} else {
					right--;
				}
			}
		}
		return count;
	}

	/// 배열 재배치: 홀수는 앞, 짝수는 뒤
	/// @param arr 정수 배열
	/// @return 재배치된 배열 (원본 수정)
	public static int[] separateOddEven(int[] arr) {
		int left = 0;
		int right = arr.length - 1;

		while (left < right) {
			if (arr[left] % 2 == 1) {
				left++;
			} else if (arr[right] % 2 == 0) {
				right--;
			} else {
				// left 가 짝수, right 가 홀수 -> 교환
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
