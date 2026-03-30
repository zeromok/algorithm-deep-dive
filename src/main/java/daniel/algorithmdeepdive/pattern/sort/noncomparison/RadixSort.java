package daniel.algorithmdeepdive.pattern.sort.noncomparison;

import java.util.Arrays;

public class RadixSort {

	public static void main(String[] args) {
		int[] A = {170, 45, 75, 90, 802, 24, 2, 66};
		radixSort(A);
	}

	// 1. 성능: O(d x (n + k) (d는 자릿수, k는 진법, 여기선 10)
	// 2. 장점: 계수 정렬의 메모리 낭비 문제를 자릿수별로 쪼개어 해결
	// 3. 특징: 반드시 안정 정렬을 내부 알고리즘으로 써야 정렬이 꼬이지 않는다.
	private static void radixSort(int[] a) {
		// 1. 최댓값을 찾아 자릿수(exp) 결정
		int max = a[0];
		for (int val : a) {
			if (val > max) {
				max = val;
			}
		}

		// 2. 자릿수를 1, 10, 100, ... 늘려가며 계수 정렬 수행
		for (int exp = 1; max / exp > 0; exp *= 10) {
			countingSortByDigit(a, exp);
		}

		System.out.println(Arrays.toString(a));
	}

	private static void countingSortByDigit(int[] a, int exp) {
		int n = a.length;
		int[] output = new int[n];
		int[] count = new int[10]; // 0~9까지의 숫자만 카운트

		// 해당 자릿수의 숫자 카운트
		for (int i = 0; i < n; i++) {
			count[(a[i] / exp) % 10]++;
		}

		// 누적합 계산
		for (int i = 1; i < 10; i++) {
			count[i] += count[i - 1];
		}

		// 뒤에서부터 결과 배열에 배치 (안정 정렬 유지)
		for (int i = n - 1; i >= 0; i--) {
			int digit = (a[i] / exp) % 10;
			output[count[digit] - 1] = a[i];
			count[digit]--;
		}

		// 원본 배열에 복사
		System.arraycopy(output, 0, a, 0, n);
	}
}
