package daniel.algorithmdeepdive.pattern.search.binary;

/// # BinarySearchV0 - 이진 탐색 기본 구현
///
/// ## 목적
/// 이진 탐색의 핵심 원리와 Lower/Upper Bound 패턴 이해
///
/// ## 핵심 개념
/// - 분할 정복 (Divide & Conquer): 매번 탐색 범위를 절반으로 축소
/// - 정렬 전제: 오름차순 정렬된 배열에서만 동작
/// - 불변식 (LoopV0 Invariant): `arr[left] < target <= arr[right]`
///
/// ## 알고리즘 흐름:
/// 1. 중간값 (`mid`) 계산
/// 2. `target` 과 비교하여 탐색 방향 결정
/// 3. 불필요한 절반 제거 (Pruning)
/// 4. `left == right` 될 때까지 반복
///
/// ## 시간 복잡도: O(log N)
/// - 매 반복마다 탐색 범위가 절반으로 감소
/// - 백만 개 데이터도 약 20회 비교로 탐색 완료
///
/// ## 공간 복잡도: O(1) (반복문) / O(log N) (재귀)
///
/// ## 장점:
/// - 압도적인 속도 (대용량 데이터에서 빛남)
/// - 안정적인 성능 (Best/Worst Case 모두 O(log N))
/// - 메모리 효율적 (추가 공간 건의 불필요)
/// - 범위 쿼리 지원 (Lower/Upper Bound)
///
/// ## 단점:
/// - 정렬 필수 (정렬 비용 O(N log N))
/// - Random Access 필수 (LinkedList 불가)
/// - 삽입/삭제 비용 높음 (O(N))
/// - 작은 데이터에서는 순차 탐색보다 느릴 수 있음
///
/// ## 실무 응용:
/// - DB 인덱스 Range Query (B-Tree의 핵심 원리)
/// - 정렬된 로그에서 특정 시간대 검색
/// - `Collections.binarySearch()` 내부 구현
/// - Git bisect (버그 발생 커밋 찾기)
public class BinarySearchV0 {

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
		return upperBound(arr, target) - lowerBound(arr, target);
	}
}
