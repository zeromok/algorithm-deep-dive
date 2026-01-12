package daniel.algorithmdeepdive.pattern.search.graph.traversal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import daniel.algorithmdeepdive.pattern.search.graph.traversal.practice.DFSPractice;

/// # DFS(Depth-First Search) 구현 - 깊이 우선 탐색
/// ## 언제 쓰는가?
/// - "경로 추적, 사이클 탐지" → DFS
///
/// ## 핵심 원리
/// - 한 경로를 끝까지 탐색 후 백트래킹
/// - Stack 자료구조 사용 (재귀 = 콜스택)
/// - 경로 추적, 사이클 탐지, 위상정렬에 유용
/// - 시간 복잡도: O(V + E)
/// ## 장점
/// - 재귀로 인해 코드가 짧음
/// - 메모리 적음 (큐 안씀)
/// - 단순 연결성 확인에는 충분
///
/// ## 실험
/// @see DFSPractice
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
	/// - 관리 방식: 시스템의 호출 스택 사용 (코드가 매우 직관적이고 짧음)
	/// - 에러 위험: 그래프가 너무 깊으면 {@code StackOverflowError} 발생 가능
	/// - 순서 제어: 인접 리스트의 앞에서 부터 자연스럽게 탐색
	public void dfsRecursive(int start) {
		Arrays.fill(visited, false);
		System.out.println("\n=== DFS 재귀 (시작: " + start + ") ===");
		dfsRecursiveHelper(start);
	}

	private void dfsRecursiveHelper(int v) {
		// 1. 방문 처리
		visited[v] = true;
		System.out.print(v + " ");

		// 2. 현재 정점 v 와 연결된 모든 인접 정점을 하나씩 꺼냄
		for (Integer neighbor : graph.get(v)) {
			// 2-1. 방문하지 않은 정점이면?
			if (!visited[neighbor]) {
				// 2-2. 정점 방문 (깊게 들어감)
				dfsRecursiveHelper(neighbor);
			}
		}
	}

	/// ## 방법 2: 스택 DFS (반복문)
	/// - 관리 방식: 개발자가 직접 정의한 {@code Stack} 사용 (상대적으로 코드가 길고 복잡함)
	/// - 에러 위험: 메모리 허용 범위 내에서 안전
	/// - 순서 제어: 스택 특성상 넣는 순서의 반대로 탐색하게 됨
	public void dfsIterative(int start) {
		Arrays.fill(visited, false);
		System.out.println("\n=== DFS 스택 (시작: " + start + ") ===");

		// 1. 방문할 정점들을 담아둘 스택 생성
		Stack<Integer> stack = new Stack<>();
		// 2. 시작 정점을 넣음
		stack.push(start);

		// 3. 스택에 정점이 없을 때까지 반복
		while (!stack.isEmpty()) {
			// 3-1. 가장 최근에 넣은 정점을 꺼냄
			Integer v = stack.pop();
			System.out.print(v + " ");

			// 3-2. 방문한 정점일 시 다음으로 넘어감 (꺼낼 때, 중복 방지)
			if (visited[v]) {
				continue;
			}

			// 3-3. 방문 처리
			visited[v] = true;

			// 3-4. 현재 정점의 인접 정점 리스트 가져옴
			List<Integer> neighbors = graph.get(v);
			// 3-5. 역순으로 순회 왜? 스택: LIFO 구조
			for (int i = neighbors.size() - 1; i >= 0; i--) {
				Integer neighbor = neighbors.get(i);
				// 3-6. 아직 방문하지 않은 인접 정점들을 모두 스택에 넣음 (넣을 때, 중복 방지)
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
