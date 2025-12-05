package daniel.algorithmdeepdive.mathematics;

/// # 자릿수 분리 패턴
/// ## 핵심 원리
/// - 숫자를 자릿수별로 분리
/// - 모듈로 연산(%)과 나눗셈(/) 활용
///
/// ## 학습 포인트
/// - 자릿수 분리 기법
/// - 진법 변환
/// - 숫자 뒤집기
///
/// ## 실무 적용
/// - 입력 검증: 숫자 형식 검증
/// - 숫자 포맷팅: 천 단위 구분자 추가
/// - 암호화 알고리즘: 숫자 변환 기법
public class SeparationOfDigits {
	/// 자릿수 합계
	public static int sumOfDigits(int num) {
		int sum = 0;
		while (num > 0) {
			sum += num % 10;
			num /= 10;
		}
		return sum;
	}

	/// 숫자 뒤집기
	public static int reverse(int num) {
		int result = 0;
		while (num > 0) {
			result = result * 10 + num % 10;
			num /= 10;
		}
		return result;
	}

	/// 진법 변환
	public static String toBase(int num, int base) {
		if (num == 0) return "0";

		StringBuilder sb = new StringBuilder();
		while (num > 0) {
			int digit = num % base;
			if (digit < 10) {
				sb.append(digit);
			} else {
				sb.append((char)('A' + digit - 10));
			}
			num /= base;
		}
		return sb.reverse().toString();
	}

	/// Digital Root (자릿수 합을 한 자리까지)
	public static int digitalRoot(int num) {
		while (num >= 10) {
			num = sumOfDigits(num);
		}
		return num;
	}
}
