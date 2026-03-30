package daniel.algorithmdeepdive.pattern.sort.topologicalsort;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TopologicalSort {

	public static void main(String[] args) {
		int v = 6;
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i <= v; i++) {
			graph.add(new ArrayList<>());
		}

		// 1: 2, 3
		graph.get(1).add(2);
		graph.get(1).add(3);

		// 2: 5
		graph.get(2).add(5);

		// 3: 5
		graph.get(3).add(5);

		// 4: 1, 6
		graph.get(4).add(1);
		graph.get(4).add(6);

		topologicalSort(graph);
	}

	private static void topologicalSort(List<List<Integer>> graph) {
		int v = graph.size();
		boolean[] visited = new boolean[v];
		Stack<Integer> stack = new Stack<>();

		// 모든 노드에 대해 DFS 수행 (연결되지 않은 그래프 고려)
		for (int i = 0; i < v; i++) {
			if (!visited[i]) {
				dfs(i, visited, stack, graph);
			}
		}

		// 결과 출력 (스택에서 꺼내면 위상 정렬 순서)
		while (!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
		}
	}

	private static void dfs(int v, boolean[] visited, Stack<Integer> stack, List<List<Integer>> graph) {
		visited[v] = true;

		for (int neighbor : graph.get(v)) {
			if (!visited[neighbor]) {
				dfs(neighbor, visited, stack, graph);
			}
		}

		// 자식 노드 방문이 모두 끝난 '역순'으로 스택에 담깁니다.
		stack.push(v);
	}
}
