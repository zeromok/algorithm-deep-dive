package daniel.algorithmdeepdive.pattern.search.graph.traversal.backtracking;

import java.util.ArrayList;
import java.util.List;

/// ## 2D 그리드 백트래킹
/// ### 언제 쓰는가?
/// - "2D 그리드에서 모든 경로 탐색" → 2D 백트래킹
/// - "N칸을 선택하는 모든 경우" → 2D 백트래킹
///
/// ### 핵심 원리
/// - 4방향(상하좌우) 또는 8방향 탐색
/// - visited 2D 배열 복원
/// - 경계 체크 필수
///
/// ### 시간 복잡도: O(4^depth) - 가지치기로 최적화
public class BacktrackingGrid {
	private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	private static boolean isValid(int x, int y, int rows, int cols) {
		return x >= 0 && x < rows && y >= 0 && y < cols;
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
		List<List<int[]>> allPaths = new ArrayList<>();
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		List<int[]> path = new ArrayList<>();

		backtrack2DFindAllPaths(grid, startX, startY, targetX, targetY,
			visited, path, allPaths);
		return allPaths;
	}

	private static void backtrack2DFindAllPaths(int[][] grid, int x, int y,
		int targetX, int targetY, boolean[][] visited, List<int[]> path,
		List<List<int[]>> result) {
		// 경계 체크
		if (!isValid(x, y, grid.length, grid[0].length) || visited[x][y]) {
			return;
		}

		// 종료 조건
		if (x == targetX && y == targetY) {
			path.add(new int[]{x, y});
			result.add(new ArrayList<>(path));
			path.remove(path.size() - 1);
			return;
		}

		// 상태 변경
		visited[x][y] = true;
		path.add(new int[]{x, y});

		// 4방향 탐색
		for (int i = 0; i < DIRECTIONS.length; i++) {
			int nx = x + DIRECTIONS[i][0];
			int ny = y + DIRECTIONS[i][1];
			backtrack2DFindAllPaths(grid, nx, ny, targetX, targetY, visited, path, result);
		}

		// 상태 복원
		visited[x][y] = false;
		path.remove(path.size() - 1);
	}

	/// N칸 선택 (연결된 경로, 가지치기 포함)
	/// - 정확히 N칸을 선택하되, 인접한 칸으로만 이동
	/// - 사용 예: 테트로미노, 연결된 블록 선택
	///
	/// @param grid 2D 그리드
	/// @param n 선택할 칸 수
	/// @return 모든 가능한 선택 리스트
	public static List<List<int[]>> selectNCellsConnected(int[][] grid, int n) {
		List<List<int[]>> result = new ArrayList<>();
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		List<int[]> path = new ArrayList<>();

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				backtrackSelectNCells(grid, i, j, n, visited, path, result);
			}
		}
		return result;
	}

	private static void backtrackSelectNCells(int[][] grid, int x, int y, int n,
		boolean[][] visited, List<int[]> path, List<List<int[]>> result) {
		// 경계 체크
		if (!isValid(x, y, grid.length, grid[0].length) || visited[x][y]) {
			return;
		}

		// 가지치기: 이미 N칸 선택했으면 더 탐색 불필요
		if (path.size() >= n) {
			return;
		}

		// 상태 변경
		visited[x][y] = true;
		path.add(new int[]{x, y});

		// 종료 조건: 정확히 N칸 선택
		if (path.size() == n) {
			result.add(new ArrayList<>(path));
			visited[x][y] = false;
			path.remove(path.size() - 1);
			return;
		}

		// 4방향 탐색
		for (int i = 0; i < DIRECTIONS.length; i++) {
			int nx = x + DIRECTIONS[i][0];
			int ny = y + DIRECTIONS[i][1];
			backtrackSelectNCells(grid, nx, ny, n, visited, path, result);
		}

		// 상태 복원
		visited[x][y] = false;
		path.remove(path.size() - 1);
	}

}
