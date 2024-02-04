
import java.io.*;
import java.util.*;

public class BOJ_1197 {

	static int[] parent; // union find에 사용되는 대표 배열
	static PriorityQueue<Edge> queue = new PriorityQueue<>();

	static class Edge implements Comparable<Edge> { // Edge 클래스 정의

		int node1;
		int node2;
		int edge;

		public Edge(int node1, int node2, int edge) {
			super();
			this.node1 = node1;
			this.node2 = node2;
			this.edge = edge;
		}

		@Override
		public int compareTo(Edge o) { // 가중치가 작은 순으로 정렬
			// TODO Auto-generated method stub
			return this.edge - o.edge;
		}
	}

	public static void union(int a, int b) {  // union 연산
		a = find(a);
		b = find(b);
		if (a != b) {
			parent[b] = a;
		}
	}

	public static int find(int n) {  // find 연산
		if (n == parent[n]) {
			return n;
		}
		return parent[n] = find(parent[n]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		parent = new int[v + 1];

		for (int i = 1; i <= v; i++) {  // 대표노드 초기화
			parent[i] = i;
		}

		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			queue.add(new Edge(n1, n2, val));  // 큐에 에지 정보 넣기
		}

		int useEdge = 0;  // 사용한 노드의 개수
		int result = 0; // 결과인 가중치의 합

		while (useEdge < v - 1) {
			Edge now = queue.poll();
			if (find(now.node1) != find(now.node2)) {
				union(now.node1, now.node2);
				result = result + now.edge;
				useEdge++;
			}
		}
		System.out.println(result);
	}
}
