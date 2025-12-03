package daniel.algorithmdeepdive.mathematics;

/// # 자릿수 분리 패턴과 응용
public class SeparationOfDigits {
	public static void main(String[] args) {

		int num = 1234;
		separationOfDigits(num);
		System.out.println("숫자 뒤집기: " + reverse(num));
		System.out.println("2진수 변환: " + toBase(num, 2));
		System.out.println("8진수 변환: " + toBase(num, 8));
		System.out.println("16진수 변환: " + toBase(num, 16));
		System.out.println("자릿수 합계: " + sumOfDigits(num));
		System.out.println("Digital Root: " + digitalRoot(num));
	}

	/// 기본 구현: 각 자릿수 출력
	private static void separationOfDigits(int num) {
		if (num == 0) {
			System.out.println("자릿수: 0");
			return;
		}

		System.out.print("자릿수들: ");
		while (num > 0) {
			System.out.print(num % 10);
			if (num >= 10) System.out.print(", ");
			num /= 10;
		}
		System.out.println();
	}

	/// 숫자뒤집기
	private static int reverse(int num) {
		int result = 0;
		while (num > 0) {
			result = result * 10 + num % 10;
			num /= 10;
		}
		return result;
	}

	/// 진법 변환
	private static String toBase(int num, int base) {
		if (num == 0) return "0";

		StringBuilder sb = new StringBuilder();
		while (num > 0) {
			int digit = num % base;
			// 16진법 대응: 10~15 → A~F
			if (digit < 10) {
				sb.append(digit);
			} else {
				sb.append((char)('A' + digit - 10));
			}
			num /= base;
		}
		return sb.reverse().toString();
	}

	/// 자릿수 합계
	private static int sumOfDigits(int num) {
		int sum = 0;
		while (num > 0) {
			sum += num % 10;
			num /= 10;
		}
		return sum;
	}

	/// 자릿수 합계 (한자리까지)
	private static int digitalRoot(int num) {
		while (num >= 10) {
			num = sumOfDigits(num);
		}
		return num;
	}
}
