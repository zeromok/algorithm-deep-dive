package daniel.algorithmdeepdive.pattern.search.twopointer.fastslow.practice;

/// ## 사이클 감지 실행 흐름 관찰
/// ### 목표
/// - slow 와 fast 가 어떻게 만나는가?
/// - 왜 시작점을 찾을 수 있는가?
public class CyclePractice {

	public static class ListNode {
		int val;
		ListNode next;

		public ListNode(int val) {
			this.val = val;
			this.next = null;
		}
	}

	public static void main(String[] args) {
		meetingPoint();
		findStart();
	}

	/// 순환 탐지
	/// - 두 포인터가 만나면? -> 순환 있음
	/// - fast 가 끝(`null`)에 도달하면? -> 순환 없음
	static void meetingPoint() {
		// 0 → 1 → 2 → 3 → 4
		//         ↑       ↓
		//         ← ← ← ← 5
		ListNode head = new ListNode(0);
		head.next = new ListNode(1);
		head.next.next = new ListNode(2);
		head.next.next.next = new ListNode(3);
		head.next.next.next.next = new ListNode(4);
		head.next.next.next.next.next = new ListNode(5);
		head.next.next.next.next.next.next = head.next.next; // 사이클

		ListNode slow = head;
		ListNode fast = head;
		int step = 0;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			step++;

			System.out.printf("[%d단계] slow=%d, fast=%d",
				step, slow.val, fast.val);
			System.out.println();

			if (slow == fast) {
				break;
			}
		}
	}

	/// 순환의 시작점 찾기
	/// - 어느 노드에서 순환이 시작 되는가?
	static void findStart() {
		ListNode head = new ListNode(0);
		head.next = new ListNode(1);
		head.next.next = new ListNode(2);
		head.next.next.next = new ListNode(3);
		head.next.next.next.next = new ListNode(4);
		head.next.next.next.next.next = head.next.next;

		// 1. 만나는 지점 찾기
		ListNode slow = head;
		ListNode fast = head;

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				break;
			}
		}

		// 2. slow 를 다시 시작점으로 보냄
		slow = head;

		// 3. 두 포인터를 모두 한 칸씩 이동
		int setp = 0;
		while (slow != fast) { // 다시 만나는 지점이 바로 순환의 시작점
			setp++;
			slow = slow.next;
			fast = fast.next;
		}
		System.out.printf("\n시작점: 노드 %d\n\n", slow.val);
	}

	/// 중간 노드 찾기
	/// - 리스트의 개수: 홀수 -> 유일한 중간 노드 반환
	/// - 리스트의 개수: 짝수 -> 중간 노드 2개 -> 첫번째 중간 노드, 두번째 중간 노드
	static void findMiddleLode(ListNode head) {
		ListNode slow = head;
		ListNode fast = head; // 두번째 중간 노드 반환
		// ListNode fast = head; // 첫번째 중간 노드 반환

		// fast 가 끝에 도달할 때까지 반복
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next; // 두번째 중간 노드 반환
			// fast = fast.next; // 첫번째 중간 노드 반환
		}

		// fast 가 끝에 도착했을 때, slow 는 정확히 중간에 위치함
		System.out.println("중간 노드: " + slow);
	}
}
