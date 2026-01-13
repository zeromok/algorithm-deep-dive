package daniel.algorithmdeepdive.pattern.search.graph.traversal.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/// # BFS(Breadth-First Search) - 너비 우선 탐색
/// ## 언제 쓰는가?
/// - "최단 거리/경로 찾기" → BFS
///
/// ## 핵심 원리
/// - 레벨 순서대로 탐색 (거리가 가까운 것부터)
/// - Queue 자료구조 사용
/// - 최단 경로 찾기에 최적
/// - 시간 복잡도: O(V + E)
/// ## 장점
/// - 최단 거리 보장 (가중치 없을 때)
/// - 레벨별 처리 가능
public class BFS {

	/// 기본 구현
	/// - Queue를 사용한 레벨별 탐색
	public static void bfs(List<List<Integer>> graph, int start) {
		int vertices = graph.size();
		boolean[] visited = new boolean[vertices];
		Arrays.fill(visited, false);

		System.out.println("\n=== BFS (시작: " + start + ") ===");

		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			Integer v = queue.poll();
			System.out.print(v + " ");

			for (Integer neighbor : graph.get(v)) {
				if (!visited[neighbor]) {
					queue.offer(neighbor);
					visited[neighbor] = true;
				}
			}
		}
		System.out.println();
	}

	/// 거리 계산
	/// - 레벨별로 출력하며 거리 계산
	public static void bfsWithLevel(List<List<Integer>> graph, int start) {
		int vertices = graph.size();
		boolean[] visited = new boolean[vertices];
		Arrays.fill(visited, false);

		System.out.println("\n=== BFS 거리 계산 (시작: " + start + ") ===");

		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		visited[start] = true;

		int level = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			System.out.print("Level: " + level + ": ");

			for (int i = 0; i < size; i++) {
				int v = queue.poll();
				System.out.print(v + " ");

				for (int neighbor : graph.get(v)) {
					if (!visited[neighbor]) {
						visited[neighbor] = true;
						queue.offer(neighbor);
					}
				}
			}
			System.out.println();
			level++;
		}
	}

	/// 최단 거리 계산
	/// - 각 정점까지의 최단 거리 배열 반환
	/// - @param graph 인접 리스트 그래프
	/// - @param start 기준 정점
	/// - @return 최단 거리 정보를 담고 있는 배열 반환
	public static int[] shortestDistances(List<List<Integer>> graph, int start) {
		int vertices = graph.size();
		int[] distances = new int[vertices];
		Arrays.fill(distances, -1);

		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		distances[start] = 0;

		while (!queue.isEmpty()) {
			int v = queue.poll();

			for (int neighbor : graph.get(v)) {
				if (distances[neighbor] == -1) {
					distances[neighbor] = distances[v] + 1;
					queue.offer(neighbor);
				}
			}
		}
		return distances;
	}

	/// 최단 경로 추적
	/// - start에서 target까지의 최단 경로 반환
	public static List<Integer> shortestPath(List<List<Integer>> graph, int start, int target) {
		int vertices = graph.size();
		int[] parent = new int[vertices];
		Arrays.fill(parent, -1);
		boolean[] visited = new boolean[vertices];
		Arrays.fill(visited, false);

		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int v = queue.poll();

			if (v == target)
				break;

			for (int neighbor : graph.get(v)) {
				if (!visited[neighbor]) {
					parent[neighbor] = v;
					visited[neighbor] = true;
					queue.offer(neighbor);
				}
			}
		}

		if (parent[target] == -1 && start != target) {
			return new ArrayList<>();
		}

		List<Integer> path = new ArrayList<>();
		for (int v = target; v != start; v = parent[v]) {
			path.add(v);
		}
		path.add(start);
		Collections.reverse(path);
		return path;
	}

	/// 모든 그래프 탐색
	/// - 연결 컴포넌트별로 BFS 수행
	public static void bfsAll(List<List<Integer>> graph) {
		int vertices = graph.size();
		boolean[] visited = new boolean[vertices];
		Arrays.fill(visited, false);

		System.out.println("\n=== 모든 그래프 BFS ===");
		int componentCount = 0;

		for (int i = 0; i < vertices; i++) {
			if (!visited[i]) {
				System.out.print("그래프 " + (++componentCount) + ": ");
				bfsComponent(graph, i, visited);
				System.out.println();
			}
		}
	}

	private static void bfsComponent(List<List<Integer>> graph, int start, boolean[] visited) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int v = queue.poll();
			System.out.print(v + " ");

			for (int neighbor : graph.get(v)) {
				if (!visited[neighbor]) {
					visited[neighbor] = true;
					queue.offer(neighbor);
				}
			}
		}
	}
}
