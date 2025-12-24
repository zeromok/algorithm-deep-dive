package daniel.algorithmdeepdive.datastructure.heap;

/// # 사용자 정의 힙V0 - 기본 구현
/// ## 핵심 원리
/// - 완전 이진 트리를 배열로 구현
/// - 힙 속성: 부모 >= 자식 (최대힙) 또는 부모 <= 자식 (최소힙)
/// - Heapify Up/Down으로 힙 속성 유지
///
/// ## 시간 복잡도
/// - 삽입: O(log N)
/// - 삭제: O(log N)
/// - 조회: O(1)
///
/// ## 공간 복잡도: O(N)
///
/// ## 학습 포인트
/// - 배열 기반 힙 구현
/// - Heapify Up/Down 동작 원리
/// - 최대힙/최소힙 전환
///
/// ## 실무 적용
/// - 우선순위 큐: 작업 스케줄링, 이벤트 처리
/// - Top-K 문제: 최대/최소 K개 요소 찾기
/// - 힙 정렬: O(N log N) 정렬 알고리즘
/// - 다익스트라 알고리즘: 최단 경로 탐색
public class MyHeapV0 {

	private final int[] heap;
	private int size = 0;
	private final int capacity;
	private final boolean isMaxHeap; // true: 최대힙, false: 최소힙

	// 생성자
	// : capacity 를 매개변수로 받아 해당 크기에 힙(배열) 생성 후 반환
	// : isMaxHeap 도 매개변수로 받아 최대힙/최소힙 어떤 타입으로 구현할 껀지?
	public MyHeapV0(int capacity, boolean isMaxHeap) {
		this.heap = new int[capacity];
		this.capacity = capacity;
		this.isMaxHeap = isMaxHeap;
	}

	// offer(): 삽입
	// : 꼬리에 요소를 삽입한다.
	// : isMaxHeap 값에 따라 Heapify Up 수행
	public void offer(int item) {
		heap[size] = item; // 마지막에 삽입
		int current = size;
		size++;

		// Heapify Up
		while (current > 0) {
			int parent = (current - 1) / 2;

			// isMaxHeap에 따라 비교 조건 변경
			boolean shouldSwap;
			if (isMaxHeap) {
				shouldSwap = heap[current] > heap[parent];  // 최대힙: 자식이 더 크면 교환
			} else {
				shouldSwap = heap[current] < heap[parent];  // 최소힙: 자식이 더 작으면 교환
			}
			if (!shouldSwap) break; // 힙 조건 만족

			swap(current, parent); // 교환
			current = parent; // 부모 위치로 이동
		}
	}

	private void swap(int i, int j) {
		int temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}

	// poll(): 최댓값/최솟값 제거
	public int poll() {
		int result = heap[0]; // 루트 저장
		heap[0] = heap[size - 1]; // 마지막 원소를 루트로
		size--;

		// Heapify Down
		int current = 0;
		while (true) {
			int left = 2 * current + 1; // 자식(왼쪽)
			int right = 2 * current + 2; // 자식(오른쪽)
			int target = current;

			// 왼쪽 자식과 비교
			if (left < size) {
				if (isMaxHeap) {
					if (heap[left] > heap[target]) target = left;
				} else {
					if (heap[left] < heap[target]) target = left;
				}
			}

			// 오른쪽 자식과 비교
			if (right < size) {  // right로 체크!
				if (isMaxHeap) {
					if (heap[right] > heap[target]) target = right;
				} else {
					if (heap[right] < heap[target]) target = right;
				}
			}

			if (target == current) break; // 힙 속성 만족

			swap(current, target);
			current = target;
		}
		return result;
	}


	// peek(): 조회
	public int peek() {
		if (size == 0) {
			throw new IllegalStateException("힙이 비어있습니다.");
		}
		return heap[0];
	}

	// size(): 사이즈 반환
	public int size() {
		return size;
	}


	// (선택) 시각화 메서드
	public void print() {
		if (size == 0) {
			System.out.println("(empty)");
			return;
		}

		System.out.println("\n=== Heap Tree Structure ===");
		printTreeHelper(0, "", true);
		System.out.println();
	}

	private void printTreeHelper(int index, String prefix, boolean isTail) {
		if (index >= size) return;

		System.out.println(prefix + (isTail ? "└── " : "├── ") + heap[index]);

		int left = 2 * index + 1;
		int right = 2 * index + 2;

		if (left < size) {
			printTreeHelper(left, prefix + (isTail ? "    " : "│   "), right >= size);
		}
		if (right < size) {
			printTreeHelper(right, prefix + (isTail ? "    " : "│   "), true);
		}
	}
}
