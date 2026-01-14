package daniel.algorithmdeepdive.pattern.search.graph.traversal;

import static daniel.algorithmdeepdive.pattern.search.graph.traversal.GraphTestUtils.*;

import java.util.ArrayList;
import java.util.Arrays;
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
		System.out.println("=== 1. 탐색 순서 비교 ===");
		compareTraversalOrder();

		System.out.println("\n=== 2. 최단 거리 비교 ===");
		compareShortestDistance();

		System.out.println("\n=== 3. 경로 찾기 비교 ===");
		comparePathFinding();

		System.out.println("\n=== 4. 연결 컴포넌트 ===");
		compareComponents();
	}

	/// 테스트 1: 기본 탐색 순서 비교
	/// 목적: DFS 와 BFS 의 탐색 순서 차이
	private static void compareTraversalOrder() {
		List<List<Integer>> graph = createBasicGraph();

		System.out.println("DFS (재귀): ");
		DFS.dfsRecursive(graph, 0);
		System.out.println();

		System.out.println("BFS: ");
		BFS.bfs(graph, 0);
		System.out.println();
	}

	/// 테스트 2: 최단거리 - BFS 의 강점
	/// 목적: 왜 BFS 를 써야 하는지 이해
	/// 결론:
	/// - BFS 는 가중치 없는 그래프의 최단 거리 보장
	/// - DFS 는 최단 거리를 보장하지 못함
	private static void compareShortestDistance() {
		List<List<Integer>> graph = createBasicGraph();

		// BFS: 레벨별 탐색
		BFS.bfsWithLevel(graph, 0);

		// BFS: 최단 거리 배열
		int[] dist = BFS.shortestDistances(graph, 0);
		System.out.println("최단 거리: " + Arrays.toString(dist));
	}

	/// 테스트 3: 경로 찾기 비교
	/// 목적: DFS 와 BFS 가 찾는 경로의 차이
	/// 결론:
	/// - 최단 경로 필요? -> BFS
	/// - 경로만 찾으면 됨? -> DFS (더 빠를 수 있음)
	private static void comparePathFinding() {
		List<List<Integer>> graph = createCycleGraph();

		int start = 0;
		int target = 5;

		// DFS: 처음 발견한 경로 (최단 보장 안됨)
		List<Integer> dfsPath = DFS.findPath(graph, start, target);
		System.out.println("DFS 경로: " + dfsPath + " (길이: " + (dfsPath.size() - 1) + ")");

		// BFS: 항상 최단 경올
		List<Integer> bfsPath = BFS.shortestPath(graph, start, target);
		System.out.println("BFS 경로: " + bfsPath + " (길이: " + (bfsPath.size() - 1) + ")");
	}

	/// 테스트 4: 연결 컴포넌트 (끊어진 그래프)
	/// 목적: 모든 정점 방문하는 패턴 이해
	/// 결과:
	/// - DFS 든 BFS 든 모든 정점 방문 가능
	/// - 연결 컴포넌트 개수 = 독립적인 그래프 개수
	private static void compareComponents() {
		List<List<Integer>> graph = createMultipleComponentsGraph();

		int dfsComponent = DFS.countComponents(graph);
		System.out.println("DFS 모든 컴포넌트: " + dfsComponent);

		int bfsComponent = BFS.countComponents(graph);
		System.out.println("BFS 모든 컴포넌트: " + bfsComponent);
	}
}