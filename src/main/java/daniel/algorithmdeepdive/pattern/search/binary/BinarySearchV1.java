package daniel.algorithmdeepdive.pattern.search.binary;

/// # BinarySearchV0 - 이진 탐색 실전 최적화
///
/// ## V0 대비 개선 사항
/// - 실용적 헬퍼 메서드 추가
/// - Early Return 패턴 적용
public class BinarySearchV1 {

	/// ### 기본 이진 탐색: 정렬된 배열에서 `target`의 정확한 위치를 찾는다.
	/// @param arr 오름차순 정렬된 정수 배열
	/// @param target 찾고자 하는 값
	/// @return target 의 인덱스, 없으면 -1
	public static int binarySearch(int[] arr, int target) {
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

	/// ### Lower Bound: `target` 이상인 값이 처음 나타나는 위치
	/// 수학적 정의: `min{i | arr[i] >= target}`
	/// @param arr 오름차순 정렬된 정수 배열
	/// @param target 찾고자 하는 기준 값
	/// @return arr[i] >= target 인 최소 i
	public static int lowerBound(int[] arr, int target) {
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
		return left;
	}

	/// ### 특정 값의 출현 횟수
	/// 원리: upperBound - lowerBound
	///
	/// @param arr 오름차순 정렬된 정수 배열
	/// @param target 개수를 세고자 하는 값
	/// @return target의 출현 횟수
	public static int countOccurrences(int[] arr, int target) {
		int lower = lowerBound(arr, target);

		// Early Return: target 이 없으면 0
		if (lower == arr.length || arr[lower] != target) {
			return 0;
		}

		int upper = upperBound(arr, target);
		return upper - lower;
	}

	/// ### 특정 값의 범위 찾기
	/// target 의 시작 인덱스와 끝 인덱스를 한 번에 반환
	///
	/// @param arr 오름차순 정렬된 배열
	/// @param target 찾을 값
	/// @return {startIndex, endIndex} 또는 {-1, -1} (없는 경우)
	public static int[] findRange(int[] arr, int target) {
		int lower = lowerBound(arr, target);

		// Early Return: target 이 없으면 upperBound 호출 불필요
		if (lower == arr.length || arr[lower] != target) {
			return new int[]{-1, -1};
		}

		int upper = upperBound(arr, target);
		return new int[]{lower, upper - 1};  // upper 는 끝 다음이므로 -1
	}
}
