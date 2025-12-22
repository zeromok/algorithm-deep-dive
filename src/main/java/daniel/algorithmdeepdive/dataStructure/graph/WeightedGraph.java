package daniel.algorithmdeepdive.dataStructure.graph;

import java.util.ArrayList;
import java.util.List;

/// # 가중치 그래프 - 인접리스트 구현
/// - 실전에서 가장 많이 사용
/// - Dijkstra, Prim 등 최단경로/MST 알고리즘에 필수
public class WeightedGraph {
	static class Edge {
		int to;
		int weight;

		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}

	private List<List<Edge>> list;
	private int vertices;

	public WeightedGraph(int vertices) {
		this.vertices = vertices;

		this.list = new ArrayList<>();
		for (int i = 0; i < vertices; i++) {
			list.add(new ArrayList<>());
		}
	}

	// 간선 추가 (방향X)
	public void addEdge(int from, int to, int weight) {
		list.get(from).add(new Edge(to, weight));
		list.get(to).add(new Edge(from, weight));
	}

	// 간선 추가 (방향O)
	public void addDirectedEdge(int from, int to, int weight) {
		list.get(from).add(new Edge(to, weight));
	}

	public List<Edge> getNeighbors(int vertex) {
		return list.get(vertex);
	}

	public void printGraph() {
		System.out.println("가중치 그래프: ");
		for (int i = 0; i < vertices; i++) {
			System.out.print(i + ": ");
			for (Edge edge : list.get(i)) {
				System.out.print("(" + edge.to + ", " + edge.weight + ")");
			}
			System.out.println();
		}
	}
}
