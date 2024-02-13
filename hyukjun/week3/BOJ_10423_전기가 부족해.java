package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_10423 {

	static class Edge {
		int startNode;
		int endNode;
		int cost;

		public Edge(int startNode, int endNode, int cost) {
			this.startNode = startNode;
			this.endNode = endNode;
			this.cost = cost;
		}

	}

	static int find(int x) {
		if (parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}

	static boolean isUnion(int x, int y) {
		return find(x) == find(y);
	}

	static void union(int x, int y) {
		int xp = find(x);
		int yp = find(y);

		if (size[xp] >= size[yp]) {
			parent[yp] = xp;
			size[xp] += size[yp];
		} else {
			parent[xp] = yp;
			size[yp] += size[xp];
		}
	}

	static int[] parent;
	static int[] size;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// get input
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()),
				K = Integer.parseInt(st.nextToken());

		parent = new int[N + 1];
		size = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
			size[i] = 1;
		}

		st = new StringTokenizer(br.readLine());
		int gen = Integer.parseInt(st.nextToken());
		for (int i = 1; i < K; i++) {
			int nextGen = Integer.parseInt(st.nextToken());
			union(gen, nextGen);
			gen = nextGen;
		}

		List<Edge> edgeList = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			edgeList.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())));
		}
		Collections.sort(edgeList, Comparator.comparingInt(o -> o.cost));

		// process
		int edgeCount = 0;
		int costSum = 0;
		for (Edge edge : edgeList) {
			if (edgeCount == N - 1) {
				break;
			}
			if (isUnion(edge.startNode, edge.endNode))
				continue;
			union(edge.startNode, edge.endNode);
			costSum += edge.cost;
			edgeCount++;
		}
		
		// output
		System.out.println(costSum);
	}

}
