package daniel.algorithmdeepdive.datastructure.graph;

public class GraphMain {
	private static final int VERTEX_NUM = 7; // 정점 개수

	public static void main(String[] args) {
		// basicFeatures();
		// sparseGraph();
		// denseGraph();
		BFSScenario();
	}

	/// 테스트 1: 기본 기능 확인
	/// 목적: 둘 다 같은 그래프를 표현할 수 있다.
	/// 결과: 같은 그래프를 다르게 저장
	private static void basicFeatures() {
		System.out.println("==== 그래프 표현 ====");
		int vertices = 6;

		// 같은 그래프를 두 방식으로 구현
		AdjacencyMatrixGraph matrix = new AdjacencyMatrixGraph(vertices);
		AdjacencyListGraph list = new AdjacencyListGraph(vertices);

		// 동일한 간선 추가
		int[][] edges = {{0, 1}, {1, 2}, {0, 3}, {2, 4}, {4, 5}};

		for (int[] edge : edges) {
			matrix.addEdge(edge[0], edge[1]);
			list.addEdge(edge[0], edge[1]);
		}

		matrix.printGraph(); // 인접행렬
		list.printGraph(); // 인접리스트
	}

	/// 테스트 2: 희소 그래프에서의 차이
	/// 목적: 메모리 낭비 체감 (공간 복잡도 차이)
	/// 결과: 희소 그래프는 인접리스트만 가능
	private static void sparseGraph() {
		System.out.println("==== 그래프 차이 ====");
		int vertices = 1_000; // 정점 1,000개
		int edges = 10; // 간선 10개
		System.out.println("(정점: " + vertices + "개" + ", 간선: " + edges + "개)\n");

		// 인접행렬
		long matrixMemory = (long)vertices * vertices * 4;
		// 간선 10개를 위해 100만칸 사용
		System.out.println("인접행렬 메모리 필요공간: " + vertices + " x " + vertices + " = " + (vertices * vertices) + "개");
		System.out.println("메모리: " + (matrixMemory  / 1024 / 1024) + " MB");

		System.out.println("---");

		// 인접리스트
		long listMemory = edges * 2 * 8; // 양방향 = 20개 참조, 참조 = 8바이트
		System.out.println("인접리스트 메모리 필요공간: " + (edges * 2) + "개 (실제 간선만)");
		System.out.println("메모리: " + (listMemory / 1024 / 1024) + "MB");

		System.out.println("인접행렬 vs 인접리스트: " + (matrixMemory / listMemory) + "배");
	}

	/// 테스트 3: 밀집 그래프
	/// 목적: 인접행렬이 좋은 경우
	/// 결과: 밀집 그래프에서 인접행렬도 좋지만 정점 >= 1000 이면 여전히 인접리스트 권장
	private static void denseGraph() {
		System.out.println("==== 언제 무엇을 사용해야할까? ====");
		int vertices = 100;
		int maxEdges = vertices * (vertices - 1) / 2; // 완전 그래프
		int actualEdges = (int)(maxEdges * 0.8); // 80% 밀집
		System.out.println("정점: " + vertices + "개, 가능한 최대 간선: " + maxEdges + "개, 실제 간선: " + actualEdges + "개 (80%밀집)");

		long matrixSpace = vertices * vertices;
		long listSpace = actualEdges * 2;
		System.out.println("인접행렬: " + matrixSpace + "칸");
		System.out.println("인접리스트: " + listSpace + "칸");
		System.out.println("차이: " + String.format("%.1f", (double)matrixSpace / listSpace) + "배");
	}

	/// 테스트 4: 실전 시나리오 - BFS 순회 시간 비교
	/// 목적: "인접 정점 순회"의 차이 체감
	/// 결론: BFS/DFS 에서 인접리스트가 압도적으로 빠름
	/// - 인접 정점 순회 = 그래프 알고리즘의 핵심
	private static void BFSScenario() {
		System.out.println("==== BFS 차이 ====");
		int vertices = 1_000;

		AdjacencyMatrixGraph matrix = new AdjacencyMatrixGraph(vertices);
		AdjacencyListGraph list = new AdjacencyListGraph(vertices);

		// 희소 그래프: 각 정점이 평균 3개와 연결
		for (int i = 0; i < vertices - 1; i++) {
			matrix.addEdge(i, i + 1);
			list.addEdge(i, i + 1);
			if (i < vertices - 2) {
				matrix.addEdge(i, i + 2);
				list.addEdge(i, i + 2);
			}
		}

		// 인접행렬로 정점 0의 인접 정점 찾기
		System.out.println("[인접행렬] 확인해야 할 칸: " + vertices + "개");

		// 인접리스트로 정점 0의 인접 정점 찾기
		System.out.println("[인접리스트] 확인해야 할 칸: " + list.getNeighbors(0).size() + "개");
	}
}
