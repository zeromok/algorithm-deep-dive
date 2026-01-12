package daniel.algorithmdeepdive.pattern.search.graph.shortestpath;

/// ## 플로이드 워셜 알고리즘 (인접 행렬)
/// ### 전제 조건
/// - 모든 쌍 최단 경로 필요
/// - 정점 수가 적을 때 (V ≤ 500)
/// ### 핵심 원리
/// - DP: 중간 정점을 거쳐가는 경로 점진적 고려
/// - `dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])`
/// - k를 중간 정점으로 하는 모든 경로 확인
/// ### 시간 복잡도
/// - O(V³): 3중 반복문
/// ### 왜 모든 쌍을 계산하는가?
/// - 다익스트라는 단일 출발점만 계산
/// - 모든 쌍이 필요하면 V번 다익스트라 = O(V × (V+E)log V)
/// - 밀집 그래프에서 플로이드 워셜이 더 효율적
/// ### 음수 가중치 가능?
/// - 가능 (다익스트라와 차이점)
/// - 단, 음수 사이클은 감지 필요
public class FloydWarshall {

	private static final int INF = 987_654_321;

	/// 모든 정점 쌍의 최단 거리
	/// @param graph `graph[i][j]` = i 에서 j 로 가는 간선 가중치 (없으면 INF)
	/// @return dist[i][j] = i 에서 j 까지 최단 거리
	public static int[][] findAllPairsShortestPath(int[][] graph) {
		int n = graph.length;
		int[][] dist = new int[n][n];

		// 초기화: 직접 연결된 간선 복수
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				dist[i][j] = graph[i][j];
			}
		}

		for (int k = 0; k < n; k++) { // 경유지
			for (int i = 0; i < n; i++) { // 출발
				for (int j = 0; j < n; j++) { // 도착
					if (dist[i][k] != INF && dist[k][j] != INF) {
						dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
					}
				}
			}
		}

		return dist;
	}
}
