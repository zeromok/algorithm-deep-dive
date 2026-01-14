package daniel.algorithmdeepdive.pattern.search.graph.traversal.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/// ## DFS(Depth-First Search) - 깊이 우선 탐색
/// ### 언제 쓰는가?
/// - "경로 추적, 사이클 탐지" → DFS
/// ### 핵심 원리
/// - 한 경로를 끝까지 탐색 후 백트래킹
/// - Stack 자료구조 사용 (재귀 = 콜스택)
/// - 경로 추적, 사이클 탐지, 위상정렬에 유용
/// ### 시간 복잡도: O(V + E)
/// ### 장점
/// - 재귀로 인해 코드가 짧음
/// - 메모리 적음 (큐 안씀)
/// - 단순 연결성 확인에는 충분
public class DFS {

	/// 재귀 DFS (가장 직관적)
	/// - 시스템의 호출 스택 사용
	/// - 그래프가 너무 깊으면 StackOverflowError 발생 가능
	public static void dfsRecursive(List<List<Integer>> graph, int start) {
		int vertices = graph.size();
		boolean[] visited = new boolean[vertices];

		dfsRecursiveHelper(graph, start, visited);
	}

	private static void dfsRecursiveHelper(List<List<Integer>> graph, int v, boolean[] visited) {
		visited[v] = true;
		System.out.print(v + " ");

		for (Integer neighbor : graph.get(v)) {
			if (!visited[neighbor]) {
				dfsRecursiveHelper(graph, neighbor, visited);
			}
		}
	}

	/// 스택 DFS (반복문)
	/// - 개발자가 직접 정의한 Stack 사용
	/// - 메모리 허용 범위 내에서 안전
	/// - 스택 특성상 넣는 순서의 반대로 탐색
	public static void dfsIterative(List<List<Integer>> graph, int start) {
		int vertices = graph.size();
		boolean[] visited = new boolean[vertices];
		Stack<Integer> stack = new Stack<>();

		stack.push(start);

		while (!stack.isEmpty()) {
			Integer v = stack.pop();

			if (visited[v]) {
				continue;
			}

			visited[v] = true;
			System.out.print(v + " ");

			List<Integer> neighbors = graph.get(v);
			for (int i = neighbors.size() - 1; i >= 0; i--) {
				Integer neighbor = neighbors.get(i);
				if (!visited[neighbor]) {
					stack.push(neighbor);
				}
			}
		}
	}

	/// 모든 그래프 탐색
	/// - 연결 컴포넌트별로 DFS 수행
	public static int countComponents(List<List<Integer>> graph) {
		int vertices = graph.size();
		boolean[] visited = new boolean[vertices];

		int count = 0;

		for (int i = 0; i < vertices; i++) {
			if (!visited[i]) {
				dfsRecursiveHelper(graph, i, visited);
				count++;
			}
		}
		return count;
	}

	/// 경로 추적 (백트레킹)
	/// - start에서 target까지의 경로 반환 (최단 경로 보장 안됨)
	public static List<Integer> findPath(List<List<Integer>> graph, int start, int target) {
		int vertices = graph.size();
		boolean[] visited = new boolean[vertices];
		Arrays.fill(visited, false);
		List<Integer> path = new ArrayList<>();

		if (dfsPathHelper(graph, start, target, visited, path)) {
			return path;
		}
		return new ArrayList<>();
	}

	private static boolean dfsPathHelper(List<List<Integer>> graph, int curr, int target,
		boolean[] visited, List<Integer> path) {
		visited[curr] = true;
		path.add(curr);

		if (curr == target) {
			return true;
		}

		for (Integer neighbor : graph.get(curr)) {
			if (!visited[neighbor]) {
				if (dfsPathHelper(graph, neighbor, target, visited, path)) {
					return true;
				}
			}
		}

		path.remove(path.size() - 1);
		return false;
	}
}