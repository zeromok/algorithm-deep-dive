package daniel.algorithmdeepdive.pattern.search.graph.traversal;

import java.util.ArrayList;
import java.util.List;

/// ## 그래프 예제 생성 유틸리티
/// ### 목적
/// - BFS/DFS 연습용 예제 그래프 생성
/// - 인접리스트와 인접행렬 두 가지 형태 제공
/// - 다양한 케이스 (기본, 다중 컴포넌트, 사이클 등)
public class GraphTestUtils {


	// ========== 기본 그래프 (8개 정점) ==========
	/// 예제 그래프 생성 (인접 리스트)
	/// ```
	///   0
	/// / | \
	/// 1 2 3
	/// | | |
	/// 4 5 6
	/// \ | /
	///   7
	/// ```
	public static List<List<Integer>> createBasicGraph() {
		int vertices = 8;
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < vertices; i++) {
			graph.add(new ArrayList<>());
		}

		// 0: 1, 2, 3
		graph.get(0).add(1);
		graph.get(0).add(2);
		graph.get(0).add(3);

		// 1: 0, 4
		graph.get(1).add(0);
		graph.get(1).add(4);

		// 2: 0, 5
		graph.get(2).add(0);
		graph.get(2).add(5);

		// 3: 0, 6
		graph.get(3).add(0);
		graph.get(3).add(6);

		// 4: 1, 7
		graph.get(4).add(1);
		graph.get(4).add(7);

		// 5: 2, 7
		graph.get(5).add(2);
		graph.get(5).add(7);

		// 6: 3, 7
		graph.get(6).add(3);
		graph.get(6).add(7);

		// 7: 4, 5, 6
		graph.get(7).add(4);
		graph.get(7).add(5);
		graph.get(7).add(6);

		return graph;
	}

	/// 예제 그래프 생성 (인접 행렬)
	public static int[][] createBasicGraphMatrix() {
		int n = 8;
		int[][] matrix = new int[n][n];

		// 0: 1, 2, 3
		matrix[0][1] = 1; matrix[1][0] = 1;
		matrix[0][2] = 1; matrix[2][0] = 1;
		matrix[0][3] = 1; matrix[3][0] = 1;

		// 1: 0, 4
		matrix[1][4] = 1; matrix[4][1] = 1;

		// 2: 0, 5
		matrix[2][5] = 1; matrix[5][2] = 1;

		// 3: 0, 6
		matrix[3][6] = 1; matrix[6][3] = 1;

		// 4: 1, 7
		matrix[4][7] = 1; matrix[7][4] = 1;

		// 5: 2, 7
		matrix[5][7] = 1; matrix[7][5] = 1;

		// 6: 3, 7
		matrix[6][7] = 1; matrix[7][6] = 1;

		return matrix;
	}


	// ========== 연결 컴포넌트가 여러 개인 그래프 ==========
	/// 다중 컴포넌트 그래프 - 인접리스트
	/// ```
	/// 그래프 1: 0-1-2
	/// 그래프 2: 3-4
	/// 그래프 3: 5 (단독)
 	/// ```
	public static List<List<Integer>> createMultipleComponentsGraph() {
		int vertices = 6;
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < vertices; i++) {
			graph.add(new ArrayList<>());
		}

		// 컴포넌트 1: 0-1-2
		graph.get(0).add(1);
		graph.get(1).add(0);
		graph.get(1).add(2);
		graph.get(2).add(1);

		// 컴포넌트 2: 3-4
		graph.get(3).add(4);
		graph.get(4).add(3);

		// 컴포넌트 3: 5 (단독)

		return graph;
	}

	/// 다중 컴포넌트 그래프 - 인접행렬
	public static int[][] createMultipleComponentsGraphMatrix() {
		int n = 6;
		int[][] matrix = new int[n][n];

		// 컴포넌트 1: 0-1-2
		matrix[0][1] = 1; matrix[1][0] = 1;
		matrix[1][2] = 1; matrix[2][1] = 1;

		// 컴포넌트 2: 3-4
		matrix[3][4] = 1; matrix[4][3] = 1;

		// 컴포넌트 3: 5 (단독)

		return matrix;
	}


	// ========== 사이클이 있는 복잡한 그래프 ==========
	/// 사이클 그래프 - 인접리스트
	/// ```
	///   0
	///  / \
	/// 1---2
	/// |   |
	/// 3---4
	///  \ /
	///   5
 	/// ```
	public static List<List<Integer>> createCycleGraph() {
		int vertices = 6;
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < vertices; i++) {
			graph.add(new ArrayList<>());
		}

		// 삼각형: 0-1-2
		graph.get(0).add(1);
		graph.get(1).add(0);
		graph.get(0).add(2);
		graph.get(2).add(0);
		graph.get(1).add(2);
		graph.get(2).add(1);

		// 사각형: 1-3-4-2
		graph.get(1).add(3);
		graph.get(3).add(1);
		graph.get(3).add(4);
		graph.get(4).add(3);
		graph.get(4).add(2);
		graph.get(2).add(4);

		// 다이아몬드: 3-5-4
		graph.get(3).add(5);
		graph.get(5).add(3);
		graph.get(5).add(4);
		graph.get(4).add(5);

		return graph;
	}

	/// 사이클 그래프 - 인접행렬
	public static int[][] createCycleGraphMatrix() {
		int n = 6;
		int[][] matrix = new int[n][n];

		// 삼각형: 0-1-2
		matrix[0][1] = 1; matrix[1][0] = 1;
		matrix[0][2] = 1; matrix[2][0] = 1;
		matrix[1][2] = 1; matrix[2][1] = 1;

		// 사각형: 1-3-4-2
		matrix[1][3] = 1; matrix[3][1] = 1;
		matrix[3][4] = 1; matrix[4][3] = 1;
		matrix[4][2] = 1; matrix[2][4] = 1;

		// 다이아몬드: 3-5-4
		matrix[3][5] = 1; matrix[5][3] = 1;
		matrix[5][4] = 1; matrix[4][5] = 1;

		return matrix;
	}
}
