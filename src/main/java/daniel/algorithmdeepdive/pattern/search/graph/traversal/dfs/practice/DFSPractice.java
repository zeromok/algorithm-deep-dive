package daniel.algorithmdeepdive.pattern.search.graph.traversal.dfs.practice;

import static daniel.algorithmdeepdive.pattern.search.graph.traversal.GraphTestUtils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import daniel.algorithmdeepdive.pattern.search.graph.traversal.GraphTestUtils;

/// ## DFS 실행 흐름 관찰
/// ### 목표
/// - 재귀 호출 깊이와 백트래킹 과정
/// - 한 경로를 끝까지 탐색하는 과정
/// - Stack vs 재귀 비교
public class DFSPractice {

	public static void main(String[] args) {
		// 인접 리스트 그래프
		List<List<Integer>> listGraph = createBasicGraph();

		// 인접 행렬 그래프
		int[][] matrixGraph = createBasicGraphMatrix();

		// 다중 컴포넌트 그래프 (인접 리스트)
		List<List<Integer>> multipleListGraph = createMultipleComponentsGraph();

		// 다중 컴포넌트 그래프 (인접 행렬)
		int[][] multipleMatrixGraph = createMultipleComponentsGraphMatrix();
	}



	/// 스택
	public static void dfsWithStack(List<List<Integer>> graph, int start) {
		;;
	}

	/// 재귀
	public static void dfsRecursion(List<List<Integer>> graph, int start) {
		;;
	}

	private static void recursionHelper(List<List<Integer>> graph, Integer start, boolean[] visited) {
		;;
	}

	/// 경로 추적
	public static List<Integer> findPath(List<List<Integer>> graph, int start, int target) {
		;;
		return null;
	}

	private static boolean pathHelper(List<List<Integer>> graph, int start, int target, boolean[] visited,
		List<Integer> path) {
		;;
		return false;
	}

	/// 그래프 탐색
	public static void countComponents(List<List<Integer>> graph) {
		;;
	}
}