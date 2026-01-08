package daniel.algorithmdeepdive.pattern.search.twopointer.fastslow;

/// ## Fast & Slow 포인터
/// ### 핵심 원리
/// - slow: 1칸씩
/// - fast: 2칸씩
/// - 속도 차이로 특수한 위치 찾기
/// ### 시간 복잡도: O(N)
/// ### 주요 활용
/// - 사이클 감지
/// - 중간 노드
/// - 배열 중복 (사이클 응용)
public class FastSlow {

	public static class ListNode {
		int val;
		ListNode next;

		public ListNode(int val) {
			this.val = val;
			this.next = null;
		}
	}

	/// 사이클 존재 여부
	/// - slow 와 fast 가 만나면 사이클 존재
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

	/// 중간 노드 찾기
	/// - fast 가 끝에 도달하면 slow 는 중간
	/// - 짝수 개일 때: 두 번째 중간 반환
	public static ListNode findMiddle(ListNode head) {
		if (head == null) {
			return null;
		}

		ListNode slow = head;
		ListNode fast = head;

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		return slow;
	}

	/// 배열 중복
	/// 배열에서 중복 원소 찾기
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
