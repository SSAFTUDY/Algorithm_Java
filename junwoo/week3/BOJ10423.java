import java.io.*;
import java.util.*;


public class Main {
	static int n, m, k, mini;
	static List<int[]>[] neighbor;
	static PriorityQueue<int[]> pq = new PriorityQueue<int[]>((o1, o2) -> { if(o1[1]==o2[1]) {return o1[0] - o2[0];} else{return o1[1] - o2[1];}});
	static boolean[] visited;
	
	public static void main(String [] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		neighbor = new ArrayList[n + 1]; int[] gene = new int[k]; visited = new boolean[n+1];
		for (int i = 1; i < n+1; i++) {
			neighbor[i] = new ArrayList<int[]>();
		}
		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < k; i++) {
			gene[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(bf.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			neighbor[u].add(new int[] {v, w});
			neighbor[v].add(new int[] {u, w});
		}
		
//		for (int i = 1; i < n+1; i++) {
//			for (int j = 0; j < neighbor[i].size(); j++) {
//				System.out.print(neighbor[i].get(j)[0] + " " + neighbor[i].get(j)[1] + " / ");
//			}
//			System.out.println();
//		}
		
		
		for (int i = 0; i < k; i++) {
			visited[gene[i]] = true;
			for (int j = 0; j < neighbor[gene[i]].size(); j++) {
				pq.add(neighbor[gene[i]].get(j));
			}
		}
//		int si = pq.size();
//		for (int i = 0; i < si; i++) {
//			int[] ele = pq.poll();
//			System.out.print(ele[0] + " " + ele[1] + " / ");
//		}System.out.println();
		
		
		int cnt = k-1;
		int cost = 0;
		while (cnt < n-1) {
			int[] ele = pq.poll();
//			System.out.println(ele[1] + " " + ele[0]);
			if (visited[ele[0]]) {continue;}
			visited[ele[0]] = true;
//			System.out.println(ele[1] + " " + ele[0]);
			cost += ele[1];
			for(int[] edge : neighbor[ele[0]]) {
				if (!visited[edge[0]]) {pq.add(edge);}
			}
			cnt++;
		}
		
		System.out.println(cost);
		
	}
}