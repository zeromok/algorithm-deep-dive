package daniel.algorithmdeepdive.pattern.search.twopointer.fastslow;

/// ## Fast & Slow 포인터 - 사이클 감지
/// ### 핵심 원리 (Floyd's Algorithm)
/// - 연결 리스트나 배열에서 두 포인터의 이동 속도를 다르게 설정하여 특정 상태를 찾아내는 기법
/// - slow: 1칸씩 이동
/// - fast: 2칸씩 이동
/// - 사이클 있으면 반드시 만남
/// ### 시간 복잡도
/// - O(N): 선형 시간
/// ### 왜 만나는가?
/// - 사이클 내에서 fast 가 slow 를 따라잡는 속도: 1칸/step
/// - 거리가 N 이면 N step 후 만남
/// - 사이클 길이와 무관하게 선형 시간
/// ### 어떤 경우에 사용하는가?
/// - 연결 리스트의 순환 탐지
/// - 연결 리스트의 중간 지점 찾기
/// - 순환의 시작점 찾기
public class CycleDetection {

	/// 링크드리스트 노드
	public static class ListNode {
		int val;
		ListNode next;

		public ListNode(int val) {
			this.val = val;
			this.next = null;
		}
	}

	/// 사이클 존재 여부
	/// @param head 링크드리스트 헤드
	/// @return 사이클 존재 여부
	public static boolean hasCycle(ListNode head) {
		if (head == null || head.next == null) {
			return false;
		}

		ListNode slow = head;
		ListNode fast = head;

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;

			if (slow == fast) {
				return true;
			}
		}
		return false;
	}

	/// 사이클 시작점 찾기
	/// @param head 링크드리스트 헤드
	/// @return 사이클 시작 노드, 없으면 null
	public static ListNode findCycleStart(ListNode head) {
		if (head == null || head.next == null) {
			return null;
		}

		ListNode slow = head;
		ListNode fast = head;

		// 1. 만나는 지점 찾기
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;

			if (slow == fast) {
				break;
			}
		}

		// 사이클 없음
		if (fast == null || fast.next == null) {
			return null;
		}

		// 2. 시작점 찾기
		slow = head;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}

		return slow;
	}

	/// 배열에서 중복 원소 (Floyd's Algorithm)
	/// 전제: 1~n 범위, 중복 1개 존재
	/// @param arr 정수 배열
	/// @return 중복 원소
	public static int findDuplicate(int[] arr) {
		// 1. 사이클 감지
		int slow = arr[0];
		int fast = arr[0];

		do {
			slow = arr[slow];
			fast = arr[arr[fast]];
		} while (slow != fast);

		// 2. 시작점(중복 값) 찾기
		slow = arr[0];
		while (slow != fast) {
			slow = arr[slow];
			fast = arr[fast];
		}

		return slow;
	}
}
