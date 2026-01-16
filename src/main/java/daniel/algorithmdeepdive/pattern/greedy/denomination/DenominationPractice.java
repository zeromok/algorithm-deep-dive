package daniel.algorithmdeepdive.pattern.greedy.denomination;

public class DenominationPractice {

	public static void main(String[] args) {
		System.out.printf("target=%d, large=%d, small=%d → result=%d%n", 18, 5, 3, denomination(18, 5, 3));
		System.out.printf("target=%d, large=%d, small=%d → result=%d%n", 20, 5, 3, denomination(20, 5, 3));
		System.out.printf("target=%d, large=%d, small=%d → result=%d%n", 7, 5, 3, denomination(7, 5, 3));
	}

	public static int denomination(int target, int large, int small) {
		if (target % large == 0) {
			return target / large;
		}

		for (int useLarge = target / large; useLarge >= 0; useLarge--) {
			int remaining = target - (useLarge * large);

			if (remaining % small == 0) {
				int useSmall = remaining / small;
				return useLarge + useSmall;
			}
		}
		return -1;
	}
}
