package daniel.algorithmdeepdive.datastructure.graph.mst;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

// 정점과 간선 정보를 담을 클래스
class Node implements Comparable<Node> {
	int dest, weight;

	Node(int dest, int weight) {
		this.dest = dest;
		this.weight = weight;
	}

	// 가중치 기준 오름차순 정렬 (PriorityQueue용)
	@Override
	public int compareTo(Node other) {
		return this.weight - other.weight;
	}
}

public class Prim {

	public static void main(String[] args) {
		int v = 6;
		List<List<Node>> graph = new ArrayList<>();
		for (int i = 0; i <= v; i++) {
			graph.add(new ArrayList<>());
		}

		// 간선 추가 (시작, 끝, 가중치)
		addEdge(graph, 1, 3, 3);
		addEdge(graph, 1, 4, 5);
		addEdge(graph, 1, 3, 6);
		addEdge(graph, 2, 5, 2);
		addEdge(graph, 2, 4, 2);
		addEdge(graph, 3, 5, 1);
		addEdge(graph, 3, 6, 1);
		addEdge(graph, 4, 6, 3);
		addEdge(graph, 5, 6, 4);
		addEdge(graph, 6, 5, 4);

		prim(graph, 1, v);
	}

	private static void addEdge(List<List<Node>> graph, int u, int v, int w) {
		graph.get(u).add(new Node(v, w));
		graph.get(v).add(new Node(u, w));
	}

	private static void prim(List<List<Node>> graph, int start, int v) {
		boolean[] visited = new boolean[v + 1]; // 방문 여부 (MST 포함 여부)
		PriorityQueue<Node> pq = new PriorityQueue<>();

		// 시작 정점 설정
		pq.add(new Node(start, 0));

		int mstWeight = 0;
		int count = 0;

		System.out.println("=== Prim MST 선택 과정 ===");

		while (!pq.isEmpty()) {
			Node curr = pq.poll();

			// 이미 MST에 포함된 정점이라면 건너뜀 (사이클 방지)
			if (visited[curr.dest]) continue;

			// MST에 포함시킴
			visited[curr.dest] = true;
			mstWeight += curr.weight;
			if (curr.weight != 0) {
				System.out.println("정점 " + curr.dest + " 연결 (가중치: " + curr.weight + ")");
			}

			count++;
			if (count == v) break; // 모든 정점을 다 연결하면 종료

			// 현재 정점과 연결된 인접 정점들을 큐에 삽입
			for (Node neighbor : graph.get(curr.dest)) {
				if (!visited[neighbor.dest]) {
					pq.add(neighbor);
				}
			}
		}

		System.out.println("최소 신장 트리의 총 가중치: " + mstWeight);
	}
}
