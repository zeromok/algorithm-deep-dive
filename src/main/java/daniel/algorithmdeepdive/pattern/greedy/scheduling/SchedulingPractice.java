package daniel.algorithmdeepdive.pattern.greedy.scheduling;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class SchedulingPractice {

	static class Meeting {
		int start;
		int end;

		public Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return String.format("[%d, %d]", start, end);
		}
	}

	static class Job {
		int deadline;
		int profit;

		public Job(int deadline, int profit) {
			this.deadline = deadline;
			this.profit = profit;
		}
	}

	public static void main(String[] args) {
		testMaxMeetings();
		testMinMeetingRooms();
	}

	/// 최대 회의 개수 테스트
	static void testMaxMeetings() {
		Meeting[] meetings = {
			new Meeting(1, 4),
			new Meeting(3, 5),
			new Meeting(0, 6),
			new Meeting(5, 7),
			new Meeting(8, 9),
			new Meeting(5, 9)
		};

		int result = maxMeetings(meetings);
		System.out.println("최대 회의 개수: " + result);
	}

	/// 최소 회의실 개수 테스트
	static void testMinMeetingRooms() {
		Meeting[] meetings = {
			new Meeting(0, 30),
			new Meeting(5, 10),
			new Meeting(15, 20)
		};

		int result = minMeetingRooms(meetings);
		System.out.println("최소 회의실 개수: " + result);
	}

	/// 최대 회의 개수
	public static int maxMeetings(Meeting[] meetings) {
		if (meetings.length == 0) {
			return 0;
		}

		// 종료 시간 기준 오름차순 정렬
		Arrays.sort(meetings, Comparator.comparingInt(m -> m.end));

		int count = 1;
		int lastEnd = meetings[0].end;

		for (int i = 1; i < meetings.length; i++) {
			if (meetings[i].start >= lastEnd) {
				count++;
				lastEnd = meetings[i].end;
			}
		}

		return count;
	}

	/// 최소 회의실 개수
	public static int minMeetingRooms(Meeting[] meetings) {
		if (meetings.length == 0) {
			return 0;
		}

		// 시작 시간 기준 정렬
		Arrays.sort(meetings, Comparator.comparingInt(m -> m.start));

		// 종료 시간을 저장하는 우선순위 큐 (최소 힙)
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.offer(meetings[0].end);

		for (int i = 1; i < meetings.length; i++) {
			// 현재 회의 시작 시간이 가장 빨리 끝나는 회의실의 종료 시간 이후면
			// 같은 회의실 사용 가능
			if (meetings[i].start >= pq.peek()) {
				pq.poll();
			}
			pq.offer(meetings[i].end);
		}

		return pq.size();
	}
}
