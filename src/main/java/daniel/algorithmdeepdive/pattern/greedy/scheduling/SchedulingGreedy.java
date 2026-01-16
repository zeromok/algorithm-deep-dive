package daniel.algorithmdeepdive.pattern.greedy.scheduling;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/// ## 스케줄링 문제 (Scheduling Problems)
/// ### 언제 쓰는가?
/// - "회의실 배정", "작업 스케줄링", "데드라인 문제" → 그리디
/// ### 핵심 원리
/// - 우선순위 기준 정렬 후 처리
/// - 종료 시간, 데드라인, 이익 등 기준에 따라 정렬
/// ### 주요 문제 유형
/// 1. 회의실 배정: 종료 시간 기준 정렬
/// 2. 작업 스케줄링: 데드라인 또는 이익 기준
/// 3. 최소 회의실: 우선순위 큐 활용
/// ### 시간 복잡도: O(N log N)
public class SchedulingGreedy {
	static class Meeting {
		int start;
		int end;

		public Meeting(int start, int end) {
			this.start = start;
			this.end = end;
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

	/// 회의실 배정 (최대 개수 회의)
	/// @param meetings 회의 배열
	/// @return 배정 가능한 최대 회의 개수
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
	/// @param meetings 회의 배열
	/// @return 필요한 최소 회의실 개수
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

	/// 작업 스케줄링 (최대 이익)
	/// @param jobs 작업 배열
	/// @return 최대 이익
	public static int maxProfit(Job[] jobs) {
		// 이익 기준 내림차순 정렬
		Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

		// 데드라인 최댓값 찾기
		int maxDeadline = 0;
		for (Job job : jobs) {
			maxDeadline = Math.max(maxDeadline, job.deadline);
		}

		// 슬롯 배열 (사용 가능 여부)
		boolean[] slot = new boolean[maxDeadline + 1];
		int profit = 0;

		for (Job job : jobs) {
			// 데드라인부터 역순으로 빈 슬롯 찾기
			for (int j = Math.min(job.deadline, maxDeadline); j >= 1; j--) {
				if (!slot[j]) {
					slot[j] = true;
					profit += job.profit;
					break;
				}
			}
		}

		return profit;
	}
}
