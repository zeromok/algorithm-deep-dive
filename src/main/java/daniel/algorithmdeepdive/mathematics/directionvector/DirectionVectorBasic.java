package daniel.algorithmdeepdive.mathematics.directionvector;

/// 방향 벡터의 기본 개념과 상수 정의
/// 다른 클래스에서 상속 또는 import static 사용
public class DirectionVectorBasic {

	// 모든 클래스가 공유할 상수
	public static final int[] DX4 = {-1, 1, 0, 0};
	public static final int[] DY4 = {0, 0, -1, 1};

	public static final int[] DX8 = {-1, -1, -1, 0, 0, 1, 1, 1};
	public static final int[] DY8 = {-1, 0, 1, -1, 1, -1, 0, 1};

	public static final int[] KNIGHT_DX = {-2, -2, -1, -1, 1, 1, 2, 2};
	public static final int[] KNIGHT_DY = {-1, 1, -2, 2, -2, 2, -1, 1};

	// 공통 유틸
	protected static boolean isValid(int x, int y, int rows, int cols) {
		return x >= 0 && x < rows && y >= 0 && y < cols;
	}

	protected static void printGrid(int[][] grid) {
		for (int[] row : grid) {
			for (int cell : row) {
				System.out.print(cell == 0 ? ". " : cell + " ");
			}
			System.out.println();
		}
	}

	protected static class Point {
		int x, y, level;
		public Point(int x, int y, int level) {
			this.x = x;
			this.y = y;
			this.level = level;
		}
	}

	// 기본 이동 실험
	public static void basicMove() {
		System.out.println("==== 4방향 이동 ====");
		int[][] grid = new int[5][5];
		int startX = 2, startY = 2;

		grid[startX][startY] = 1;

		for (int i = 0; i < 4; i++) {
			int nx = startX + DX4[i];
			int ny = startY + DY4[i];
			if (isValid(nx, ny, 5, 5)) {
				grid[nx][ny] = 2;
			}
		}
		printGrid(grid);
	}

	public static void main(String[] args) {
		basicMove();
	}
}
