package daniel.algorithmdeepdive.pattern.search.binary;

public class Basic {

	public static void main(String[] args) {
		int[] A = new int[100];
		for (int i = 0; i < 100; i++) {
			A[i] = i;
		}

		System.out.println(binarySearchBasic(A, 65));
	}


	/// 정렬된 배열에서 이분 탐색(Binary Search)를 통해 특정 타겟의 인덱스를 반환한다.\
	/// 이 메서드는 가장 기초적인 형태의 이분 탐색으로, 타겟을 찾는 즉시 해당 인덱스를 반환한다.\
	/// 배열 내에 중복된 값이 있을 경우, 어떤 인덱스가 반환될지 보장하지 않는다.
	/// @param arr 탐색 대상이 되는 정수 배열 (정렬되어 있어야 함)
	/// @param target 찾고자 하는 대상 값
	/// @return 타겟의 인덱스. 존재하지 않을 경우 -1 반환
	/// @implNote 시간 복잡도: O(log N)
	private static int binarySearchBasic(int[] arr, int target) {
		int left = 0, right = arr.length - 1;

		while (left <= right) {
			int mid = left + (right - left) / 2;

			if (arr[mid] == target){
				return mid;
			}
			else if (arr[mid] < target) {
				left = mid + 1;
			} else if(arr[mid] > target){
				right = mid - 1;
			}
		}
		return -1;
	}

	/// 재귀 호출을 이용하여 정렬된 배열에서 특정 타겟의 인덱스를 탐색합니다.
	///
	/// 이 방식은 분할 정복 패러다임을 따르며, 탐색 범위를 절반씩 좁혀가며 자기 자신을 호출합니다.
	/// 배열이 사전에 정렬되어 있어야 정확한 결과를 보장합니다.
	///
	/// @param arr    탐색 대상이 되는 정렬된 정수 배열
	/// @param target 찾고자 하는 대상 값
	/// @param left   탐색 범위의 시작 인덱스
	/// @param right  탐색 범위의 끝 인덱스
	/// @return       타겟의 인덱스. 존재하지 않을 경우 -1 반환
	/// @throws StackOverflowError 탐색 범위가 비정상적으로 커서 재귀 깊이가 깊어질 경우 발생할 수 있음
	/// (단, log2(N) 수준이므로 일반적인 배열 크기에서는 안전함)
	public static int recursive(int[] arr, int target, int left, int right) {
		// [Base Case] 탐색 범위가 역전되면 타겟이 배열에 없는 것임
		if (left > right) {
			return -1;
		}

		int mid = left + (right - left) / 2;

		if (arr[mid] == target) {
			return mid;
		} else if (arr[mid] < target) {
			return recursive(arr, target, mid + 1, right);
		} else {
			return recursive(arr, target, left, mid - 1);
		}
	}
}
