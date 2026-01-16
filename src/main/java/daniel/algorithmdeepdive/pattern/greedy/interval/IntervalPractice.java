package daniel.algorithmdeepdive.pattern.greedy.interval;

import java.util.Arrays;
import java.util.Comparator;

public class IntervalPractice {

	static class Interval {
		int start;
		int end;

		public Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return String.format("[%d, %d]", start, end);
		}
	}

	public static void main(String[] args) {
		testEraseOverlap();
		testMerge();
	}

	/// 겹치는 구간 제거 테스트
	static void testEraseOverlap() {
		Interval[] intervals = {
			new Interval(1, 2),
			new Interval(2, 3),
			new Interval(3, 4),
			new Interval(1, 3)
		};

		int result = eraseOverlapIntervals(intervals);
		System.out.println("제거해야 할 구간 개수: " + result);
	}

	/// 구간 병합 테스트
	static void testMerge() {
		Interval[] intervals = {
			new Interval(1, 3),
			new Interval(2, 6),
			new Interval(8, 10),
			new Interval(15, 18)
		};

		Interval[] merged = mergeIntervals(intervals);
		System.out.println("병합된 구간:");
		for (Interval interval : merged) {
			System.out.println(interval);
		}
	}

	/// 겹치는 구간 제거
	public static int eraseOverlapIntervals(Interval[] intervals) {
		if (intervals.length == 0) {
			return 0;
		}

		// 끝점 기준 오름차순 정렬
		Arrays.sort(intervals, Comparator.comparingInt(a -> a.end));

		int count = 1; // 첫번째 구간은 항상 선택
		int lastEnd = intervals[0].end;

		for (int i = 1; i < intervals.length; i++) {
			// 현재 구간의 시작이 마지막 구간의 끝 이후면 선택
			if (intervals[i].start >= lastEnd) {
				count++;
				lastEnd = intervals[i].end;
			}
		}

		return intervals.length - count;
	}

	/// 구간 병합
	public static Interval[] mergeIntervals(Interval[] intervals) {
		if (intervals.length == 0) {
			return new Interval[0];
		}

		// 시작점 기준 오름차순 정렬
		Arrays.sort(intervals, Comparator.comparingInt(a -> a.start));

		java.util.List<Interval> merged = new java.util.ArrayList<>();
		merged.add(intervals[0]);

		for (int i = 1; i < intervals.length; i++) {
			Interval last = merged.get(merged.size() - 1);
			Interval curr = intervals[i];

			// 겹치면 병합
			if (curr.start <= last.end) {
				last.end = Math.max(last.end, curr.end);
			} else {
				merged.add(curr);
			}
		}

		return merged.toArray(new Interval[0]);
	}
}
