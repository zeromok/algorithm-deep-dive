package daniel.algorithmdeepdive.pattern.search.graph.traversal.bfs.practice;

import static daniel.algorithmdeepdive.pattern.search.graph.traversal.GraphTestUtils.*;
import static daniel.algorithmdeepdive.pattern.search.graph.traversal.GraphTestUtils.createBasicGraphMatrix;
import static daniel.algorithmdeepdive.pattern.search.graph.traversal.GraphTestUtils.createMultipleComponentsGraph;
import static daniel.algorithmdeepdive.pattern.search.graph.traversal.GraphTestUtils.createMultipleComponentsGraphMatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class BFSPracticeList {

	public static void main(String[] args) {
		// 인접 리스트 그래프
		List<List<Integer>> listGraph = createBasicGraph();

		// 다중 컴포넌트 그래프 (인접 리스트)
		List<List<Integer>> multipleListGraph = createMultipleComponentsGraph();

		System.out.println("=== 기본 BFS ===");
		bfsForList(listGraph, 0);
		System.out.println("\n");

		System.out.println("=== 레벨별 BFS ===");
		bfsWithLevel(listGraph, 0);
		System.out.println();

		System.out.println("=== 최단 거리 ===");
		int[] distances = shortDistForList(listGraph, 0);
		System.out.println(Arrays.toString(distances));
		System.out.println();

		System.out.println("=== 최단 경로 (0 -> 7) ===");
		List<Integer> path = shortestPath(listGraph, 0, 7);
		System.out.println(path);
		System.out.println();

		System.out.println("=== 연결 컴포넌트 개수 ===");
		int count = getComponentCountForList(multipleListGraph);
		System.out.println("컴포넌트 개수: " + count);
	}
	/// 기본 구현
	public static void bfsForList(List<List<Integer>> graph, int start) {
		int v = graph.size();
		boolean[] visited = new boolean[v];

		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int curr = queue.poll();
			System.out.print(curr + " ");

			for (Integer next : graph.get(curr)) {
				if (!visited[next]) {
					queue.offer(next);
					visited[next] = true;
				}
			}
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
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				int curr = queue.poll();
				System.out.print(curr + " ");

				for (Integer next : graph.get(curr)) {
					if (!visited[next]) {
						visited[next] = true;
						queue.offer(next);
					}
				}
			}
			System.out.println("(Level " + level + ")");
			level++;
		}
	}

	/// 최단 거리 계산
	public static int[] shortDistForList(List<List<Integer>> graph, int start) {
		int v = graph.size();
		int[] dist = new int[v];
		Arrays.fill(dist, -1);

		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		dist[start] = 0;

		while (!queue.isEmpty()) {
			int curr = queue.poll();

			for (Integer next : graph.get(curr)) {
				if (dist[next] == -1) {
					dist[next] = dist[curr] + 1;
					queue.offer(next);
				}
			}
		}
		return dist;
	}

	/// 최단 경로 추적
	public static List<Integer> shortestPath(List<List<Integer>> graph, int start, int target) {
		int v = graph.size();
		boolean[] visited = new boolean[v];
		int[] parent = new int[v];
		Arrays.fill(parent, -1);

		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int curr = queue.poll();

			if (curr == target) break;

			for (Integer next : graph.get(curr)) {
				if (!visited[next]) {
					parent[next] = curr;
					visited[next] = true;
					queue.offer(next);
				}
			}
		}

		if (parent[target] == -1 && start != target) {
			return new ArrayList<>();
		}

		List<Integer> path = new ArrayList<>();
		for (int i = target; i != start; i = parent[i]) {
			path.add(i);
		}
		path.add(start);
		Collections.reverse(path);
		return path;
	}

	/// 그래프 탐색
	public static int getComponentCountForList(List<List<Integer>> graph) {
		int v = graph.size();
		boolean[] visited = new boolean[v];
		int count = 0;

		for (int i = 0; i < v; i++) {
			if (!visited[i]) {
				componentSearchForList(graph, i, visited);
				count++;
			}
		}
		return count;
	}

	private static void componentSearchForList(List<List<Integer>> graph, int start, boolean[] visited) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int curr = queue.poll();

			for (Integer next : graph.get(curr)) {
				if (!visited[next]) {
					visited[next] = true;
					queue.offer(next);
				}
			}
		}
	}
}