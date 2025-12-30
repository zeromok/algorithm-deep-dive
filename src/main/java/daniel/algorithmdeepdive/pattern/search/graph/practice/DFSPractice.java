package daniel.algorithmdeepdive.pattern.search.graph.practice;

import java.util.*;

/// ## DFS 실행 흐름 관찰
/// ### 실험 목적
/// - 재귀 호출 깊이와 백트래킹 과정 관찰
/// - 한 경로를 끝까지 탐색하는 과정 확인
/// - DFS vs BFS 차이 이해
public class DFSPractice {
	private static int depth = 0;

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

		boolean[] visited = new boolean[vertices];
		traceDFS(graph, 0, visited);
	}

	private static void traceDFS(List<List<Integer>> graph, int v, boolean[] visited) {
		String indent = "  ".repeat(depth);
		System.out.println(indent + "→ " + v + " 방문 (깊이: " + depth + ")");

		visited[v] = true;
		depth++;

		for (int neighbor : graph.get(v)) {
			if (!visited[neighbor]) {
				traceDFS(graph, neighbor, visited);
			} else {
				System.out.println(indent + "  " + neighbor + " 이미 방문함 (스킵)");
			}
		}

		depth--;
		System.out.println(indent + "← " + v + " 백트래킹 (깊이: " + depth + ")");
	}
}

