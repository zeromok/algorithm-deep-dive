package daniel.algorithmdeepdive.mathematics;

import java.math.BigDecimal;

/// # 부동 소수점
/// ## 부동 소수점 이란?
/// 실수를 컴퓨터가 표현하는 방법.\
/// 컴퓨터는 유한한 비트로 무한한 실수를 표현해야 한다. 이게 모든 문제의 시작이다.
public class FloatingPoint {
	public static void main(String[] args) {

		// 정수
		int a = 3;
		int b = 4;
		System.out.println("a + b: " + a + b); // 7 (항상 정확)

		// 실수
		double x = 0.1;
		double y = 0.2;
		System.out.println("x + y: " + (x + y)); // 0.30000000000000004
		System.out.println("x + y == 0.3: " + (x + y == 0.3)); // false


		// 실습
		double d = 0.1;
		// 1. printf - 반올림됨
		System.out.printf("%.30f%n", d);

		// 2. BigDecimal - 실제 값
		System.out.println(new BigDecimal(d));

		// 3. 2진수 비트 표현
		long bits = Double.doubleToRawLongBits(d);
		String binary = String.format("%64s", Long.toBinaryString(bits)).replace(' ', '0');
		System.out.println(binary);

		// 4. 16진수 표현 (간결함)
		System.out.println(Double.toHexString(d));

		// 5. 실제로 0.1과 다름을 증명
		System.out.println(d == 0.1);  // true (같은 방식으로 저장되어서)
		BigDecimal exact = new BigDecimal("0.1");  // 정확한 0.1
		BigDecimal actual = new BigDecimal(d);     // 실제 저장된 값
		System.out.println(exact.equals(actual));  // false! ← 실제로는 다름

		// 6. 차이 계산
		System.out.println(actual.subtract(exact));
	}
}
