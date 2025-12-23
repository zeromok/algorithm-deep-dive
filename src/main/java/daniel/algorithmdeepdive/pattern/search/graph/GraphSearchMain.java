package daniel.algorithmdeepdive.pattern.search.graph;

import java.util.List;

/// 배운점
/// - DFS / BFS 의 탐색 순서는 다르다.
/// - BFS 는 최단 거리를 보장한다.
/// - DFS 는 최단 거리를 보장하지 못한다.
/// - DFS / BFS 모두 모든 정점을 방문할 수 있다.
public class GraphSearchMain {
	public static void main(String[] args) {
		basic();
		shortPath();
		pathFinding();
		disconnectedGraph();
	}

	/// 테스트 1: 기본 탐색 순서 비교
	/// 목적: DFS 와 BFS 의 탐색 순서 차이
	private static void basic() {
		DFS dfs = new DFS(7);
		BFS bfs = new BFS(7);

		// 동일한 간선 추가
		int[][] edges = {{1,2}, {1,5}, {2,3}, {5,6}};
		for (int[] edge : edges) {
			dfs.addEdge(edge[0], edge[1]);
			bfs.addEdge(edge[0], edge[1]);
		}

		System.out.println("그래프 구조:");
		System.out.println("    1");
		System.out.println("   / \\");
		System.out.println("  2   5");
		System.out.println("  |   |");
		System.out.println("  3   6");

		dfs.dfsRecursive(1); // 1 2 3 5 6
		bfs.bfs(1); // 1 2 5 3 6
	}

	/// 테스트 2: 최단거리 - BFS 의 강점
	/// 목적: 왜 BFS 를 써야 하는지 이해
	/// 결론:
	/// - BFS 는 가중치 없는 그래프의 최단 거리 보장
	/// - DFS 는 최단 거리를 보장하지 못함
	private static void shortPath() {
		BFS bfs = new BFS(7);

		bfs.addEdge(0, 1);
		bfs.addEdge(0, 2);
		bfs.addEdge(1, 3);
		bfs.addEdge(1, 4);
		bfs.addEdge(2, 4);
		bfs.addEdge(3, 5);
		bfs.addEdge(4, 5);
		bfs.addEdge(4, 6);

		// 레벨별 출력
		bfs.bfsWithLevel(0);

		// 최단 거리 계산
		int[] distances = bfs.shortestDistances(0);
		for (int i = 0; i < 7; i++) {
			if (distances[i] != -1) {
				System.out.println("0 -> " + i + ": " + distances[i] + "칸");
			}
		}
	}

	/// 테스트 3: 경로 찾기 비교
	/// 목적: DFS 와 BFS 가 찾는 경로의 차이
	/// 결론:
	/// - 최단 경로 필요? -> BFS
	/// - 경로만 찾으면 됨? -> DFS (더 빠를 수 있음)
	private static void pathFinding() {
		DFS dfs = new DFS(7);
		BFS bfs = new BFS(7);

		int[][] edges = {{0,1}, {0,2}, {1,3}, {1,4}, {2,4}, {3,5}, {4,5}};
		for (int[] edge : edges) {
			dfs.addEdge(edge[0], edge[1]);
			bfs.addEdge(edge[0], edge[1]);
		}

		// 0 -> 5 까지의 경로 찾기
		int start = 0;
		int target = 5;

		List<Integer> dfsPath = dfs.findPath(start, target);
		List<Integer> bfsPath = bfs.shortestPath(start, target);

		// 처음 발견한 경로
		System.out.println("DFS 경로: " + dfsPath);
		System.out.println("길이: " + (dfsPath.size() - 1) + "칸");

		// 항상 최단 경로
		System.out.println("BFS 경로: " + bfsPath);
		System.out.println("길이: " + (bfsPath.size() - 1) + "칸");
	}

	/// 테스트 4: 연결 컴포넌트 (끊어진 그래프)
	/// 목적: 모든 정점 방문하는 패턴 이해
	/// 결과:
	/// - DFS 든 BFS 든 모든 정점 방문 가능
	/// - 연결 컴포넌트 개수 = 독립적인 그래프 개수
	private static void disconnectedGraph() {
		DFS dfs = new DFS(8);
		BFS bfs = new BFS(8);

		int[][] edges = {
			{0,1}, {1,2},      // 컴포넌트 1
			{3,4},             // 컴포넌트 2
			{5,6}              // 컴포넌트 3
			// 7은 독립
		};

		for (int[] edge : edges) {
			dfs.addEdge(edge[0], edge[1]);
			bfs.addEdge(edge[0], edge[1]);
		}

		dfs.dfsAll();
		bfs.bfsAll();
	}
}
