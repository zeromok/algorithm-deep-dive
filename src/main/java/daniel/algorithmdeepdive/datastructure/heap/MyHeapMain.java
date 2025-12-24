package daniel.algorithmdeepdive.datastructure.heap;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyHeapMain {
	public static void main(String[] args) {
		MyHeapV0 heap = new MyHeapV0(10, true);

		heap.print();
		heap.offer(5);
		heap.offer(1);
		heap.offer(4);
		heap.offer(3);
		heap.print();

		log.info("첫 번째 peek(): {}, 사이즈: {}", heap.peek(), heap.size());
		log.info("두 번째 peek(): {}, 사이즈: {}", heap.peek(), heap.size());
		log.info("세 번째 peek(): {}, 사이즈: {}", heap.peek(), heap.size());

		while (heap.size() != 0) {
			log.info("poll() 순회: {}", heap.poll());
		}
	}
}

