package daniel.algorithmdeepdive.dataStructure.graph;

import java.util.ArrayList;
import java.util.List;

import lombok.ToString;

/// # 가중치 그래프 - 인접리스트 구현
/// - 실전에서 가장 많이 사용
/// - Dijkstra, Prim 등 최단경로/MST 알고리즘에 필수
public class WeightedGraph {
	@ToString
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

	/// ### 간선 추가 (방향X)
	/// - 두 정점은 방향이 없는 간선으로 이어진다. (양방향)
	/// @param from 정점 1
	/// @param to 정점 2
	/// @param weight 가중치
	public void addEdge(int from, int to, int weight) {
		list.get(from).add(new Edge(to, weight));
		list.get(to).add(new Edge(from, weight));
	}

	/// ### 간선 추가 (방향O)
	/// - 두 정점은 방향이 있는 간선으로 이어진다.
	/// @param from 출발 정점
	/// @param to 도착 정점
	/// @param weight 가중치
	public void addDirectedEdge(int from, int to, int weight) {
		list.get(from).add(new Edge(to, weight));
	}

	/// ### 특정 정점의 인접 정점 반환
	/// @param vertex 확인할 정점
	/// @return 인접한 정점 정보를 담고 있는 {@link Edge} 객체들의 {@link List}
	public List<Edge> getNeighbors(int vertex) {
		return list.get(vertex);
	}

	/// ### 특정 간선의 가중치 반환
	/// @param from 시작 정점
	/// @param to 도착 정점
	/// @return 가중치 (간선 없으면 -1)
	public int getWeight(int from, int to) {
		for (Edge edge : list.get(from)) {
			if (edge.to == to) {
				return edge.weight;
			}
		}
		return -1;  // 간선 없음
	}

	/// ### 그래프의 총 가중치 합 계산
	/// @return 모든 간선의 가중치 합 (무방향이면 2로 나눔)
	public int getTotalWeight() {
		int total = 0;
		for (List<Edge> edges : list) {
			for (Edge edge : edges) {
				total += edge.weight;
			}
		}
		return total / 2;  // 무방향 그래프는 중복 제거
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
