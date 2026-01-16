package daniel.algorithmdeepdive.pattern.greedy.activity;

import java.util.Arrays;
import java.util.Comparator;

/// ## 활동 선택 문제
/// ### 언제 쓰는가?
/// - 겹치지 않는 최대 개수의 활동 선택 -> 그리디
/// ### 핵심 원리
/// - 종료 시간이 빠른 활동부터 선택
/// - 종료 시간 기준 정렬 후, 겹치지 않는 활동 선택
/// ### 왜 종료 시간이 빠른 것부터?
/// - 종료가 빠를수록 이후 선택 가능한 활동이 많아짐
/// - 지역 최적: "가장 빨리 끝나는 것" = 전역 최적
/// ### 시간 복잡도
/// - O(N log N): 정렬
/// - O(N): 선택 과정
/// - 총 O(N log N)
public class ActivitySelection {

	static class Activity {
		int start;
		int end;

		public Activity(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	/// 겹치지 않는 최대 개수의 활동 선택
	/// @param activities 활동 배열
	/// @return 선택된 활동의 개수
	public static int maxActivities(Activity[] activities) {
		if (activities.length == 0) {
			return 0;
		}

		// 종료 시간 기준 오름차순
		Arrays.sort(activities, Comparator.comparingInt(a -> a.end));

		int count = 1; // 첫번째 활동은 항상 선택
		int lastEnd = activities[0].end;

		for (int i = 1; i < activities.length; i++) {
			// 현재 활동의 시작 시간이 마지막 선택 활동의 종료 시간 이후면 선택
			if (activities[i].start >= lastEnd) {
				count++;
				lastEnd = activities[i].end;
			}
		}
		return count;
	}
}
