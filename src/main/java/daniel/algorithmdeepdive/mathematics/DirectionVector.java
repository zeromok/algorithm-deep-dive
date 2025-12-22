package daniel.algorithmdeepdive.mathematics;

/// # 방향 벡터
/// ## 핵심 개념
/// - 방향 벡터는 2D/3D 그리드에서 이동방향을 나타내는 (dx, dy) 쌍
/// - 상하좌우, 대각석, 나이트 이동 등 다양한 이동 패턴을 표현
/// - 배열 인덱스 계산의 본질: 현재 위치 + 방향 벡터 = 다음 위치
public class DirectionVector {

	/// ### 기본 방향 벡터
	// 1. 상하좌우 (4방향)
	static final int[] DX4 = {-1, 1, 0, 0};
	static final int[] DY4 = {0, 0, -1, 1};

	// 2. 상하좌우 + 대각선 (8방향)
	static final int[] DX8 = {-1, -1, -1, 0, 0, 1, 1, 1};
	static final int[] DY8 = {-1, 0, 1, -1, 1, -1, 0, 1};

	// 3. 나이트 이동 (체스 말)
	static final int[] KNIGHT_DX = {-2, -2, -1, -1, 1, 1, 2, 2};
	static final int[] KNIGHT_DY = {-1, 1, -2, 2, -2, -2, -1, -1};

	/// ### 기본 이동
	public static void basicMove() {
		System.out.println("==== 4방향 이동 ====");
		int[][] grid = new int[10][10];
		int startX = 2, startY = 2;

		grid[startX][startY] = 1; // 시작점

		for (int i = 0; i < 4; i++) {
			int nx = startX + DX4[i];
			int ny = startY + DY4[i];

			if (isValid(nx, ny, 5, 5)) {
				grid[nx][ny] = 2;
			}
		}
		printGrid(grid);
	}

	private static boolean isValid(int x, int y, int rows, int cols) {
		return x >= 0 && x < rows && y >= 0 && y < cols;
	}

	private static void printGrid(int[][] grid) {
		for (int[] row : grid) {
			for (int cell : row) {
				System.out.print(cell == 0 ? ". " : cell + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		basicMove();
	}
}
