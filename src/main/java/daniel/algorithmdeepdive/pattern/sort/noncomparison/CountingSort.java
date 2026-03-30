package daniel.algorithmdeepdive.pattern.sort.noncomparison;

import java.util.Arrays;

public class CountingSort {

	public static void main(String[] args) {
		int[] A = {7, 5, 9, 8, 4, 5, 7, 5};

		countingSort(A);
	}

	// 1. 성능: O(n + k) (n은 데이터 개수, 는 데이터의 최댓값)
	// 2. 장점: 비교 연산이 없어 조건만 맞으면 그 어떤 정렬보다 빠름
	// 3. 단점: 데이터의 최댓값(k)만큼의 배열 공간이 필요하므로 제자리 정렬 x
	// 4. 특징: 데이터가 양의 정수이고 범위가 좁을 때(예: 성적 0~100점) 가장 효율적임
	private static void countingSort(int[] a) {
		int n = a.length;
		int max = a[0];

		// 1. 최댓값 찾기
		for (int val : a) {
			if (val > max)
				max = val;
		}

		// 2. 카운트 배열 및 결과 배열 초기화
		int[] count = new int[max + 1];
		int[] result = new int[n];

		// 3. 각 숫자의 출현 회수 계산 (0번 인덱스부터 시작)
		for (int i = 0; i < n; i++) {
			count[a[i]]++;
		}

		// 4. 출현 횟수의 누적합 계산
		// 이 과정을 거치면 count[i] 는 숫자 i 가 들어갈 "마지막 위치"를 알려줌
		for (int i = 1; i <= max; i++) {
			count[i] += count[i - 1];
		}

		// 5. 뒤에서 부터 훓으며 결과 배열에 배치 (안정 정렬)
		// 뒤에서부터 하는 이유: 동일한 값들의 상대적 순서를 지켜주기 위해
		for (int i = n - 1; i >= 0; i--) {
			int val = a[i];
			int position = count[val] - 1; // 인덱스는 0부터 시작하므로 -1
			result[position] = val;
			count[val]--; // 다음 같은 숫자는 하나 앞 칸에 들어가야 하므로 감소
		}

		System.out.println(Arrays.toString(result));
	}
}
