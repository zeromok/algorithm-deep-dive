package daniel.algorithmdeepdive.pattern.search.graph.shortestpath;

import java.util.Arrays;
import java.util.List;

/// ## 벨만-포드 알고리즘 (Bellman-Ford Algorithm)
/// ### 전제 조건
/// - 음수 가중치 처리 가능 (Dijkstra와의 차이)
/// - 단일 출발점 최단 경로
/// - 음수 사이클 감지 가능
/// ### 핵심 원리
/// - V-1번 반복: 모든 간선에 대해 완화(relaxation)
/// - "최단 경로는 최대 V-1개 간선 사용"
/// - `dist[v] = min(dist[v], dist[u] + weight(u,v))`
/// ### 시간 복잡도
/// - O(VE): V-1번 반복 × E개 간선 확인
/// - Dijkstra보다 느리지만 음수 가중치 처리 가능
/// ### 왜 V-1번 반복인가?
/// - 사이클 없는 최단 경로는 최대 V-1개 간선 포함
/// - 각 라운드마다 "1개 간선 더 사용한 경로" 고려
/// - V-1번이면 모든 가능한 최단 경로 발견
/// ### 음수 사이클 검사
/// - V-1번 후에도 갱신 가능 → 음수 사이클 존재
/// - 음수 사이클이 있으면 최단 거리를 정의할 수 없음
public class BellmanFord {

	static class Edge {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}

	/// 음수 가중치 처리 가능
	/// @param n 정점 개수
	/// @param edges 모든 간선 리스트
	/// @param start 시작 정점
	/// @return dist[i] = start 에서 i 까지 최단 거리, 음수 사이클 있으면 null
	public static int[] bellmanFord(int n, List<Edge> edges, int start) {
		int[] dist = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;

		// V-1번 반복: 모든 간선에 대해 완화
		for (int i = 0; i < n - 1; i++) {
			for (Edge edge : edges) {
				if (dist[edge.from] != Integer.MAX_VALUE) {
					if (dist[edge.from] + edge.weight < dist[edge.to]) {
						dist[edge.to] = dist[edge.from] + edge.weight;
					}
				}
			}
		}

		// 음수 사이클 검사
		for (Edge edge : edges) {
			if (dist[edge.from] != Integer.MAX_VALUE) {
				if (dist[edge.from] + edge.weight < dist[edge.to]) {
					return null; // 음수 사이클 존재
				}
			}
		}
		return dist;
	}
}
