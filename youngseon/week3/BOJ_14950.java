
import java.io.*;
import java.util.*;

public class BOJ_14950 {

	static int[] parent;

	static class Edge implements Comparable<Edge> { // edge 클래스 정의
		int node1;
		int node2;
		int val;

		Edge(int node1, int node2, int val) {
			this.node1 = node1;
			this.node2 = node2;
			this.val = val;
		}

		@Override
		public int compareTo(Edge o) { // 간선의 크기가 작은 순으로 정렬
			// TODO Auto-generated method stub
			return this.val - o.val;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		PriorityQueue<Edge> queue = new PriorityQueue<>();

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());

		parent = new int[N + 1];

		for (int i = 1; i < N + 1; i++) { // 대표 노드 초기화
			parent[i] = i;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int edge = Integer.parseInt(st.nextToken());
			queue.add(new Edge(n1, n2, edge));
		}

		int useEdge = 0;
		int result = 0;

		while (useEdge < N - 1) { // 간선의 개수가 N-1개이면 종료
			Edge now = queue.poll();
			if (find(now.node1) != find(now.node2)) { // 사이클 생기는지 판단하기 위해 find 연산
				union(find(now.node1), find(now.node2));
				result += now.val + t * useEdge; // t 고려하여 계산
				useEdge++;
			}
		}
		System.out.println(result);
	}

	public static int find(int n) { // find 연산
		if (n == parent[n]) {
			return n;
		}
		return parent[n] = find(parent[n]);
	}

	public static void union(int u, int v) { // union 연산
		u = find(u);
		v = find(v);
		if (u != v) {
			parent[v] = u;
		}
	}
}
