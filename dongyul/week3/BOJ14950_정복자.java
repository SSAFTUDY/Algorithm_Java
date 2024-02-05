import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * prim 알고리즘이용! 
 * 각 라운드에서 현재 갈 수 있는 간선 중 비용이 가장 작은 것을 PQ로 꺼내가며 진행
 * 이 간선의 도착 지점이 방문 안한 정점이면 그룹에 추가(방문 배열 체크)
 */
public class Main {

	//각 정점을 표현
	static class Node{
		int index;
		List<Edge> edge; //해당 정점과 연결된 간선

		public Node(int index) {
			this.index = index;
			edge = new ArrayList<Edge>();
		}
	}
	
	static class Edge{
		int end;	//간선의 도착지점
		int cost;	//간선의 비용
		
		public Edge(int end, int cost) {
			this.end = end;
			this.cost = cost;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());

		//정점 초기화
		Node[] nodes = new Node[N + 1];
		for (int i = 1; i <= N; i++) {
			nodes[i] = new Node(i);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			//양방향 매핑
			nodes[s].edge.add(new Edge(e,c));
			nodes[e].edge.add(new Edge(s,c));
		}
		
		//Union find로 싸이클 찾기 대신 방문으로 체크
		boolean[] visited = new boolean[N+1];
		visited[1] = true;
		
		PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
			@Override
			public int compare(Edge e1, Edge e2) {
				return e1.cost - e2.cost;
			}
		});
		
		//1번 정점 간선 넣고 시작
		for (Edge edge : nodes[1].edge) {
			pq.add(edge);
		}
		
		int cnt = 1;
		int answer = 0;
		while(cnt<N) {
			Edge curr = pq.poll();
			if(!visited[curr.end]) {
				for(Edge edge: nodes[curr.end].edge) {
					pq.add(edge);
				}
				answer = answer + (cnt-1)*t + curr.cost;
				visited[curr.end] = true;
				cnt+=1;
			}
		}
		System.out.println(answer);
	}
}
