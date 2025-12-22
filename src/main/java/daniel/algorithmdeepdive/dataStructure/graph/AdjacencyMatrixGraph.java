package daniel.algorithmdeepdive.dataStructure.graph;

/// # 인접행렬 그래프 구현
/// ## 본질
/// 2차원 배열로 "모든 가능한 간선"을 표현
/// ## 원리
/// - `matrix[i][j]` = 1 이면 i -> j 간선 존재
/// - O(1) 간선 존재 확인
/// - O(V^2) 공간 복잡도
/// ## 적합한 경우: 밀집 그래프(Dense Graph), 간선 존재 여부를 자주 확인
public class AdjacencyMatrixGraph {
	private int[][] matrix;
	private int vertices;

	/// @param vertices 정점의 수
	public AdjacencyMatrixGraph(int vertices) {
		this.matrix = new int[vertices][vertices];
		this.vertices = vertices;
	}

	/// ### 무방향 그래프 간선 추가
	/// 간선에 방향이 없어서 양방향으로 이어준다.
	/// @param from 정점 1
	/// @param to 정점 2
	public void addEdge(int from, int to) {
		matrix[from][to] = 1;
		matrix[to][from] = 1; // 양방향
	}

	/// ### 방향 그래프 간선 추가
	/// 간선에 방향이 있어서 한 정점에서 다른 정점으로만 이동
	/// @param from 출발 정점
	/// @param to 도착 정점
	public void addDirectedEdge(int from, int to) {
		matrix[from][to] = 1;
	}

	/// ### 가중치 그래프
	/// 그래프의 간선은 가중치를 가질 수 있다.
	/// - 두 정점 사이의 거리, 비용, 우선순위등을 나타낸다.
	/// @param from 정점 1
	/// @param to 정점 2
	/// @param weight 가중치
	public void addWeightedEdge(int from, int to, int weight) {
		matrix[from][to] = weight;
		matrix[to][from] = weight;
	}

	// 간선 존재 확인: O(1) - 핵심 장점
	public boolean hasEdge(int from, int to) {
		return matrix[from][to] != 0;
	}

	// 특정 정점의 모든 인접 정점 출력
	public void printNeighbors(int vertex) {
		System.out.print(vertex + "의 인접 정점: ");
		for (int i = 0; i < vertices; i++) {
			if (matrix[vertex][i] != 0) {
				System.out.print(i + " ");
			}
		}
		System.out.println();
	}

	public void printGraph() {
		System.out.println("인접 행렬: ");
		for (int i = 0; i < vertices; i++) {
			for (int j = 0; j < vertices; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
