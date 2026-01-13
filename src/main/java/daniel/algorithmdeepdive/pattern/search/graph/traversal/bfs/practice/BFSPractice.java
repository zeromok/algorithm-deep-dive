package daniel.algorithmdeepdive.pattern.search.graph.traversal.bfs.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/// ## BFS 실행 흐름 관찰
/// ### 목표
/// - Queue에 넣는 순서와 꺼내는 순서
/// - 레벨별 탐색 과정
/// - "최단 거리" 보장 과정
public class BFSPractice {

	public static void main(String[] args) {
		bfs(createGraph(), 0);
		bfsWithLevel(createGraph(), 0);
		System.out.println(Arrays.toString(shortestDistances(createGraph(), 0)));
		System.out.println(shortestPath(createGraph(), 0, 1));
		graphSearch(createGraph());
	}

	/// 예제 그래프 생성
	/// ```
	///     0
	///   /   \
	///  1     2
	///   \   /
	///     3
	/// ```
	static List<List<Integer>> createGraph() {
		int vertices = 4;
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < vertices; i++) {
			graph.add(new ArrayList<>());
		}

		graph.get(0).add(1);
		graph.get(0).add(2);
		graph.get(1).add(0);
		graph.get(1).add(3);
		graph.get(2).add(0);
		graph.get(2).add(3);
		graph.get(3).add(1);
		graph.get(3).add(2);

		return graph;
	}

	/// 기본 구현
	public static void bfs(List<List<Integer>> graph, int start) {
		int v = graph.size();
		boolean[] visited = new boolean[v];

		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			Integer curr = queue.poll();
			System.out.print(curr);

			for (Integer next : graph.get(curr)) {
				if (!visited[next]) {
					queue.offer(next);
					visited[next] = true;
				}
			}
			System.out.print(" -> ");
		}
	}

	/// 거리 계산
	public static void bfsWithLevel(List<List<Integer>> graph, int start) {
		int v = graph.size();
		boolean[] visited = new boolean[v];

		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		visited[start] = true;

		int level = 0;
		while (!queue.isEmpty()) {
			for (int i = 0; i < queue.size(); i++) {
				Integer curr = queue.poll();
				System.out.println("Level: " + level);

				for (Integer next : graph.get(curr)) {
					if (!visited[next]) {
						queue.offer(next);
						visited[next] = true;
					}
				}
			}
			level++;
		}
	}

	/// 최단 거리 계산
	public static int[] shortestDistances(List<List<Integer>> graph, int start) {
		int v = graph.size();
		int[] dist = new int[v];
		Arrays.fill(dist, -1);

		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		dist[start] = 0;

		while (!queue.isEmpty()) {
			Integer curr = queue.poll();

			for (Integer next : graph.get(curr)) {
				if (dist[next] == -1) {
					queue.offer(next);
					dist[next] = dist[curr] + 1;
				}
			}
		}
		return dist;
	}

	/// 최단 경로 추적
	public static List<Integer> shortestPath(List<List<Integer>> graph, int start, int target) {
		int v = graph.size();
		int[] dist = new int[v];
		boolean[] visited = new boolean[v];
		Arrays.fill(dist, -1);

		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			Integer curr = queue.poll();

			if (curr == target) {
				break;
			}

			for (Integer next : graph.get(curr)) {
				if (!visited[next]) {
					dist[next] = curr;
					visited[next] = true;
					queue.offer(next);
				}
			}
		}

		if (dist[target] == -1 && start != target) {
			// 추적할 수 없음
			return new ArrayList<>();
		}

		List<Integer> path = new ArrayList<>();
		for (int i = target; i != start; i = dist[i]) {
			path.add(i);
		}
		path.add(start);
		Collections.reverse(path);
		return path;
	}

	/// 그래프 탐색
	public static void graphSearch(List<List<Integer>> graph) {
		int v = graph.size();
		boolean[] visited = new boolean[v];

		int graphCnt = 0;
		for (int i = 0; i < v; i++) {
			if (!visited[i]) {
				System.out.print("그래프 " + (++graphCnt) + ": ");
				helper(graph, i, visited);
			}
		}
	}

	private static void helper(List<List<Integer>> graph, int start, boolean[] visited) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int curr = queue.poll();
			System.out.print(curr + " ");

			for (int next : graph.get(curr)) {
				if (!visited[next]) {
					visited[next] = true;
					queue.offer(next);
				}
			}
		}
	}
}