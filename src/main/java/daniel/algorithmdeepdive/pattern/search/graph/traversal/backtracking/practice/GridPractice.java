package daniel.algorithmdeepdive.pattern.search.graph.traversal.backtracking.practice;

import java.util.ArrayList;
import java.util.List;

public class GridPractice {

	public static void main(String[] args) {
		int[][] grid = {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9}
		};

		System.out.println("=== 모든 경로 탐색 (0,0) -> (2,2) ===");
		List<List<int[]>> allPaths = findAllPaths2D(grid, 0, 0, 2, 2);
		System.out.println("총 경로 수: " + allPaths.size());
		if (allPaths.size() > 0) {
			System.out.print("첫 번째 경로: ");
			for (int[] point : allPaths.get(0)) {
				System.out.print("(" + point[0] + "," + point[1] + ") ");
			}
			System.out.println();
		}
		System.out.println();

		System.out.println("=== 4칸 선택 (연결된 경로) ===");
		List<List<int[]>> selections = selectNCellsConnected(grid, 4);
		System.out.println("총 경우 수: " + selections.size());
		if (selections.size() > 0) {
			System.out.print("첫 번째 선택: ");
			for (int[] point : selections.get(0)) {
				System.out.print("(" + point[0] + "," + point[1] + ") ");
			}
			System.out.println();
		}
	}

	/// 모든 경로 탐색 (2D 그리드)
	/// - (startX, startY)에서 (targetX, targetY)까지 모든 경로
	/// 
	/// @param grid 2D 그리드
	/// @param startX 시작 X 좌표
	/// @param startY 시작 Y 좌표
	/// @param targetX 목표 X 좌표
	/// @param targetY 목표 Y 좌표
	/// @return 모든 경로 리스트 (각 경로는 좌표 배열 리스트)
	public static List<List<int[]>> findAllPaths2D(int[][] grid, int startX, int startY,
		int targetX, int targetY) {
		// TODO: 구현
		return new ArrayList<>();
	}

	/// N칸 선택 (연결된 경로, 가지치기 포함)
	/// - 정확히 N칸을 선택하되, 인접한 칸으로만 이동
	/// - 사용 예: 테트로미노, 연결된 블록 선택
	/// 
	/// @param grid 2D 그리드
	/// @param n 선택할 칸 수
	/// @return 모든 가능한 선택 리스트
	public static List<List<int[]>> selectNCellsConnected(int[][] grid, int n) {
		// TODO: 구현
		return new ArrayList<>();
	}
}
