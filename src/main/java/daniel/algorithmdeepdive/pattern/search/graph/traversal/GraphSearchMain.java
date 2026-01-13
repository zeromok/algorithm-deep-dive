package daniel.algorithmdeepdive.pattern.search.graph.traversal;

import java.util.ArrayList;
import java.util.List;

import daniel.algorithmdeepdive.pattern.search.graph.traversal.bfs.BFS;
import daniel.algorithmdeepdive.pattern.search.graph.traversal.dfs.DFS;

/// ## DFS / BFS 비교 실험
/// ### 배운점
/// - DFS / BFS 의 탐색 순서는 다르다.
/// - BFS 는 최단 거리를 보장한다. (가중치 없을 경우)
/// - DFS 는 최단 거리를 보장하지 못한다.
/// - DFS / BFS 모두 모든 정점을 방문할 수 있다.
/// - 가중치 그래프의 최단 경로 알고리즘 -> Dijkstra
public class GraphSearchMain {

	public static void main(String[] args) {
		basic();
		// shortPath();
		// pathFinding();
		// disconnectedGraph();
	}

	/// 테스트 1: 기본 탐색 순서 비교
	/// 목적: DFS 와 BFS 의 탐색 순서 차이
	private static void basic() {
		List<List<Integer>> graph = createBasicGraph();

		System.out.println("그래프 구조:");
		System.out.println("    1");
		System.out.println("   / \\");
		System.out.println("  2   5");
		System.out.println("  |   |");
		System.out.println("  3   6");

		DFS.dfsRecursive(graph, 1); // 1 2 3 5 6
		DFS.dfsIterative(graph, 1);
		BFS.bfs(graph, 1); // 1 2 5 3 6
	}

	/// 테스트 2: 최단거리 - BFS 의 강점
	/// 목적: 왜 BFS 를 써야 하는지 이해
	/// 결론:
	/// - BFS 는 가중치 없는 그래프의 최단 거리 보장
	/// - DFS 는 최단 거리를 보장하지 못함
	private static void shortPath() {
		List<List<Integer>> graph = createShortPathGraph();

		// 레벨별 출력
		BFS.bfsWithLevel(graph, 0);

		// 최단 거리 계산
		int[] distances = BFS.shortestDistances(graph, 0);
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
		List<List<Integer>> graph = createPathFindingGraph();

		int start = 0;
		int target = 5;

		List<Integer> dfsPath = DFS.findPath(graph, start, target);
		List<Integer> bfsPath = BFS.shortestPath(graph, start, target);

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
		List<List<Integer>> graph = createDisconnectedGraph();

		DFS.dfsAll(graph);
		BFS.bfsAll(graph);
	}

	// 그래프 생성 헬퍼 메서드들
	private static List<List<Integer>> createBasicGraph() {
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			graph.add(new ArrayList<>());
		}

		int[][] edges = {{1,2}, {1,5}, {2,3}, {5,6}};
		for (int[] edge : edges) {
			graph.get(edge[0]).add(edge[1]);
			graph.get(edge[1]).add(edge[0]);
		}
		return graph;
	}

	private static List<List<Integer>> createShortPathGraph() {
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			graph.add(new ArrayList<>());
		}

		int[][] edges = {{0,1}, {0,2}, {1,3}, {1,4}, {2,4}, {3,5}, {4,5}, {4,6}};
		for (int[] edge : edges) {
			graph.get(edge[0]).add(edge[1]);
			graph.get(edge[1]).add(edge[0]);
		}
		return graph;
	}

	private static List<List<Integer>> createPathFindingGraph() {
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			graph.add(new ArrayList<>());
		}

		int[][] edges = {{0,1}, {0,2}, {1,3}, {1,4}, {2,4}, {3,5}, {4,5}};
		for (int[] edge : edges) {
			graph.get(edge[0]).add(edge[1]);
			graph.get(edge[1]).add(edge[0]);
		}
		return graph;
	}

	private static List<List<Integer>> createDisconnectedGraph() {
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			graph.add(new ArrayList<>());
		}

		int[][] edges = {
			{0,1}, {1,2},      // 컴포넌트 1
			{3,4},             // 컴포넌트 2
			{5,6}              // 컴포넌트 3
			// 7은 독립
		};

		for (int[] edge : edges) {
			graph.get(edge[0]).add(edge[1]);
			graph.get(edge[1]).add(edge[0]);
		}
		return graph;
	}
}