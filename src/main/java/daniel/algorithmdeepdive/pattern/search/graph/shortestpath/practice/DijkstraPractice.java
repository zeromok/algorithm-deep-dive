package daniel.algorithmdeepdive.pattern.search.graph.shortestpath.practice;

import java.util.ArrayList;
import java.util.List;

public class DijkstraPractice {

	static class Edge {
		int to;
		int dist;

		public Edge(int to, int dist) {
			this.to = to;
			this.dist = dist;
		}
	}

	public static void main(String[] args) {

	}

	/// 예제 그래프 생성
	static List<Edge>[] createGraph() {
		List<Edge>[] graph = new ArrayList[4];
		for (int i = 0; i < 4; i++) {
			graph[i] = new ArrayList<>();
		}

		graph[0].add(new Edge(1, 4));
		graph[0].add(new Edge(2, 1));
		graph[2].add(new Edge(1, 2));
		graph[1].add(new Edge(3, 1));
		graph[2].add(new Edge(3, 5));

		return graph;
	}

	/// 단일 정점에서 모든 정점까지의 최단 거리

}
