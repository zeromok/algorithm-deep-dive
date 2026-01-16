package daniel.algorithmdeepdive.pattern.search.graph.traversal.bfs.practice;

import static daniel.algorithmdeepdive.pattern.search.graph.traversal.GraphTestUtils.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSPracticeMatrix {

	public static void main(String[] args) {
		// 인접 행렬 그래프
		int[][] matrixGraph = createBasicGraphMatrix();

		// 다중 컴포넌트 그래프 (인접 행렬)
		int[][] multipleMatrixGraph = createMultipleComponentsGraphMatrix();

		System.out.println("=== 기본 BFS ===");
		bfsForMatrix(matrixGraph, 0);
		System.out.println("\n");

		System.out.println("=== 레벨별 BFS ===");
		bfsWithLevel(matrixGraph, 0);
		System.out.println();

		System.out.println("=== 최단 거리 ===");
		int[] distances = shortDistForMatrix(matrixGraph, 0);
		System.out.println(Arrays.toString(distances));
		System.out.println();

		System.out.println("=== 최단 경로 (0 -> 7) ===");
		List<Integer> path = shortestPath(matrixGraph, 0, 7);
		System.out.println(path);
		System.out.println();

		System.out.println("=== 연결 컴포넌트 개수 ===");
		int count = getComponentCountForMatrix(multipleMatrixGraph);
		System.out.println("컴포넌트 개수: " + count);
	}


	/// 기본 구현
	public static void bfsForMatrix(int[][] graph, int start) {
		int v = graph.length;
		boolean[] visited = new boolean[v];

		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int curr = queue.poll();
			System.out.print(curr + " ");

			for (int i = 0; i < v; i++) {
				if (graph[curr][i] == 1 && !visited[i]) {
					queue.offer(i);
					visited[i] = true;
				}
			}
		}
	}

	/// 거리 계산
	public static void bfsWithLevel(int[][] graph, int start) {
		int v = graph.length;
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

				for (int next = 0; next < v; next++) {
					if (graph[curr][next] == 1 && !visited[next]) {
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
	public static int[] shortDistForMatrix(int[][] graph, int start) {
		int v = graph.length;
		int[] dist = new int[v];
		Arrays.fill(dist, -1);

		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		dist[start] = 0;

		while (!queue.isEmpty()) {
			int curr = queue.poll();

			for (int i = 0; i < v; i++) {
				if (graph[curr][i] == 1 && dist[i] == -1) {
					dist[i] = dist[curr] + 1;
					queue.offer(i);
				}
			}
		}
		return dist;
	}

	/// 최단 경로 추적
	public static List<Integer> shortestPath(int[][] graph, int start, int target) {
		int v = graph.length;
		boolean[] visited = new boolean[v];
		int[] parent = new int[v];
		Arrays.fill(parent, -1);

		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int curr = queue.poll();

			if (curr == target) break;

			for (int next = 0; next < v; next++) {
				if (graph[curr][next] == 1 && !visited[next]) {
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
	public static int getComponentCountForMatrix(int[][] graph) {
		int v = graph.length;
		boolean[] visited = new boolean[v];
		int count = 0;

		for (int i = 0; i < v; i++) {
			if (!visited[i]) {
				componentSearchForMatrix(graph, i, visited);
				count++;
			}
		}
		return count;
	}

	private static void componentSearchForMatrix(int[][] graph, int start, boolean[] visited) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int curr = queue.poll();

			for (int next = 0; next < graph.length; next++) {
				if (graph[curr][next] == 1 && !visited[next]) {
					visited[next] = true;
					queue.offer(next);
				}
			}
		}
	}
}
