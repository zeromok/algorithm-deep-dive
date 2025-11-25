package daniel.algorithmdeepdive.search.binary;

/// # BinarySearchV2 - 엣지 케이스 방어
///
/// ## V1 대비 개선 사항
/// - 입력 검증 (null, 빈 배열)
/// - 정렬 검증 (디버그 모드)
/// - 명확한 예외 처리
/// - Assertion 으로 불변식 검증
public class BinarySearchV2 {

	private static final boolean DEBUG_MODE = false;

	/// ### 기본 이진 탐색: 정렬된 배열에서 `target`의 정확한 위치를 찾는다.
	/// @param arr 오름차순 정렬된 정수 배열
	/// @param target 찾고자 하는 값
	/// @return target 의 인덱스, 없으면 -1
	/// @throws IllegalArgumentException arr 가 null 이거나 정렬되지 않음
	public static int binarySearch(int[] arr, int target) {
		validateInput(arr);

		int left = 0;
		int right = arr.length - 1;

		while (left <= right) {
			int mid = left + (right - left) / 2; // 오버플로우 방지

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

	// 입력 검증
	private static void validateInput(int[] arr) {
		if (arr == null) {
			throw new IllegalArgumentException("배열은 null 일 수 없습니다.");
		}

		// 디버그 모드에서만 정렬 검증 (성능 고려)
		if (DEBUG_MODE && arr.length > 1 && !isSorted(arr)) {
			throw new IllegalArgumentException("오름차순 정렬이 된 배열이여야 합니다.");
		}
	}

	private static boolean isSorted(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] < arr[i - 1]) {
				return false;
			}
		}
		return true;
	}

	/// ### Lower Bound: `target` 이상인 값이 처음 나타나는 위치
	/// 수학적 정의: `min{i | arr[i] >= target}`
	/// @param arr 오름차순 정렬된 정수 배열
	/// @param target 찾고자 하는 기준 값
	/// @return arr[i] >= target 인 최소 i
	public static int lowerBound(int[] arr, int target) {
		validateInput(arr);
		if (arr.length == 0) return 0;

		int left = 0;
		int right = arr.length;

		while (left < right) {
			int mid = left + (right - left) / 2;

			if (arr[mid] >= target) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		assert left >= 0 && left <= arr.length : "lowerBound() 잘못됨";
		return left;
	}

	/// ### Upper Bound: target 초과인 값이 처음 나타나는 위치
	/// 수학적 정의: `min{i | arr[i] > target}`
	///
	/// @param arr 오름차순 정렬된 정수 배열
	/// @param target 찾고자 하는 기준 값
	/// @return arr[i] > target인 최소 i
	public static int upperBound(int[] arr, int target) {
		int left = 0;
		int right = arr.length;

		while (left < right) {
			int mid = left + (right - left) / 2;

			if (arr[mid] > target) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		assert left >= 0 && left <= arr.length : "upperBound() 잘못됨";
		return left;
	}

	/// ### 특정 값의 출현 횟수
	/// 원리: upperBound - lowerBound
	///
	/// @param arr 오름차순 정렬된 정수 배열
	/// @param target 개수를 세고자 하는 값
	/// @return target의 출현 횟수
	public static int countOccurrences(int[] arr, int target) {
		validateInput(arr);

		int lower = lowerBound(arr, target);

		// Early Return: target 이 없으면 0
		if (lower == arr.length || arr[lower] != target) {
			return 0;
		}

		int upper = upperBound(arr, target);
		return Math.max(0, upper - lower); // 음수 방지 (안전장치)
	}

	/// ### 특정 값의 범위 찾기
	/// target 의 시작 인덱스와 끝 인덱스를 한 번에 반환
	///
	/// @param arr 오름차순 정렬된 배열
	/// @param target 찾을 값
	/// @return {startIndex, endIndex} 또는 {-1, -1} (없는 경우)
	public static int[] findRange(int[] arr, int target) {
		validateInput(arr);

		int lower = lowerBound(arr, target);

		// Early Return: target 이 없으면 upperBound 호출 불필요
		if (lower == arr.length || arr[lower] != target) {
			return new int[]{-1, -1};
		}

		int upper = upperBound(arr, target);
		return new int[]{lower, upper - 1};  // upper 는 끝 다음이므로 -1
	}
}
