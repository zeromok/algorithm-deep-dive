package daniel.algorithmdeepdive.pattern.search.graph.traversal.backtracking.practice;

import static daniel.algorithmdeepdive.pattern.search.graph.traversal.GraphTestUtils.*;

public class BasicPractice {

	public static void main(String[] args) {
		int[][] graph = createBasicGraphMatrix();

		findAllPaths(graph, 0, 7);

		findShortestPathLength(graph, 0, 7);

		allSubsets(new int[]{1, 2, 3});
	}

	/// 모든 경로 탐색 (상태 복원)
	/// - visited 배열을 복원하여 모든 가능한 경로 탐색
	/// - 결과를 콘솔에 출력
	/// 
	/// @param graph 인접 행렬 그래프 (1:연결, 0:비연결)
	/// @param start 시작 정점
	/// @param target 목표 정점
	public static void findAllPaths(int[][] graph, int start, int target) {
		boolean[] visited = new boolean[graph.length];
		findAllPathsHelper(graph, start, target, visited, String.valueOf(start));
	}

	private static void findAllPathsHelper(int[][] graph, int start, int target, boolean[] visited, String path) {
		visited[start] = true;

		if (start == target) {
			System.out.println(path + " 도착");
		} else {
			for (int next = 0; next < graph.length; next++) {
				if (graph[start][next] == 1 && !visited[next]) {
					findAllPathsHelper(graph, next, target, visited, path + " -> " + next);
				}
			}
		}
		visited[start] = false;
	}

	/// 최적해 찾기 (가지치기 포함)
	/// - 최단 경로 길이 찾기
	/// - 가지치기: 현재 경로가 이미 최소값보다 크면 중단
	/// - 결과를 콘솔에 출력
	/// 
	/// @param graph 인접 행렬 그래프
	/// @param start 시작 정점
	/// @param target 목표 정점
	public static void findShortestPathLength(int[][] graph, int start, int target) {
		boolean[] visited = new boolean[graph.length];
		int[] minContainer = {Integer.MAX_VALUE};
		shortestPathHelper(graph, start, target, 0, visited, minContainer);
		System.out.println("최단 거리: " + (minContainer[0] == Integer.MAX_VALUE ? -1 : minContainer[0]));
	}

	private static void shortestPathHelper(int[][] graph, int start, int target,int depth, boolean[] visited, int[] min) {
		if (depth >= min[0]) return;

		if (start == target) {
			min[0] = depth;
			return;
		}

		visited[start] = true;
		for (int next = 0; next < graph.length; next++) {
			if (graph[start][next] == 1 && !visited[next]) {
				shortestPathHelper(graph, next, target, depth + 1, visited, min);
			}
		}
		visited[start] = false;
	}

	/// 모든 부분집합 생성
	/// - 각 원소를 선택/비선택하는 모든 경우
	/// - 결과를 콘솔에 출력
	/// 
	/// @param arr 원본 배열
	public static void allSubsets(int[] arr) {
		subsetsHelper(arr, 0, "");
	}

	private static void subsetsHelper(int[] arr, int idx, String current) {
		if (idx == arr.length) {
			System.out.println("{" + current + "}");
			return;
		}

		subsetsHelper(arr, idx + 1, current);

		String next = current.isEmpty() ? String.valueOf(arr[idx]) : current + ", " + arr[idx];
		subsetsHelper(arr, idx + 1, next);
	}
}
