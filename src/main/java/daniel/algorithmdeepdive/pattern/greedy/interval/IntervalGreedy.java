package daniel.algorithmdeepdive.pattern.greedy.interval;

import java.util.Arrays;
import java.util.Comparator;

/// ## 구간 문제
/// ### 언제 쓰는가?
/// - "겹치는 구간 제거", "구간 병합", "구간 스케일링" -> 그리디
/// ### 핵심 원리
/// - 구간의 끝점 기준 정렬
/// - 끝점이 빠른 구간부터 처리
/// ### 주요 문제 유형
/// - 겹치는 구간 제거: 최소 개수 제거로 모든 구간이 겹치지 않게
/// - 구간 병합: 겹치는 구간들을 하나로 합치기
/// - 구간 스케줄링: 최대 개수의 겹치지 않는 구간 선택
/// ### 시간 복잡도: O(N log N) (정렬)
public class IntervalGreedy {

	static class Interval {
		int start;
		int end;

		public Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	/// 겹치는 구간 제거 (최소 개수 제거)
	/// @param intervals 구간 배열
	/// @return 제거해야 할 최소 구간 개수
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
	/// @param intervals 구간 배열
	/// @return 병합된 구간 배열
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

	/// 최대 개수의 겹치지 않는 구간 선택
	/// @param intervals 구간 배열
	/// @return 선택 가능한 최대 구간 개수
	public static int maxNonOverlappingIntervals(Interval[] intervals) {
		if (intervals.length == 0) {
			return 0;
		}

		// 끝점 기준 오름차순 정렬
		Arrays.sort(intervals, Comparator.comparingInt(a -> a.end));

		int count = 1;
		int lastEnd = intervals[0].end;

		for (int i = 1; i < intervals.length; i++) {
			if (intervals[i].start >= lastEnd) {
				count++;
				lastEnd = intervals[i].end;
			}
		}

		return count;
	}
}
