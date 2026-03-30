package daniel.algorithmdeepdive.pattern.sort.noncomparison;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BucketSort {

	public static void main(String[] args) {
		int[] A = {85, 68, 65, 100, 88, 61, 82, 95};
		bucketSort(A);

		System.out.println(Arrays.toString(A));
	}

	// 1. 특징: 데이터를 여러 구역(Bucket)으로 나누어 담은 뒤 각각 정렬함
	// 2. 장점: 데이터가 특정 범위 내에 아주 골고루 분포되어 있을 때 O(n)에 가까운 속도를 냄
	// 3. 주의사항: 한 버킷에 데이터가 쏠리면(최악의 경우) 성능이 O(n^2)까지 떨어질 수 있음
	// 4. 활용: 주로 0.0 ~ 1.0 사이의 실수나 성적 분포처럼 범위가 명확한 데이터에 유리함
	private static void bucketSort(int[] a) {
		int n = a.length;
		if (n <= 0) return;

		// 1. 최솟값과 최댓값 찾기
		int min = a[0];
		int max = a[0];
		for (int val : a) {
			if (val < min) min = val;
			if (val > max) max = val;
		}

		// 2. INTERVAL 계산 (공식: (MAX - MIN + 1) / n)
		// 정수 나눗셈에서 0이 되는 것을 방지하기 위해 최소 1로 설정합니다.
		int interval = (int) Math.ceil((double) (max - min + 1) / n);

		// 3. 연결 리스트(LinkedList)로 버킷 배열 생성
		LinkedList<Integer>[] buckets = new LinkedList[n];
		for (int i = 0; i < n; i++) {
			buckets[i] = new LinkedList<>();
		}

		// 4. 데이터를 계산된 인덱스에 따라 버킷에 삽입
		// 공식: (A[i] - MIN) / INTERVAL
		for (int i = 0; i < n; i++) {
			int bucketIdx = (a[i] - min) / interval;

			// 계산된 인덱스가 n을 벗어나지 않도록 방어 코드 추가 (최댓값 처리)
			if (bucketIdx >= n) bucketIdx = n - 1;

			buckets[bucketIdx].add(a[i]);
		}

		// 5. 각 버킷 내부 정렬 후 원본 배열에 순서대로 복사
		int index = 0;
		for (int i = 0; i < n; i++) {
			// LinkedList도 Collections.sort() 사용이 가능합니다.
			Collections.sort(buckets[i]);
			for (int val : buckets[i]) {
				a[index++] = val;
			}
		}
	}
}