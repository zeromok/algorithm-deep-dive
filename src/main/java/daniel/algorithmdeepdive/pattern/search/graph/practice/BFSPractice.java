package daniel.algorithmdeepdive.pattern.search.graph.practice;

import java.util.*;

/// ## BFS 실행 흐름 관찰
/// ### 실험 목적
/// - Queue에 넣는 순서와 꺼내는 순서 관찰
/// - 레벨별 탐색 과정 확인
/// - "최단 거리"가 어떻게 보장되는지 이해
public class BFSPractice {

	public static void main(String[] args) {
		// 예제 그래프: 0-1-2-3 형태
		//     0
		//   /   \
		//  1     2
		//   \   /
		//     3
		int vertices = 4;
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < vertices; i++) {
			graph.add(new ArrayList<>());
		}
		graph.get(0).add(1);
		graph.get(0).add(2);
		graph.get(1).add(0);
		graph.get(1).add(3);
		graph.get(2).add(0);
		graph.get(2).add(3);
		graph.get(3).add(1);
		graph.get(3).add(2);

		System.out.println("그래프: 0-1-2-3 (0과 1,2 연결, 1,2와 3 연결)");
		System.out.println();

		traceBFS(graph, 0);
	}

	private static void traceBFS(List<List<Integer>> graph, int start) {
		boolean[] visited = new boolean[graph.size()];
		Queue<Integer> queue = new LinkedList<>();
		int level = 0;

		System.out.println("=== BFS (시작: " + start + ") ===");
		queue.offer(start);
		visited[start] = true;
		System.out.println("→ " + start + " 큐에 추가, 방문 처리");

		while (!queue.isEmpty()) {
			int size = queue.size();
			System.out.println();
			System.out.println("Level " + level + ": 큐 크기=" + size);

			for (int i = 0; i < size; i++) {
				int v = queue.poll();
				System.out.println("  큐에서 꺼냄: " + v);

				for (int neighbor : graph.get(v)) {
					if (!visited[neighbor]) {
						queue.offer(neighbor);
						visited[neighbor] = true;
						System.out.println("    → " + neighbor + " 큐에 추가, 방문 처리");
					} else {
						System.out.println("    → " + neighbor + " 이미 방문함 (스킵)");
					}
				}
			}
			level++;
		}
		System.out.println();
		System.out.println("→ 종료 (큐 비어있음)");
	}
}

