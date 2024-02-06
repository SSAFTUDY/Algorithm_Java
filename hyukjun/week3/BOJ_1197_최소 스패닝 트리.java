package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_1197 {

	private static class Edge {
		int A;
		int B;
		int cost;

		public Edge(int A, int B, int cost) {
			this.A = A;
			this.B = B;
			this.cost = cost;
		}
	}

	private static int[] parent;
	private static int[] size;

	private static int find(int x) {
		if (parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}

	private static void union(int x, int y) {
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

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// get input
		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken()), E = Integer.parseInt(st.nextToken());
		parent = new int[V + 1];
		size = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			parent[i] = i;
		}

		Edge[] edges = new Edge[E];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			edges[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
		}

		// process
		Arrays.sort(edges, Comparator.comparingInt(o -> o.cost));
		int cost = 0;
		for (int i = 0; i < edges.length; i++) {
			Edge edge = edges[i];
			int r1 = find(edge.A);
			int r2 = find(edge.B);
			if (r1 != r2) {
				union(r1, r2);
				cost += edge.cost;
			}
		}
		
		System.out.println(cost);
	}

}
