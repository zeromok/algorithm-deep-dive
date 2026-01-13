package daniel.algorithmdeepdive.pattern.search.graph.shortestpath.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/// Bellman-Ford 알고리즘 실행 흐름 관찰
/// - V-1번 반복하며 모든 간선 완화
/// - 음수 사이클 검사 과정
public class BellmanFordPractice {

	static class Node {
		int id;
		int cost;

		public Node(int id, int cost) {
			this.id = id;
			this.cost = cost;
		}
	}

	public static void main(String[] args) {
		int[] bellmanFord = bellmanFord(createNegativeCycleGraph(), 1);
		System.out.println(Arrays.toString(bellmanFord));
	}

	/// 예제 그래프 생성
	static List<List<Node>> createGraph() {
		List<List<Node>> graph = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			graph.add(new ArrayList<>());
		}

		graph.get(1).add(new Node(2, 3));
		graph.get(1).add(new Node(3, 2));
		graph.get(1).add(new Node(4, 5));
		graph.get(2).add(new Node(3, 2));
		graph.get(2).add(new Node(5, 8));
		graph.get(3).add(new Node(4, 2));
		graph.get(4).add(new Node(5, 6));
		graph.get(5).add(new Node(6, 11));

		return graph;
	}

	static List<List<Node>> createNegativeCycleGraph() {
		List<List<Node>> graph = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			graph.add(new ArrayList<>());
		}

		// 1번(시작)에서 2번으로 진입
		graph.get(1).add(new Node(2, 5));

		// --- 음수 사이클 구간 ---
		graph.get(2).add(new Node(3, 2));   // 2 -> 3 (비용 2)
		graph.get(3).add(new Node(4, 1));   // 3 -> 4 (비용 1)
		graph.get(4).add(new Node(2, -5));  // 4 -> 2 (비용 -5) !! 여기서 사이클 합이 2 + 1 + (-5) = -2가 됨
		// -----------------------

		graph.get(4).add(new Node(5, 10));  // 사이클에서 밖으로 나가는 길
		graph.get(5).add(new Node(6, 3));

		return graph;
	}

	public static int[] bellmanFord(List<List<Node>> graph, int start) {
		int v = graph.size();
		int[] minDist = new int[v];
		Arrays.fill(minDist, Integer.MAX_VALUE);
		minDist[start] = 0;

		for (int i = 1; i < v; i++) {
			for (int u = 1; u < v; u++) {
				for (Node node : graph.get(u)) {
					if (minDist[u] == Integer.MAX_VALUE) continue;

					if (minDist[u] + node.cost < minDist[node.id]) {
						minDist[node.id] = minDist[u] + node.cost;
					}
				}
			}
		}

		// 2. 음수 사이클 체크 (v번째 반복에서도 값이 변한다면 사이클 존재)
		return negativeCycleChk(graph, v, minDist) ? null : minDist;
	}

	private static boolean negativeCycleChk(List<List<Node>> graph, int v, int[] minDist) {
		for (int u = 1; u < v; u++) {
			for (Node neighbor : graph.get(u)) {
				if (minDist[u] != Integer.MAX_VALUE && minDist[u] + neighbor.cost < minDist[neighbor.id]) {
					System.out.println("그래프에 음수 사이클이 존재합니다.");
					return true;
				}
			}
		}
		return false;
	}
}
