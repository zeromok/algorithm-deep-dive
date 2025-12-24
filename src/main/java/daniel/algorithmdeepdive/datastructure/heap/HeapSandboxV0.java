package daniel.algorithmdeepdive.datastructure.heap;

import java.util.PriorityQueue;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HeapSandboxV0 {

	public static void main(String[] args) {
		basicHeap();
	}

	private static void basicHeap() {
		// 최소 힙 생성
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();

		// 데이터 삽입
		minHeap.offer(5);
		minHeap.offer(3);
		minHeap.offer(8);
		minHeap.offer(1);
		minHeap.offer(9);

		// peek(): 제거하지 않고 조회만
		log.info("현재 최솟 값: {}", minHeap.peek());
		log.info("힙 크기: {}", minHeap.size());

		// poll(): 순서대로 추출
		// 잘못된 방법: forEach 순회
		// 왜? 힙은 "부분 정렬"만 유지 (루트만 최솟값)
		// Iterator 는 내부 배열을 그대로 순회
		// 힙 속성: parent <= child (형제 간 순서 보장 x)
		for (Integer i : minHeap) {
			log.info("forEach: {}", i);
		}
		// 올바른 방법: poll() 순회
		// int count = 1;
		// while (!minHeap.isEmpty()) {
		// 	int value = minHeap.poll();
		// 	log.info("{} 번째 추출: {}", count, value);
		// 	count++;
		// }

		// peek(): 여러 번 호출해도 값 변화 없음
		log.info("첫 번째 peek(): {}", minHeap.peek());
		log.info("두 번째 peek(): {}", minHeap.peek());
		log.info("세 번째 peek(): {}", minHeap.peek());
	}
}
