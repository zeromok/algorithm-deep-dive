package daniel.algorithmdeepdive.mathematics.directionvector;

import static daniel.algorithmdeepdive.mathematics.directionvector.DirectionVectorBasic.*;

import java.util.Stack;

/// DFS와 방향 벡터
/// - 깊이 우선 탐색
/// - Stack 사용 (LIFO)
/// - 방향 역순 탐색
public class DirectionVectorDFS {

	public static void dfsStackExploration() {
		System.out.println("==== DFS (Stack) ====");
		int[][] grid = new int[7][7];
		boolean[][] visited = new boolean[7][7];
		Stack<Point> stack = new Stack<>();

		int startX = 3, startY = 3;

		stack.push(new Point(startX, startY, 0));

		while (!stack.isEmpty()) {
			Point curr = stack.pop();

			if (visited[curr.x][curr.y]) continue;

			visited[curr.x][curr.y] = true;
			grid[curr.x][curr.y] = curr.level;

			for (int i = 3; i >= 0; i--) {
				int nx = curr.x + DX4[i];
				int ny = curr.y + DY4[i];

				if (isValid(nx, ny, 7, 7) && !visited[nx][ny]) {
					stack.push(new DirectionVectorBasic.Point(nx, ny, curr.level + 1));
				}
			}
		}
		printGrid(grid);
	}

	// 재귀 DFS도 추가 가능
	public static void dfsRecursive() {
		System.out.println("\n==== DFS (재귀) ====");
		int[][] grid = new int[7][7];
		boolean[][] visited = new boolean[7][7];

		dfs(grid, visited, 3, 3, 0, 7, 7);
		printGrid(grid);
	}

	private static void dfs(int[][] grid, boolean[][] visited,
		int x, int y, int level, int rows, int cols) {
		visited[x][y] = true;
		grid[x][y] = level;

		for (int i = 0; i < 4; i++) {
			int nx = x + DX4[i];
			int ny = y + DY4[i];

			if (isValid(nx, ny, rows, cols) && !visited[nx][ny]) {
				dfs(grid, visited, nx, ny, level + 1, rows, cols);
			}
		}
	}

	public static void main(String[] args) {
		dfsStackExploration();
		dfsRecursive();
	}
}
