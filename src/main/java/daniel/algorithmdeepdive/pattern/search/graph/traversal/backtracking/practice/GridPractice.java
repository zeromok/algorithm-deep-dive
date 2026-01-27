package daniel.algorithmdeepdive.pattern.search.graph.traversal.backtracking.practice;

public class GridPractice {

	private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	public static void main(String[] args) {
		int[][] grid = {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9}
		};

		System.out.println("모든 경로 탐색");
		findAllPaths2D(grid, 0, 0, 2, 2);

		System.out.println("N 칸 탐색");
		selectNCellsConnected(grid, 4);
	}

	/// 모든 경로 탐색 (2D 그리드)
	/// - (startX, startY)에서 (targetX, targetY)까지 모든 경로
	/// - 결과를 콘솔에 출력
	/// 
	/// @param grid 2D 그리드
	/// @param startX 시작 X 좌표
	/// @param startY 시작 Y 좌표
	/// @param targetX 목표 X 좌표
	/// @param targetY 목표 Y 좌표
	public static void findAllPaths2D(int[][] grid, int startX, int startY,
		int targetX, int targetY) {
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		findAllPaths2DHelper(grid, startX, startY, targetX, targetY, visited,
			"(" + startX + "," + startY + ")");
	}

	private static void findAllPaths2DHelper(int[][] grid, int x, int y,
		int targetX, int targetY, boolean[][] visited, String path) {
		if (!isValid(x, y, grid.length, grid[0].length) || visited[x][y]) {
			return;
		}

		visited[x][y] = true;

		if (x == targetX && y == targetY) {
			System.out.println(path + " 도착");
		} else {
			for (int i = 0; i < DIRECTIONS.length; i++) {
				int nx = x + DIRECTIONS[i][0];
				int ny = y + DIRECTIONS[i][1];
				findAllPaths2DHelper(grid, nx, ny, targetX, targetY, visited,
					path + " -> (" + nx + "," + ny + ")");
			}
		}

		visited[x][y] = false;
	}

	/// N칸 선택 (연결된 경로, 가지치기 포함)
	/// - 정확히 N칸을 선택하되, 인접한 칸으로만 이동
	/// - 사용 예: 테트로미노, 연결된 블록 선택
	/// - 결과를 콘솔에 출력
	/// 
	/// @param grid 2D 그리드
	/// @param n 선택할 칸 수
	public static void selectNCellsConnected(int[][] grid, int n) {
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				selectNCellsHelper(grid, i, j, n, visited, "(" + i + "," + j + ")");
			}
		}
	}

	private static void selectNCellsHelper(int[][] grid, int x, int y, int n,
		boolean[][] visited, String path) {
		if (!isValid(x, y, grid.length, grid[0].length) || visited[x][y]) {
			return;
		}

		// 가지치기: 이미 N칸 선택했으면 더 탐색 불필요
		int currentCount = path.split("->").length;
		if (currentCount >= n) {
			return;
		}

		visited[x][y] = true;

		// 종료 조건: 정확히 N칸 선택
		if (currentCount == n - 1) {
			System.out.println(path);
			visited[x][y] = false;
			return;
		}

		// 4방향 탐색
		for (int i = 0; i < DIRECTIONS.length; i++) {
			int nx = x + DIRECTIONS[i][0];
			int ny = y + DIRECTIONS[i][1];
			selectNCellsHelper(grid, nx, ny, n, visited, path + " -> (" + nx + "," + ny + ")");
		}

		visited[x][y] = false;
	}

	private static boolean isValid(int x, int y, int rows, int cols) {
		return x >= 0 && x < rows && y >= 0 && y < cols;
	}
}
