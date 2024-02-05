package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_14950 {

	static int[] parent;
	static int[] size;

	public static int find(int x) {
		if (x == parent[x])
			return x;
		return parent[x] = find(parent[x]);
	}

	public static boolean isUnion(int x, int y) {
		return find(x) == find(y);
	}

	public static void union(int x, int y) {
		int r1 = find(x);
		int r2 = find(y);

		if (size[r1] >= size[r2]) {
			parent[r2] = r1;
			size[r1]++;
		} else {
			parent[r1] = r2;
			size[r2]++;
		}
	}

	static List<Edge>[] neighbor;

	public static class Edge implements Comparable<Edge> {
		int node;
		int cost;

		public Edge(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return cost - o.cost;
		}
	}

	// prim 알고리즘
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// get input
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()),
				t = Integer.parseInt(st.nextToken());
		parent = new int[N + 1];
		size = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		neighbor = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			neighbor[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()), B = Integer.parseInt(st.nextToken()),
					cost = Integer.parseInt(st.nextToken());
			neighbor[A].add(new Edge(B, cost));
			neighbor[B].add(new Edge(A, cost));
		}

		// process
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.addAll(neighbor[1]);
		int costSum = 0;
		int multiply = 0;
		while (multiply + 1 < N) {
			Edge next = pq.poll();
			if (isUnion(1, next.node))
				continue;
			union(1, next.node);
			pq.addAll(neighbor[next.node]);
			costSum += next.cost + t * multiply++;
		}

		System.out.println(costSum);
	}

}
