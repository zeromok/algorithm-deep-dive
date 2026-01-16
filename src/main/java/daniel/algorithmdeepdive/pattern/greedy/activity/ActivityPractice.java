package daniel.algorithmdeepdive.pattern.greedy.activity;

import java.util.Arrays;
import java.util.Comparator;

public class ActivityPractice {

	static class Activity {
		int start;
		int end;

		public Activity(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return String.format("[%d, %d]", start, end);
		}
	}

	public static void main(String[] args) {
		Activity[] activities = {
			new Activity(1, 4),
			new Activity(3, 5),
			new Activity(0, 6),
			new Activity(5, 7),
			new Activity(8, 9),
			new Activity(5, 9)
		};

		int result = maxActivities(activities);
		System.out.println("최대 활동 개수: " + result);
	}

	public static int maxActivities(Activity[] activities) {
		if (activities.length == 0) {
			return 0;
		}

		Arrays.sort(activities, Comparator.comparingInt(a -> a.end));

		int count = 0;
		int lastEnd = -1;

		for (int i = 0; i < activities.length; i++) {
			if (activities[i].start >= lastEnd) {
				count++;
				lastEnd = activities[i].end;
			}
		}
		return count;
	}
}
