package daniel.algorithmdeepdive.mathematics.directionvector;

import static daniel.algorithmdeepdive.mathematics.directionvector.DirectionVectorBasic.*;

import java.util.LinkedList;
import java.util.Queue;

/// BFS와 방향 벡터
/// - 레벨별 탐색
/// - 최단 거리 보장
/// - Queue 사용
public class DirectionVectorBFS {

	public static void bfsExploration() {
		System.out.println("==== BFS 레벨별 탐색 ====");
		int[][] grid = new int[7][7];
		boolean[][] visited = new boolean[7][7];
		Queue<DirectionVectorBasic.Point> queue = new LinkedList<>();

		int startX = 3, startY = 3;

		queue.offer(new DirectionVectorBasic.Point(startX, startY, 0));
		visited[startX][startY] = true;
		grid[startX][startY] = 0;

		while (!queue.isEmpty()) {
			DirectionVectorBasic.Point curr = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = curr.x + DX4[i];
				int ny = curr.y + DY4[i];

				if (isValid(nx, ny, 7, 7) && !visited[nx][ny]) {
					visited[nx][ny] = true;
					grid[nx][ny] = curr.level + 1;
					queue.offer(new Point(nx, ny, curr.level + 1));
				}
			}
		}
		printGrid(grid);
	}

	public static void main(String[] args) {
		bfsExploration();
	}
}
