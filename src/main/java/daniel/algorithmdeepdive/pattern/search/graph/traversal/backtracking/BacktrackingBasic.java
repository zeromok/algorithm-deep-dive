package daniel.algorithmdeepdive.pattern.search.graph.traversal.backtracking;

import java.util.ArrayList;
import java.util.List;

/// ## 백트래킹 (Backtracking) - 상태 복원 기법
/// ### 언제 쓰는가?
/// - "모든 가능한 경로/조합을 탐색" → 백트래킹
/// - "조건을 만족하는 모든 해를 찾거나 최적해 탐색" → 백트래킹
///
/// ### 핵심 원리
/// - 재귀적으로 상태를 변경하고, 탐색 후 **상태를 복원**
/// - DFS와의 차이: DFS는 visited 복원 안함 (단일 경로), 백트래킹은 복원 (모든 경로)
/// - 가지치기(Pruning)로 불필요한 탐색 차단
///
/// ### 시간 복잡도: O(상태 공간 크기) - 가지치기로 최적화 가능
/// ### 공간 복잡도: O(재귀 깊이)
public class BacktrackingBasic {

	/// 모든 경로 탐색 (상태 복원)
	/// - visited 배열을 복원하여 모든 가능한 경로 탐색
	/// - DFS findPath() 와의 차이: visited 복원 여부
	/// @param graph 인접 행렬 그래프 (1:연결, 0:비연결)
	/// @param start 시작 정점
	/// @param target 목표 정점
	/// @return start 에서 target 까지의 모든 경로 리스트
	public static List<List<Integer>> findAllPaths(int[][] graph, int start, int target) {
		List<List<Integer>> allPaths = new ArrayList<>();
		boolean[] visited = new boolean[graph.length];
		List<Integer> path = new ArrayList<>();
		findAllPathsHelper(graph, start, target, visited, path, allPaths);
		return allPaths;
	}

	private static void findAllPathsHelper(int[][] graph, int curr, int target, boolean[] visited, List<Integer> path,
		List<List<Integer>> result) {
		// 종료 조건: 목표 도달
		if (curr == target) {
			result.add(new ArrayList<>(path)); // 경로 복사본 저장
			return;
		}

		// 상태 변경
		visited[curr] = true;
		path.add(curr);

		// 재귀
		for (int next = 0; next < graph.length; next++) {
			if (graph[curr][next] == 1 && !visited[next]) {
				findAllPathsHelper(graph, next, target, visited, path, result);
			}
		}

		visited[curr] = false;
		path.remove(path.size() - 1);
	}

	/// 최적해 찾기 (가지치기 포함)
	/// - 최단 경로 길이 찾기 (BFS가 더 효율적이지만 백트래킹 연습용)
	/// - 가지치기: 현재 경로가 이미 최소값보다 크면 중단
	/// @param graph 인접 행렬 그래프
	/// @param start 시작 정점
	/// @param target 목표 정점
	/// @return 최단 경로 길이, 경로가 없으면 -1
	public static int findShortestPathLength(int[][] graph, int start, int target) {
		boolean[] visited = new boolean[graph.length];
		int[] minLength = {Integer.MAX_VALUE};

		shortestPathHelper(graph, start, target, visited, 0, minLength);
		return minLength[0] == Integer.MAX_VALUE ? -1 : minLength[0];
	}

	private static void shortestPathHelper(int[][] graph, int curr, int target, boolean[] visited, int depth,
		int[] minLength) {
		// 가지치기: 이미 더 짧은 경로를 찾았으면 중단
		if (depth >= minLength[0]) {
			return;
		}

		// 종료 조건
		if (curr == target) {
			minLength[0] = Math.min(minLength[0], depth);
			return;
		}

		// 상태 변경
		visited[curr] = true;

		// 재귀
		for (int next = 0; next < graph.length; next++) {
			if (graph[curr][next] == 1 && !visited[next]) {
				shortestPathHelper(graph, next, target, visited, depth + 1, minLength);
			}
		}

		// 상태 복원
		visited[curr] = false;
	}

	/// 모든 부분집합 생성
	/// - 각 원소를 선택/비선택하는 모든 경우
	/// - 사용 예: 부분집합 합 문제, 부분집합 개수 세기
	/// @param arr 원본 배열
	/// @return 모든 부분집합 리스트
	public static List<List<Integer>> allSubsets(int[] arr) {
		List<List<Integer>> result = new ArrayList<>();
		subsetsHelper(arr, 0, new ArrayList<>(), result);
		return result;
	}

	private static void subsetsHelper(int[] arr, int idx, List<Integer> current, List<List<Integer>> result) {
		// 종료 조건: 모든 원소 처리 완료
		if (idx == arr.length) {
			result.add(new ArrayList<>(current));
			return;
		}

		// 선택하지 않는 경우
		subsetsHelper(arr, idx + 1, current, result);

		// 선택하는 경우
		current.add(arr[idx]);
		subsetsHelper(arr, idx + 1, current, result);
		current.remove(current.size() - 1); // 상태 복원
	}

}
