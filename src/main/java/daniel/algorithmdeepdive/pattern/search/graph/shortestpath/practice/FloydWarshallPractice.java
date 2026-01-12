package daniel.algorithmdeepdive.pattern.search.graph.shortestpath.practice;

/// Floyd-Warshall 알고리즘 실행 흐름 관찰
/// - k값이 증가하며 dist[][] 테이블이 어떻게 완성되는지
/// - "k를 거쳐가는 경로"의 의미
public class FloydWarshallPractice {

	static class Edge {
		int to;
		int dist;

		public Edge(int to, int dist) {
			this.to = to;
			this.dist = dist;
		}
	}

	private static final int INF = 987_654_321;

	public static void main(String[] args) {

	}

	/// 예제 그래프 생성
	/// 0 → 1(5), 0 → 3(8)
	/// 1 → 0(7), 1 → 2(9)
	/// 2 → 0(2), 2 → 3(4)
	/// 3 → 2(3)
	static int[][] createGraph() {
		return new int[][]{
			{0, 5, INF, 8},
			{7, 0, 9, INF},
			{2, INF, 0, 4},
			{INF, INF, 3, 0}
		};
	}

	/// 모든 정점 쌍의 최단 거리

}
