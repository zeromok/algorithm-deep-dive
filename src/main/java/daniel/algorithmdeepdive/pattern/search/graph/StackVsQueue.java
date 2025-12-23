package daniel.algorithmdeepdive.pattern.search.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/// 같은 그래프, 다른 자료구조 -> 다른 순서
public class StackVsQueue {

	public static void main(String[] args) {
		System.out.println("\n==== DFS (스택) ====");
		dfsWithStack();

		System.out.println("\n==== BFS (큐) ====");
		bfsWithQueue();
	}

	private static void bfsWithQueue() {
		List<List<Integer>> graph = buildGraph();
		boolean[] visited = new boolean[7];
		Queue<Integer> queue = new LinkedList<>();

		queue.offer(1); // 시작 정점: 1
		visited[1] = true;
		while (!queue.isEmpty()) {
			int v = queue.poll();
			System.out.print(v + " ");

			for (Integer next : graph.get(v)) {
				if (!visited[next]) {
					queue.offer(next);
					visited[next] = true;
				}
			}

		}
	}

	private static void dfsWithStack() {
		List<List<Integer>> graph = buildGraph();
		boolean[] visited = new boolean[7];
		Stack<Integer> stack = new Stack<>();

		stack.push(1); // 시작 정점: 1
		while (!stack.isEmpty()) {
			int v = stack.pop();
			if (visited[v]) continue;
			visited[v] = true;
			System.out.print(v + " ");

			List<Integer> next = graph.get(v);
			for (int i = 0; i < next.size(); i++) {
				if (!visited[next.get(i)]) {
					stack.push(next.get(i));
				}
			}
		}
	}

	private static List<List<Integer>> buildGraph() {
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			graph.add(new ArrayList<>());
		}

		graph.get(1).add(2);
		graph.get(1).add(3);
		graph.get(2).add(4);
		graph.get(2).add(5);
		graph.get(3).add(6);

		return graph;
	}
}
