package daniel.algorithmdeepdive.pattern.sort.insertionsort;

/// # 삽입정렬 V0 - 기본 구현
/// ## 언제 쓰는가?
/// - "작은 배열, 거의 정렬된 배열" → InsertionSort
///
/// ## 알고리즘 원리
/// 배열을 "정렬된 영역"과 "미정렬 영역"으로 나누고, 미정렬 영역의 첫 번째 원소를 정렬된 영역의 올바른 위치에 삽입하는 과정을 반복
///
/// ## 시간 복잡도
/// - 최선: O(N) - 이미 정렬된 경우
/// - 평균: O(N^2)
/// - 최악: O(N^2) - 역순 정렬된 경우
///
/// ## 공간 복잡도
/// O(1) - In-place 정렬
///
/// ## 안정성
/// 안정 정렬(Stable) - 같은 값의 상대적 순서 유지
public class InsertionSortV0 {

	/// @param arr 정렬할 정수 배열 (원본 배열이 수정됨)
	public void insertionSort(int[] arr) {
		// i = 1 부터 시작: 첫 번째 원소(i = 0)는 이미 정렬되어 있다고 가정
		for (int i = 1; i < arr.length; i++) {
			int key = arr[i]; // 현재 정렬되지 않은 영역의 첫 번째 원소 (삽입할 대상)
			int j = i - 1; // 정렬된 영역의 마지막 인덱스부터 역순으로 탐색

			// 조건1: j >= 0 (배열 범위 체크)
			// 조건2: arr[j] > key (key 보다 큰 원소 찾기)
			// 두 조건을 만족하는 동안 원소들을 오른쪽으로 한 칸씩 이동
			while (j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j]; // 큰 원소를 오른쪽으로 이동
				j = j - 1; // 왼쪽으로 계속 탐색
			}

			// while 종료 시점: arr[j] <= key 이거나 j == -1
			// j + 1 위치가 key가 들어갈 올바른 자리
			arr[j + 1] = key;
		}
	}
}
