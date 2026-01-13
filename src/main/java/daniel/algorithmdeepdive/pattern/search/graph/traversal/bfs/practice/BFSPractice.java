package daniel.algorithmdeepdive.pattern.search.graph.traversal.bfs.practice;

import java.util.ArrayList;
import java.util.List;

/// ## BFS 실행 흐름 관찰
/// ### 목표
/// - Queue에 넣는 순서와 꺼내는 순서
/// - 레벨별 탐색 과정
/// - "최단 거리" 보장 과정
public class BFSPractice {

	public static void main(String[] args) {
		List<List<Integer>> graph = createGraph();
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

	/// 스택

	/// 재귀

	/// 모든 그래프 탐색

	/// 경로 추적
}