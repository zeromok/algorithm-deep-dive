package daniel.algorithmdeepdive.mathematics;

/// # 최대 공약수와 최소 공배수
/// ## 최대 공약수 (GCD)
/// 두 수가 공통으로 가지고 있는 약수 중 가장 큰수
///
/// ## 최소 공배수 (LCM)
/// 두 수를 곱한 값을 최대 공약수로 나눈 수
public class GCDAndLCM {

	public static void main(String[] args) {
		basicGCD(24, 18);
		int basicLCM = basicLCM(24, 18);
		System.out.println("basicLCM: " + basicLCM);
	}

	/// 최대 공약수 (일반)
	private static void basicGCD(int a, int b) {
		long s = System.currentTimeMillis();
		int gcd = 0;
		for (int i = 1; i <= Math.max(a, b); i++) {
			if (a % i == 0 && b % i == 0) {
				gcd = i;
			}
		}
		long e = System.currentTimeMillis();
		System.out.println("basicGCD: " + gcd + " " + (e - s) + " ms");
	}

	/// 최대 공약수 (유클리드) - 반복문 버전
	private static int euclideanIterative(int a, int b) {
		while (b != 0) {
			int temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}

	/// 최대 공약수 (유클리드) - 재귀 버전
	private static int euclidean(int a, int b) {
		if (b == 0) {
			return a;
		}

		return euclidean(b, a % b);
	}

	/// 최소 공배수
	private static int basicLCM(int a, int b) {
		return (a * b) / euclidean(a, b);
	}
}
