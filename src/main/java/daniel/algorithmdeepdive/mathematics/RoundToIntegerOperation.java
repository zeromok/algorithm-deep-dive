package daniel.algorithmdeepdive.mathematics;

/// # 정수 연산 나눗셈 패턴
/// ## 핵심 원리
/// - 정수 연산만으로 반올림, 올림, 내림 구현
/// - 부동소수점 연산 없이 정확한 결과
///
/// ## 학습 포인트
/// - 정수 연산을 통한 반올림 기법
/// - 음수 처리 방법
///
/// ## 실무 적용
/// - 통계 계산: 평균값 반올림
/// - UI 표시 값 조정: 사용자에게 보여줄 값 포맷팅
/// - 금액 계산: 소수점 처리
public class RoundToIntegerOperation {
	/// 반올림: (a + b/2) / b
	public static long round(int a, int b) {
		if (a >= 0) {
			return (a + b / 2) / b;
		} else {
			return (a - b / 2) / b;
		}
	}

	/// 올림: (a + b - 1) / b
	public static long ceil(int a, int b) {
		if (a >= 0) {
			return (a + b - 1) / b;
		} else {
			return a / b;
		}
	}

	/// 내림: a / b
	public static long floor(int a, int b) {
		return a / b;
	}
}
