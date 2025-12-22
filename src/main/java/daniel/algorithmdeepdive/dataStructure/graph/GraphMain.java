package daniel.algorithmdeepdive.dataStructure.graph;

public class GraphMain {
	private static final int VERTEX_NUM = 7; // 정점 개수

	public static void main(String[] args) {

		/// ## 인접 행렬
		AdjacencyMatrixGraph matrixGraph = new AdjacencyMatrixGraph(VERTEX_NUM + 1);
		matrixGraph.addEdge(1, 2);
		matrixGraph.addEdge(2, 3);
		matrixGraph.addEdge(1, 5);
		matrixGraph.addEdge(5, 2);
		matrixGraph.addEdge(5, 6);
		matrixGraph.addEdge(4, 7);

		matrixGraph.printGraph();
		for (int i = 1; i < 8; i++) {
			matrixGraph.printNeighbors(i);
		}

		/// ## 인접 리스트
		AdjacencyListGraph listGraph = new AdjacencyListGraph(VERTEX_NUM + 1);
		listGraph.addEdge(1, 2);
		listGraph.addEdge(2, 3);
		listGraph.addEdge(1, 5);
		listGraph.addEdge(5, 2);
		listGraph.addEdge(5, 6);
		listGraph.addEdge(4, 7);

		listGraph.printGraph();
		for (int i = 1; i < 8; i++) {
			listGraph.printNeighbors(i);
		}
	}
}
