package daniel.algorithmdeepdive.pattern.search.graph.shortestpath.practice;

import java.util.ArrayList;
import java.util.List;

/// Bellman-Ford 알고리즘 실행 흐름 관찰
/// - V-1번 반복하며 모든 간선 완화
/// - 음수 사이클 검사 과정
public class BellmanFordPractice {

	static class Edge {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}

	public static void main(String[] args) {

	}

	/// 정상 케이스: 양수 가중치만
	static void testNormalCase() {
		int n = 5;
		List<Edge> edges = new ArrayList<>();

		edges.add(new Edge(0, 1, 4));
		edges.add(new Edge(0, 2, 3));
		edges.add(new Edge(1, 3, 5));
		edges.add(new Edge(2, 1, 2));
		edges.add(new Edge(2, 3, 6));
		edges.add(new Edge(3, 4, 2));

		// TODO: trace 실행
	}

	/// 음수 가중치 포함 케이스
	static void testNegativeWeight() {
		int n = 4;
		List<Edge> edges = new ArrayList<>();

		edges.add(new Edge(0, 1, 5));
		edges.add(new Edge(0, 2, 3));
		edges.add(new Edge(1, 2, -2));  // 음수!
		edges.add(new Edge(2, 3, 4));
		edges.add(new Edge(1, 3, 6));

		// TODO: trace 실행
	}

	/// 음수 사이클 케이스
	static void testNegativeCycle() {
		int n = 3;
		List<Edge> edges = new ArrayList<>();

		edges.add(new Edge(0, 1, 1));
		edges.add(new Edge(1, 2, -3));
		edges.add(new Edge(2, 1, 1));  // 사이클: 1→2→1 = -2

		// TODO: trace 실행
	}
}
