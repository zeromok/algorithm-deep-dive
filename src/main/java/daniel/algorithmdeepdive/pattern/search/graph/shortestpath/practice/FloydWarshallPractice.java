package daniel.algorithmdeepdive.pattern.search.graph.shortestpath.practice;

import java.util.Arrays;

/// Floyd-Warshall 알고리즘 실행 흐름 관찰
/// - k값이 증가하며 dist[][] 테이블이 어떻게 완성되는지
/// - "k를 거쳐가는 경로"의 의미
public class FloydWarshallPractice {

	private static final int INF = 987_654_321;

	public static void main(String[] args) {
		int[][] floydWarshall = floydWarshall(createGraph());
		System.out.println(Arrays.deepToString(floydWarshall));
	}

	/// 예제 그래프 생성
	static int[][] createGraph() {
		int[][] matrix = new int[7][7];

		for (int i = 0; i < 7; i++) {
			Arrays.fill(matrix[i], INF);
			matrix[i][i] = 0;
		}

		matrix[1][2] = 3;
		matrix[1][3] = 2;
		matrix[1][4] = 5;
		matrix[2][3] = 2;
		matrix[2][5] = 8;
		matrix[3][4] = 2;
		matrix[4][5] = 6;
		matrix[5][6] = 11;

		return matrix;
	}

	/// 모든 정점 쌍의 최단 거리
	public static int[][] floydWarshall(int[][] graph) {
		int v = graph.length;
		int[][] minDist = new int[v][v];

		for (int i = 0; i < v; i++) {
			for (int j = 0; j < v; j++) {
				minDist[i][j] = graph[i][j];
			}
		}

		for (int k = 0; k < v; k++) {
			for (int i = 0; i < v; i++) {
				for (int j = 0; j < v; j++) {
					if (minDist[i][k] != INF && minDist[k][j] != INF) {
						minDist[i][j] = Math.min(minDist[i][k] + minDist[k][j], minDist[i][j]);
					}
				}
			}
		}

		return minDist;
	}
}
