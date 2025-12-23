package daniel.algorithmdeepdive.pattern.search.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/// # DFS(Depth-First Search) 구현 - 깊이 우선 탐색
/// ## 핵심 원리
/// - 한 경로를 끝까지 탐색 후 백트래킹
/// - Stack 자료구조 사용 (재귀 = 콜스택)
/// - 경로 추적, 사이클 탐지, 위상정렬에 유용
/// - 시간 복잡도: O(V + E)
/// ## 장점
/// - 재귀로 인해 코드가 짧음
/// - 메모리 적음 (큐 안씀)
/// - 단순 연결성 확인에는 충분
public class DFS {
	private List<List<Integer>> graph;
	private boolean[] visited;
	private int vertices;

	public DFS(int vertices) {
		this.vertices = vertices;
		this.graph = new ArrayList<>();
		this.visited = new boolean[vertices];

		for (int i = 0; i < vertices; i++) {
			graph.add(new ArrayList<>());
		}
	}

	// 무방향
	public void addEdge(int from, int to) {
		graph.get(from).add(to);
		graph.get(to).add(from);
	}

	/// ## 방법 1: 재귀 DFS (가장 직관적)
	public void dfsRecursive(int start) {
		System.out.println("\n=== DFS 재귀 (시작: " + start + ") ===");
		Arrays.fill(visited, false);
		dfsRecursiveHelper(start);
	}

	private void dfsRecursiveHelper(int v) {
		visited[v] = true;
		System.out.print(v + " ");

		// 인접한 모든 정점 탐색
		for (Integer neighbor : graph.get(v)) {
			if (!visited[neighbor]) {
				dfsRecursiveHelper(neighbor);
			}
		}
	}

	/// ## 방법 2: 스택 DFS (반복문)
	public void dfsIterative(int start) {
		System.out.println("\n=== DFS 스택 (시작: " + start + ") ===");
		Arrays.fill(visited, false);

		Stack<Integer> stack = new Stack<>();
		stack.push(start);

		while (!stack.empty()) {
			Integer v = stack.pop();

			if (visited[v]) continue;
			visited[v] = true;
			System.out.print(v + " ");

			// 인접 정점을 역순으로 푸시 (재귀와 동일한 순서 유지)
			List<Integer> neighbors = graph.get(v);
			for (int i = neighbors.size() - 1; i >= 0; i--) {
				Integer neighbor = neighbors.get(i);
				if (!visited[neighbor]) {
					stack.push(neighbor);
				}
			}
		}
		System.out.println();
	}

	// 모든 그래프 탐색
	public void dfsAll() {
		System.out.println("\n=== 모든 그래프 ===");
		Arrays.fill(visited, false);
		int componentCount = 0;

		for (int i = 0; i < vertices; i++) {
			if (!visited[i]) {
				System.out.print("그래프 " + (++componentCount) + ": ");
				dfsRecursiveHelper(i);
				System.out.println();
			}
		}
	}

	// 경로 추적
	public List<Integer> findPath(int start, int target) {
		Arrays.fill(visited, false);
		List<Integer> path = new ArrayList<>();

		if (dfsPathHelper(start, target, path)) {
			return path;
		}
		return new ArrayList<>();
	}

	private boolean dfsPathHelper(int curr, int target, List<Integer> path) {
		visited[curr] = true;
		path.add(curr);

		if (curr == target) {
			return true;
		}

		for (Integer neighbor : graph.get(curr)) {
			if (!visited[neighbor]) {
				if (dfsPathHelper(neighbor, target, path)) {
					return true;
				}
			}
		}

		path.remove(path.size() - 1);
		return false;
	}
}
