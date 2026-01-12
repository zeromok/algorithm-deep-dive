package daniel.algorithmdeepdive.pattern.search.graph.traversal.practice;

import java.util.ArrayList;
import java.util.List;

/// BFS 실행 흐름 관찰
/// - Queue에 넣는 순서와 꺼내는 순서
/// - 레벨별 탐색 과정
public class BFSPractice {

	public static void main(String[] args) {
		// 예제 그래프 생성
		List<List<Integer>> graph = createGraph();

		// TODO: BFS 실행 흐름 trace
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

	/// BFS 실행 흐름을 단계별로 출력
	/// - 각 레벨별로 방문하는 정점
	/// - Queue 상태 변화
	/// - "최단 거리" 보장 과정
	static void traceBFS(List<List<Integer>> graph, int start) {
		// TODO: 구현
	}
}
