package daniel.algorithmdeepdive.mathematics;

/// # 정수 연산 나눗셈
/// ## 목표: `a / b` 를 정수 연산만으로 반올림
public class RoundToIntegerOperation {
	public static void main(String[] args) {

		// case 1: 278 / 3 = 92.666... -> 93
		int sum1 = 278, n1 = 3;
		long case1 = (sum1 + n1 / 2) / n1;
		System.out.println("case 1: " + case1);

		// case 2: 24 / 10 = 2.4 -> 2
		int sum2 = 24, n2 = 10;
		long case2 = (sum2 + n2 / 2) / n2;
		System.out.println("case 2: " + case2);

		// case 3: 25 / 10 = 2.5 -> 3
		int sum3 = 25, n3 = 10;
		long case3 = (sum3 + n3 / 2) / n3;
		System.out.println("case 3: " + case3);

		// 음수 처리
		long case4 = roundedDivide(-25, 10);
		System.out.println("case 4: " + case4);
	}


	private static long roundedDivide(int sum, int n) {
		if (sum >= 0) {
			return (sum + n / 2) / n;
		} else {
			return (sum - n / 2) / n;
		}
	}
}
