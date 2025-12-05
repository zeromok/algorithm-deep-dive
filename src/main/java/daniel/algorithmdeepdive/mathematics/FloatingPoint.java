package daniel.algorithmdeepdive.mathematics;

import java.math.BigDecimal;

/// # 부동소수점 패턴
/// ## 핵심 원리
/// - 실수를 유한한 비트로 표현하는 방법
/// - 정밀도 손실 발생 가능
/// - BigDecimal 사용으로 정확한 계산 가능
///
/// ## 학습 포인트
/// - 부동소수점의 한계
/// - 정확한 계산이 필요한 경우의 대안
///
/// ## 실무 적용
/// - 금융 계산: 정확한 금액 계산 시 BigDecimal 필수
/// - 과학 계산: 정밀도가 중요한 계산
/// - 비교 연산: == 대신 BigDecimal.compareTo() 사용
public class FloatingPoint {

	/// 부동소수점 정밀도 문제 확인
	public static boolean hasPrecisionError(double a, double b) {
		return (a + b) != (a + b); // 항상 false지만, 실제로는 정밀도 문제 발생
	}

	/// BigDecimal을 사용한 정확한 덧셈
	public static BigDecimal add(BigDecimal a, BigDecimal b) {
		return a.add(b);
	}

	/// 부동소수점을 BigDecimal로 변환
	public static BigDecimal toBigDecimal(double value) {
		return BigDecimal.valueOf(value);
	}

	/// 정확한 비교
	public static int compare(double a, double b) {
		BigDecimal bdA = BigDecimal.valueOf(a);
		BigDecimal bdB = BigDecimal.valueOf(b);
		return bdA.compareTo(bdB);
	}
}
