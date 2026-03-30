package daniel.algorithmdeepdive.datastructure.graph.mst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 간선 정보 클래스
class Edge implements Comparable<Edge> {
	int src, dest, weight;

	public Edge(int src, int dest, int weight) {
		this.src = src;
		this.dest = dest;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge other) {
		return this.weight - other.weight;
	}
}

public class Kruskal {
	static int[] parent;

	public static void main(String[] args) {
		int v = 6; // 정점의 개수
		List<Edge> edges = new ArrayList<>();

		// 간선 추가 (시작, 끝, 가중치)
		edges.add(new Edge(1, 2, 4));
		edges.add(new Edge(1, 3, 3));
		edges.add(new Edge(1, 4, 5));
		edges.add(new Edge(2, 3, 6));
		edges.add(new Edge(2, 5, 2));
		edges.add(new Edge(3, 4, 2));
		edges.add(new Edge(3, 5, 1));
		edges.add(new Edge(3, 6, 1));
		edges.add(new Edge(4, 6, 3));
		edges.add(new Edge(5, 6, 4));
		edges.add(new Edge(6, 5, 4));

		kruskal(edges, v);
	}

	private static void kruskal(List<Edge> edges, int v) {
		// 1. 간선들을 가중치 기준으로 오름차순 정렬
		Collections.sort(edges);

		// 2. Union-Find를 위한 부모 배열 초기화
		parent = new int[v + 1];
		for (int i = 1; i <= v; i++) {
			parent[i] = i;
		}

		int mstWeight = 0;
		int edgesCount = 0;
		List<Edge> result = new ArrayList<>();

		// 3. 작은 간선부터 하나씩 확인
		for (Edge edge : edges) {
			// 사이클이 발생하지 않는다면 (부모가 다르다면)
			if (find(edge.src) != find(edge.dest)) {
				union(edge.src, edge.dest);
				mstWeight += edge.weight;
				result.add(edge);
				edgesCount++;
			}

			// 간선 수가 (정점 - 1)개가 되면 종료 가능
			if (edgesCount == v - 1) break;
		}


		// 결과 출력
		System.out.println("선택된 간선들:");
		for (Edge e : result) {
			System.out.println(e.src + " - " + e.dest + " (" + e.weight + ")");
		}
		System.out.println("최소 신장 트리의 총 가중치: " + mstWeight);
	}

	// Union-Find: 부모 노드 찾기 (선택하려는 간선의 양 끝점이 어떤 그룹에 속해있는지 확인)
	private static int find(int i) {
		if (parent[i] == i) {
			return i;
		}
		return parent[i] = find(parent[i]);
	}

	// Union-Find: 두 집합 합치기 (두 정점의 사이클이 없다면, 해당 간선을 선택해 MST에 포함)
	private static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA != rootB) {
			parent[rootB] = rootA;
		}
	}
}
