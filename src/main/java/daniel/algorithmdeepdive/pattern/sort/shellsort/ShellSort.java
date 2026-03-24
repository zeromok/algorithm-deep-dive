package daniel.algorithmdeepdive.pattern.sort.shellsort;

import java.util.Arrays;

public class ShellSort {
	public static void main(String[] args) {
		int[] arr = {30, 50, 10, 40, 75, 20, 45, 55, 25, 35, 65, 80, 15, 60, 5, 70};
		shellSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	// gap 을 계산하는 방식, 즉 사용하는 순열에 따라 성능이 달라진다.
	// 불안정 정렬이다.
	// 제자리 정렬 알고리즘이다.
	private static void shellSort(int[] arr) {
		int n = arr.length;

		// 1. 간격(gap)을 n/2에서 시작해 매번 반으로 줄임
		for (int gap = n / 2; gap > 0; gap /= 2) {

			// 2. 각 간격에 대해 삽입 정렬 수행
			for (int i = gap; i < n; i++) {
				int temp = arr[i];
				int j;

				// 3. 현재 요소(temp)를 gap 만큼 떨어진 앞쪽 요소들과 비교하며 뒤로 밀기
				for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
					arr[j] = arr[j - gap];
				}

				// 4. 적절한 위치에 temp 삽입
				arr[j] = temp;
			}
		}
	}
}
