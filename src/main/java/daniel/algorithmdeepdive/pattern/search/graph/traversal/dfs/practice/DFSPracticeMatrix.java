package daniel.algorithmdeepdive.pattern.search.graph.traversal.dfs.practice;

import static daniel.algorithmdeepdive.pattern.search.graph.traversal.GraphTestUtils.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class DFSPracticeMatrix {

	public static void main(String[] args) {
		// 인접 행렬 그래프
		int[][] matrixGraph = createBasicGraphMatrix();

		// 다중 컴포넌트 그래프 (인접 행렬)
		int[][] multipleMatrixGraph = createMultipleComponentsGraphMatrix();

		System.out.println("=== 스택 DFS ===");
		dfsWithStack(matrixGraph, 0);
		System.out.println("\n");

		System.out.println("=== 재귀 DFS ===");
		dfsRecursionForMatrix(matrixGraph, 0);
		System.out.println("\n");

		System.out.println("=== 경로 찾기 (0 -> 7) ===");
		List<Integer> path = findPath(matrixGraph, 0, 7);
		System.out.println(path);
		System.out.println();

		System.out.println("=== 연결 컴포넌트 개수 ===");
		int count = getComponentCount(matrixGraph);
		System.out.println("컴포넌트 개수: " + count);
	}


	/// 스택
	public static void dfsWithStack(int[][] graph, int start) {
		int v = graph.length;
		boolean[] visited = new boolean[v];

		Stack<Integer> stack = new Stack<>();
		stack.push(start);

		while (!stack.isEmpty()) {
			int curr = stack.pop();

			if (visited[curr]) continue;

			visited[curr] = true;
			System.out.print(curr + " ");

			// 역순으로 순회하여 인접리스트와 동일한 탐색 순서 유지
			for (int next = v - 1; next >= 0; next--) {
				if (graph[curr][next] == 1 && !visited[next]) {
					stack.push(next);
				}
			}
		}
	}

	/// 재귀
	public static void dfsRecursionForMatrix(int[][] graph, int start) {
		int v = graph.length;
		boolean[] visited = new boolean[v];

		recursionHelperForMatrix(graph, start, visited);
	}

	private static void recursionHelperForMatrix(int[][] graph, int start, boolean[] visited) {
		visited[start] = true;
		System.out.print(start + " ");

		for (int next = 0; next < graph.length; next++) {
			if (graph[start][next] == 1 && !visited[next]) {
				recursionHelperForMatrix(graph, next, visited);
			}
		}
	}

	/// 경로 추적
	public static List<Integer> findPath(int[][] graph, int start, int target) {
		int v = graph.length;
		boolean[] visited = new boolean[v];
		Arrays.fill(visited, false);
		List<Integer> path = new ArrayList<>();

		if (pathHelper(graph, start, target, visited, path)) {
			return path;
		}

		return new ArrayList<>();
	}

	private static boolean pathHelper(int[][] graph, int start, int target, boolean[] visited,
		List<Integer> path) {
		visited[start] = true;
		path.add(start);

		if (start == target)
			return true;

		for (int next = 0; next < graph.length; next++) {
			if (graph[start][next] == 1 && !visited[next]) {
				if (pathHelper(graph, next, target, visited, path)) {
					return true;
				}
			}
		}

		path.remove(path.size() - 1);
		return false;
	}

	/// 그래프 탐색
	public static int getComponentCount(int[][] graph) {
		int v = graph.length;
		boolean[] visited = new boolean[v];
		int count = 0;

		for (int i = 0; i < v; i++) {
			if (!visited[i]) {
				recursionHelperForMatrix(graph, i, visited);
				count++;
			}
		}
		return count;
	}
}
