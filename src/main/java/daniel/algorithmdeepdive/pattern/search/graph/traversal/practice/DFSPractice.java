package daniel.algorithmdeepdive.pattern.search.graph.traversal.practice;

import java.util.ArrayList;
import java.util.List;

/// DFS 실행 흐름 관찰
/// - 재귀 호출 깊이와 백트래킹 과정
/// - 한 경로를 끝까지 탐색하는 과정
public class DFSPractice {

	public static void main(String[] args) {
		// 예제 그래프 생성
		List<List<Integer>> graph = createGraph();

		// TODO: DFS 실행 흐름 trace
	}

	/// 예제 그래프 생성
	///     0
	///   /   \
	///  1     2
	///   \   /
	///     3
	static List<List<Integer>> createGraph() {
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

		return graph;
	}

	/// DFS 실행 흐름을 단계별로 출력
	/// - 재귀 깊이 표시
	/// - 백트래킹 시점
	/// - 방문 순서
	static void traceDFS(List<List<Integer>> graph, int start, boolean[] visited) {
		// TODO: 구현
	}
}
