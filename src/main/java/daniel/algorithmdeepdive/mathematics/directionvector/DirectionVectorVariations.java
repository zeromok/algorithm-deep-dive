package daniel.algorithmdeepdive.mathematics.directionvector;

import static daniel.algorithmdeepdive.mathematics.directionvector.DirectionVectorBasic.*;

/// 방향 벡터의 다양한 변형
/// - 나이트 이동
/// - 8방향 (대각선)
/// - 직진 탐색
/// - 커스텀 벡터
public class DirectionVectorVariations {

	public static void knightMove() {
		System.out.println("==== 나이트 이동 ====");
		int[][] grid = new int[8][8];
		int startX = 4, startY = 4;

		grid[startX][startY] = 9;

		for (int i = 0; i < 8; i++) {
			int nx = startX + KNIGHT_DX[i];
			int ny = startY + KNIGHT_DY[i];

			if (isValid(nx, ny, 8, 8)) {
				grid[nx][ny] = 1;
			}
		}
		printGrid(grid);
	}

	public static void eightDirections() {
		System.out.println("\n==== 8방향 (대각선 포함) ====");
		int[][] grid = new int[7][7];
		int startX = 3, startY = 3;

		grid[startX][startY] = 9;

		for (int i = 0; i < 8; i++) {
			int nx = startX + DX8[i];
			int ny = startY + DY8[i];

			if (isValid(nx, ny, 7, 7)) {
				grid[nx][ny] = 1;
			}
		}
		printGrid(grid);
	}

	public static void lineMove() {
		System.out.println("\n==== 직진 탐색 ====");
		int[][] grid = new int[9][9];
		int startX = 4, startY = 4;

		grid[startX][startY] = 9;

		for (int dir = 0; dir < 4; dir++) {
			int nx = startX;
			int ny = startY;
			int step = 1;

			while (true) {
				nx += DX4[dir];
				ny += DY4[dir];

				if (!isValid(nx, ny, 9, 9)) break;

				grid[nx][ny] = step++;
			}
		}
		printGrid(grid);
	}

	public static void customDirections() {
		System.out.println("\n==== 커스텀 벡터 (2칸 점프) ====");
		int[][] grid = new int[9][9];
		int startX = 4, startY = 4;

		int[] customDX = {-2, 2, 0, 0};
		int[] customDY = {0, 0, -2, 2};

		grid[startX][startY] = 9;

		for (int i = 0; i < 4; i++) {
			int nx = startX + customDX[i];
			int ny = startY + customDY[i];

			if (isValid(nx, ny, 9, 9)) {
				grid[nx][ny] = 1;
			}
		}
		printGrid(grid);
	}

	public static void main(String[] args) {
		knightMove();
		eightDirections();
		lineMove();
		customDirections();
	}
}
