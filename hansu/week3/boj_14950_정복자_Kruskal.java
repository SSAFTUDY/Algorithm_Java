import java.io.*;
import java.util.*;


/** Kruskal 30284KB, 456ms */
class Solution {
	
	private static int[] parent;
	
	private static boolean union(int[] edge) {
		int x = find(edge[0]), y = find(edge[1]);
		
		if (x == y) {
			return false;
		}
		parent[Math.max(x, y)] = Math.min(x, y);
		return true;
	}
	
	private static int find(int x) {
		if (parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
	
	private static int kruskal(int[][] edges) {
		int res = 0, cnt = 0;
		
		Arrays.sort(edges, Comparator.comparingInt(o -> o[2]));
		for (int[] edge : edges) {
			if (union(edge)) {
				res += edge[2];
				if (++cnt == edges.length - 2) {
					return res;
				}
			}
		}
		return res;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()),
			M = Integer.parseInt(st.nextToken()),
			t = Integer.parseInt(st.nextToken());
		int[][] edges = new int[M][];
		
		//init parent
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] =i;
		}
		
		//parsing
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()),
				B = Integer.parseInt(st.nextToken()),
				C = Integer.parseInt(st.nextToken());
			edges[i] = new int[] {A, B, C};
		}
		
		System.out.println(kruskal(edges) + t * (N - 1) * (N - 2) / 2);
	}
	
}
