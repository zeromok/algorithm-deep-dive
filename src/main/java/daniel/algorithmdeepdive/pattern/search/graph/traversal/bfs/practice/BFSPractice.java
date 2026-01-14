package daniel.algorithmdeepdive.pattern.search.graph.traversal.bfs.practice;

import static daniel.algorithmdeepdive.pattern.search.graph.traversal.GraphTestUtils.*;
import static daniel.algorithmdeepdive.pattern.search.graph.traversal.GraphTestUtils.createBasicGraphMatrix;
import static daniel.algorithmdeepdive.pattern.search.graph.traversal.GraphTestUtils.createMultipleComponentsGraph;
import static daniel.algorithmdeepdive.pattern.search.graph.traversal.GraphTestUtils.createMultipleComponentsGraphMatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/// ## BFS 실행 흐름 관찰
/// ### 목표
/// - Queue에 넣는 순서와 꺼내는 순서
/// - 레벨별 탐색 과정
/// - "최단 거리" 보장 과정
public class BFSPractice {

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

	/// 기본 구현
	public static void bfs(List<List<Integer>> graph, int start) {
		;;
	}

	/// 거리 계산
	public static void bfsWithLevel(List<List<Integer>> graph, int start) {
		;;
	}

	/// 최단 거리 계산
	public static int[] shortestDistances(List<List<Integer>> graph, int start) {
		;;
		return null;
	}

	/// 최단 경로 추적
	public static List<Integer> shortestPath(List<List<Integer>> graph, int start, int target) {
		;;
		return null;
	}

	/// 그래프 탐색
	public static void countComponents(List<List<Integer>> graph) {
		;;
	}

	private static void bfsComponent(List<List<Integer>> graph, int start, boolean[] visited) {
		;;
	}
}