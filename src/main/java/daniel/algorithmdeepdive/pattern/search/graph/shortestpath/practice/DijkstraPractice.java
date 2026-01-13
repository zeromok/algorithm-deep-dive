package daniel.algorithmdeepdive.pattern.search.graph.shortestpath.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraPractice {

	static class Node {
		int id;
		int cost;

		public Node(int id, int cost) {
			this.id = id;
			this.cost = cost;
		}
	}

	public static void main(String[] args) {
		int[] dijkstra = dijkstra(createGraph(), 1);
		System.out.println(Arrays.toString(dijkstra));
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

	/// 단일 정점에서 모든 정점까지의 최단 거리
	public static int[] dijkstra(List<List<Node>> graph, int start) {
		int v = graph.size();
		int[] minDist = new int[v];

		Arrays.fill(minDist, Integer.MAX_VALUE);
		minDist[start] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
		pq.offer(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node currNode = pq.poll();

			if (currNode.cost > minDist[currNode.id]) {
				continue;
			}

			for (Node node : graph.get(currNode.id)) {
				int newDist = minDist[currNode.id] + node.cost;
				if (newDist < minDist[node.id]) {
					minDist[node.id] = newDist;
					pq.offer(new Node(node.id, newDist));
				}
			}
		}
		return minDist;
	}
}
