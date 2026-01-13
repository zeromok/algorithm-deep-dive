package daniel.algorithmdeepdive.pattern.search.graph.shortestpath;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/// ## 다익스트라 알고리즘 (Dijkstra's Algorithm)
/// 하나의 시작점으로부터 다른 모든 정점까지의 최단 거리를 구하는 알고리즘
/// ### 전제 조건
/// - 양수 가중치만 가능 (음수 가중치 불가)
/// - 단일 출발점 최단 경로
/// ### 핵심 원리
/// - 그리디: 현재까지 가장 가까운 정점부터 확정
/// - BFS: 다음 방문할 정점 선택
/// - `dist[v] = min(dist[v], dist[u] + weight(u,v))`
/// ### 시간 복잡도
/// - O((V + E)log V): 우선순위 큐 연산
/// - 희소 그래프: O(E log V)
/// - 밀집 그래프: O(V^2 log V)
/// ### 왜 음수 가중치가 안되는가?
/// - 그리디 전략이 "되돌아가지 않는다" 가정
/// - 음수 가중치가 있으면 이미 확정한 정점도 다시 확인 필요
public class Dijkstra {

	static class Edge {
		int to;
		int dist;

		public Edge(int to, int dist) {
			this.to = to;
			this.dist = dist;
		}
	}

	/// 단일 출발점에서 모든 정점까지의 최단 거리
	/// @param graph 인접 리스트 (`graph[from]` = List of Edge)
	/// @param start 시작 정점
	/// @return dist[i] = start 에서 i 까지 최단 거리
	public static int[] dijkstra(List<Edge>[] graph, int start) {
		// 1. 초기화
		int v = graph.length; // 정점의 개수
		int[] dist = new int[v]; // 각 정점까지의 최단 거리를 저장할 배열
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;

		// 2. 최소거리 노드 선택
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist); // 거리가 짧은 정점부터 꺼냄
		pq.offer(new Edge(start, 0));

		while (!pq.isEmpty()) {
			Edge curr = pq.poll(); // 현재 가장 거리가 짧은 정점을 꺼냄 -> Dijkstra 의 "그리디"

			if (curr.dist > dist[curr.to]) continue; // 이미 확정된 거리보다 크면 무시

			// 3. 인접 정점 탐색 및 거리 갱신
			for (Edge next : graph[curr.to]) {
				int newDist = dist[curr.to] + next.dist; // curr.to 를 거쳐 next.to 로가는 경로의 총 거리

				if (newDist < dist[next.to]) {
					dist[next.to] = newDist;
					pq.offer(new Edge(next.to, newDist));
				}
			}
		}

		return dist;
	}
}
