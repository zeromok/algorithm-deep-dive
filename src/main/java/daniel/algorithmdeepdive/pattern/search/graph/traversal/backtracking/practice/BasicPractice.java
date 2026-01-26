package daniel.algorithmdeepdive.pattern.search.graph.traversal.backtracking.practice;

import static daniel.algorithmdeepdive.pattern.search.graph.traversal.GraphTestUtils.*;

import java.util.ArrayList;
import java.util.List;

public class BasicPractice {

	public static void main(String[] args) {
		int[][] graph = createBasicGraphMatrix();

		System.out.println("=== 모든 경로 탐색 (0 -> 7) ===");
		List<List<Integer>> allPaths = findAllPaths(graph, 0, 7);
		System.out.println("총 경로 수: " + allPaths.size());
		for (int i = 0; i < Math.min(3, allPaths.size()); i++) {
			System.out.println("경로 " + (i + 1) + ": " + allPaths.get(i));
		}
		System.out.println();

		System.out.println("=== 최단 경로 길이 (0 -> 7) ===");
		int shortest = findShortestPathLength(graph, 0, 7);
		System.out.println("최단 경로 길이: " + shortest);
		System.out.println();

		System.out.println("=== 부분집합 생성 ===");
		int[] arr = {1, 2, 3};
		List<List<Integer>> subsets = allSubsets(arr);
		for (List<Integer> subset : subsets) {
			System.out.println(subset);
		}
	}

	/// 모든 경로 탐색 (상태 복원)
	/// - visited 배열을 복원하여 모든 가능한 경로 탐색
	/// 
	/// @param graph 인접 행렬 그래프 (1:연결, 0:비연결)
	/// @param start 시작 정점
	/// @param target 목표 정점
	/// @return start에서 target까지의 모든 경로 리스트
	public static List<List<Integer>> findAllPaths(int[][] graph, int start, int target) {
		// TODO: 구현
		return new ArrayList<>();
	}

	/// 최적해 찾기 (가지치기 포함)
	/// - 최단 경로 길이 찾기
	/// - 가지치기: 현재 경로가 이미 최소값보다 크면 중단
	/// 
	/// @param graph 인접 행렬 그래프
	/// @param start 시작 정점
	/// @param target 목표 정점
	/// @return 최단 경로 길이, 경로가 없으면 -1
	public static int findShortestPathLength(int[][] graph, int start, int target) {
		// TODO: 구현
		return -1;
	}

	/// 모든 부분집합 생성
	/// - 각 원소를 선택/비선택하는 모든 경우
	/// 
	/// @param arr 원본 배열
	/// @return 모든 부분집합 리스트
	public static List<List<Integer>> allSubsets(int[] arr) {
		// TODO: 구현
		return new ArrayList<>();
	}
}
