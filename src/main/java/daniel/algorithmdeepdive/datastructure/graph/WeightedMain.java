package daniel.algorithmdeepdive.datastructure.graph;

public class WeightedMain {
	public static void main(String[] args) {
		weightMatters();
		cityNetwork();
		directedVsUndirected();
		minimumCostPath();
	}

	/// 테스트 1: 왜 가중치가 필요할까?
	/// 목적: 일반 그래프와의 차이
	/// 결론:
	/// - 경로1(대전)이 짧음
	/// - 일반 그래프(BFS)는 경로2를 선택 (간선 개수만 봄)
	/// - 가중치가 없으면 최단거리를 못 찾음
	static void weightMatters() {
		System.out.println("==== test1 ===");
		// 시나리오: 서울 -> 부산 가는 방법
		// 경로 1: 서울 -> 대전 -> 부산 (거리: 100Km + 150Km = 250Km)
		// 경로 1: 서울 -> 광주 -> 부산 (거리: 80Km + 200Km = 280Km)
		WeightedGraph cityGraph = new WeightedGraph(4);
		// 0: 서울, 1: 대전, 2: 광주, 3: 부산

		cityGraph.addEdge(0, 1, 100);  // 서울-대전 100km
		cityGraph.addEdge(1, 3, 150);  // 대전-부산 150km
		cityGraph.addEdge(0, 2, 80);   // 서울-광주 80km
		cityGraph.addEdge(2, 3, 200);  // 광주-부산 200km

		cityGraph.printGraph(); // 도시 간 거리
	}

	/// 테스트 2: 실전 네트워크 예제
	/// 목적: 현실 문제를 그래프로 모델링
	static void cityNetwork() {
		System.out.println("==== test2 ===");
		String[] cities = {"서울", "대전", "대구", "부산", "광주", "전주"};
		WeightedGraph network = new WeightedGraph(cities.length);

		// 실제 도로망 구성
		network.addEdge(0, 1, 140);  // 서울-대전
		network.addEdge(1, 2, 120);  // 대전-대구
		network.addEdge(2, 3, 90);   // 대구-부산
		network.addEdge(0, 4, 270);  // 서울-광주
		network.addEdge(4, 5, 80);   // 광주-전주
		network.addEdge(5, 1, 90);   // 전주-대전
		network.addEdge(1, 3, 200);  // 대전-부산 (고속도로)

		// 도시 매핑
		for (int i = 0; i < cities.length; i++) {
			System.out.println("  " + i + ": " + cities[i]);
		}

		// 네트워크 구조
		for (int i = 0; i < cities.length; i++) {
			System.out.print(cities[i] + " → ");
			for (WeightedGraph.Edge edge : network.getNeighbors(i)) {
				System.out.print(cities[edge.to] + "(" + edge.weight + "km) ");
			}
			System.out.println();
		}

		// 특정 도시의 연결 정보
		System.out.println("1(대전)의 인접도시: " + network.getNeighbors(1).size() + "개");

		int totalDistance = 0;
		for (WeightedGraph.Edge edge : network.getNeighbors(1)) {
			System.out.println("  → " + cities[edge.to] + ": " + edge.weight + "km");
			totalDistance += edge.weight;
		}
		System.out.println("총 거리: " + totalDistance + "Km");
	}

	/// 테스트 3: 방향 그래프 vs 무방향 그래프
	/// 목적: 방향성에 따른 차이 이해
	static void directedVsUndirected() {
		System.out.println("==== test3 ===");
		// 우방향: 도로 (양방향 통행 가능)
		// 0 -> 1 / 1 -> 0 같은 간선 (양방향)
		WeightedGraph undirected = new WeightedGraph(4);
		undirected.addEdge(0, 1, 10);
		undirected.addEdge(1, 2, 20);
		undirected.printGraph();

		// 방향: 일반통행
		// 0 -> 1 / 2 -> 0 다른 간선
		WeightedGraph directed = new WeightedGraph(4);
		directed.addDirectedEdge(0, 1, 10);
		directed.addDirectedEdge(1, 2, 20);
		directed.addDirectedEdge(2, 0, 5); // 돌아오는 길은 지름길
		directed.printGraph();
	}

	/// 테스트 4: 최소 비용 경로 찾기 (간단)
	/// 목적: 가중치 그래프의 실전 활용 - 모든 경로 탐색
	static void minimumCostPath() {
		System.out.println("==== test4 ===");
		WeightedGraph graph = new WeightedGraph(5);
		graph.addEdge(0, 1, 10);
		graph.addEdge(1, 3, 20);
		graph.addEdge(0, 2, 5);
		graph.addEdge(1, 4, 2);
		graph.addEdge(2, 4, 3);
		graph.addEdge(3, 4, 1);
		graph.printGraph();

		// 모든 경로 찾기 로직
		// ...
	}
}
