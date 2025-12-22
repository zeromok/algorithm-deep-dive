package daniel.algorithmdeepdive.pattern.search.graph;

import java.util.Arrays;

public class GraphSearchMain {
	public static void main(String[] args) {

		/// DFS
		// DFS dfs = new DFS(8);
		// dfs.addEdge(1, 2);
		// dfs.addEdge(2, 3);
		// dfs.addEdge(1, 5);
		// dfs.addEdge(5, 2);
		// dfs.addEdge(5, 6);
		// dfs.addEdge(4, 7);

		// dfs.dfsRecursive(1);
		// dfs.dfsIterative(1);
		// dfs.dfsAll();
		// System.out.println(dfs.findPath(1, 5));

		/// BFS
		BFS bfs = new BFS(8);
		bfs.addEdge(1, 2);
		bfs.addEdge(2, 3);
		bfs.addEdge(1, 5);
		bfs.addEdge(5, 2);
		bfs.addEdge(5, 6);
		bfs.addEdge(4, 7);

		// bfs.bfs(1);
		// bfs.bfsWithLevel(1);
		// System.out.println(Arrays.toString(bfs.shortestDistances(1)));
		// System.out.println(bfs.shortestPath(1, 6));
		bfs.bfsAll();
	}
}
