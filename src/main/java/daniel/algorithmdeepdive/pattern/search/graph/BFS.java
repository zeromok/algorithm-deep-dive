package daniel.algorithmdeepdive.pattern.search.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/// # BFS(Breadth-First Search) - 너비 우선 탐색
/// ## 핵심 원리
/// - 레벨 순서대로 탐색 (거리가 가까운 것부터)
/// - Queue 자료구조 사용
/// - 최단 경로 찾기에 최적
/// - 시간 복잡도: O(V + E)
/// ## 장점
/// - 최단 거리 보장 (가중치 없을 때)
/// - 레벨별 처리 가능
public class BFS {
	List<List<Integer>> graph;
	private boolean[] visited;
	private int vertices;

	public BFS(int vertices) {
		this.vertices = vertices;
		this.visited = new boolean[vertices];

		this.graph = new ArrayList<>();
		for (int i = 0; i < vertices; i++) {
			graph.add(new ArrayList<>());
		}
	}

	// 무방향
	public void addEdge(int from, int to) {
		graph.get(from).add(to);
		graph.get(to).add(from);
	}

	/// 기본 구현
	public void bfs(int start) {
		Arrays.fill(visited, false);
		System.out.println("\n=== BFS (시작: " + start + ") ===");

		// 1. 방문할 정점들을 담아둘 큐 생성
		Queue<Integer> queue = new LinkedList<>();

		// 2. 시작 정점을 큐에 넣고 즉시 방문 처리 (BFS 핵심)
		queue.offer(start);
		visited[start] = true;

		// 3. 큐에 정점이 없을 때까지 반복
		while (!queue.isEmpty()) {
			// 3-1. 가장 먼저 넣은 정점을 꺼냄 (FIFO)
			Integer v = queue.poll();
			System.out.print(v + " ");

			// 3-2. 현재 정점의 인접 정점 리스트 가져옴
			List<Integer> neighbors = graph.get(v);
			// 3-3. 인접 정점들을 순회하며 처리
			for (Integer neighbor : neighbors) {
				// 3-4. 아직 방문하지 않은 인접 정점만 큐에 넣음
				if (!visited[neighbor]) {
					queue.offer(neighbor);
					// 3-5. 큐에 넣는 즉시 방문 처리
					visited[neighbor] = true;
				}
			}
		}
	}

	/// 거리 계산
	public void bfsWithLevel(int start) {
		System.out.println("\n=== BFS 거리 계산 (시작: " + start + ") ===");
		Arrays.fill(visited, false);

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
	///
	/// @param start 기준 정점
	/// @return 최단 거리 정보를 담고 있는 배열 반환
	public int[] shortestDistances(int start) {
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
	public List<Integer> shortestPath(int start, int target) {
		int[] parent = new int[vertices];
		Arrays.fill(parent, -1);

		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int v = queue.poll();

			if (v == target)
				break;

			for (int neighbor : graph.get(v)) {
				if (parent[neighbor] == -1) {
					parent[neighbor] = v;
					queue.offer(neighbor);
				}
			}
		}

		if (parent[target] == -1) {
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
	public void bfsAll() {
		System.out.println("\n=== 모든 그래프 BFS ===");
		Arrays.fill(visited, false);
		int componentCount = 0;

		for (int i = 0; i < vertices; i++) {
			if (!visited[i]) {
				System.out.print("그래프 " + (++componentCount) + ": ");
				bfsComponent(i);
				System.out.println();
			}
		}
	}

	private void bfsComponent(int start) {
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
