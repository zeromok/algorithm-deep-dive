package daniel.algorithmdeepdive.dataStructure.graph;

import java.util.ArrayList;
import java.util.List;

/// # 인접 리스트 그래프 구현
/// ## 본질
/// 각 정점마다 "실제 연결된 정점만" 리스트로 저장
/// ## 핵심 원리
/// - 각 정점이 연결된 정점들의 리스트만 저장
/// - O(V+E) 공간 복잡도
/// - 인접 정정 순회가 효율적
/// ## 적합한 경우
/// 희소 그래프(Sparse Graph), 대부분의 실전 문제
public class AdjacencyListGraph {
	private List<List<Integer>> list;
	private int vertices;

	public AdjacencyListGraph(int vertices) {
		this.list = new ArrayList<>();
		this.vertices = vertices;

		for (int i = 0; i < vertices; i++) {
			list.add(new ArrayList<>());
		}
	}

	/// ### 무방향 그래프 간선 추가
	/// 간선에 방향이 없어서 양방향으로 이어준다.
	/// @param from 정점1
	/// @param to 정점2
	public void addEdge(int from, int to) {
		list.get(from).add(to);
		list.get(to).add(from);
	}

	/// ### 방향 그래프 간선 추가
	/// 간선에 방향이 있어서 한 정점에서 다른 정점으로만 이동
	/// @param from 출발 정점
	/// @param to 도착 정점
	public void addDirectedEdge(int from, int to) {
		list.get(from).add(to);
	}

	// 간선 존재 확인: O(degree) - 인접행렬보다 느림
	public boolean hasEdge(int from, int to) {
		return list.get(from).contains(to);
	}

	// 특정 정점의 인접 정점들 반환
	public List<Integer> getNeighbors(int vertex) {
		return list.get(vertex);
	}

	// 인접 정점 순회: O(degree) - 핵심 장점
	public void printNeighbors(int vertex) {
		System.out.print(vertex + "의 인접 정점: ");
		for (int e : list.get(vertex)) {
			System.out.print(e + " ");
		}
		System.out.println();
	}

	public void printGraph() {
		System.out.println("인접 리스트: ");
		for (int i = 0; i < vertices; i++) {
			System.out.print(i + ": ");
			for (Integer e : list.get(i)) {
				System.out.print(e + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
