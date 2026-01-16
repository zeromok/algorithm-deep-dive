package daniel.algorithmdeepdive.pattern.search.graph.traversal.dfs.practice;

import static daniel.algorithmdeepdive.pattern.search.graph.traversal.GraphTestUtils.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


public class DFSPracticeList {

	public static void main(String[] args) {
		// 인접 리스트 그래프
		List<List<Integer>> listGraph = createBasicGraph();

		// 다중 컴포넌트 그래프 (인접 리스트)
		List<List<Integer>> multipleListGraph = createMultipleComponentsGraph();

		System.out.println("=== 스택 DFS ===");
		dfsWithStack(listGraph, 0);
		System.out.println("\n");

		System.out.println("=== 재귀 DFS ===");
		dfsRecursionForList(listGraph, 0);
		System.out.println("\n");

		System.out.println("=== 경로 찾기 (0 -> 7) ===");
		List<Integer> path = findPath(listGraph, 0, 7);
		System.out.println(path);
		System.out.println();

		System.out.println("=== 연결 컴포넌트 개수 ===");
		int count = getComponentCount(listGraph);
		System.out.println("컴포넌트 개수: " + count);
	}


	/// 스택
	public static void dfsWithStack(List<List<Integer>> graph, int start) {
		int v = graph.size();
		boolean[] visited = new boolean[v];

		Stack<Integer> stack = new Stack<>();
		stack.push(start);

		while (!stack.isEmpty()) {
			int curr = stack.pop();

			if (visited[curr]) continue;

			visited[curr] = true;
			System.out.print(curr + " ");

			List<Integer> nextList = graph.get(curr);
			for (int i = nextList.size() - 1; i >= 0; i--) {
				int next = nextList.get(i);
				if (!visited[next]) {
					stack.push(next);
				}
			}
		}
	}

	/// 재귀
	public static void dfsRecursionForList(List<List<Integer>> graph, int start) {
		int v = graph.size();
		boolean[] visited = new boolean[v];

		recursionHelperForList(graph, start, visited);
	}

	private static void recursionHelperForList(List<List<Integer>> graph, Integer start, boolean[] visited) {
		visited[start] = true;
		System.out.print(start + " ");

		for (Integer next : graph.get(start)) {
			if (!visited[next]) {
				recursionHelperForList(graph, next, visited);
			}
		}
	}

	/// 경로 추적
	public static List<Integer> findPath(List<List<Integer>> graph, int start, int target) {
		int v = graph.size();
		boolean[] visited = new boolean[v];
		Arrays.fill(visited, false);
		List<Integer> path = new ArrayList<>();

		if (pathHelper(graph, start, target, visited, path)) {
			return path;
		}

		return new ArrayList<>();
	}

	private static boolean pathHelper(List<List<Integer>> graph, int start, int target, boolean[] visited,
		List<Integer> path) {
		visited[start] = true;
		path.add(start);

		if (start == target)
			return true;

		for (Integer next : graph.get(start)) {
			if (!visited[next]) {
				if (pathHelper(graph, next, target, visited, path)) {
					return true;
				}
			}
		}

		path.remove(path.size() - 1);
		return false;
	}

	/// 그래프 탐색
	public static int getComponentCount(List<List<Integer>> graph) {
		int v = graph.size();
		boolean[] visited = new boolean[v];
		int count = 0;

		for (int i = 0; i < v; i++) {
			if (!visited[i]) {
				recursionHelperForList(graph, i, visited);
				count++;
			}
		}
		return count;
	}
}