import java.io.*;
import java.util.*;

/** Prim 30640KB, 528ms */
class Solution {
	
	private static int prim(List<int[]>[] adj) {
		//pq 원소: {다음 도시, 도로 비용}
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
		BitSet visited = new BitSet();
		int res = 0;
		
		pq.addAll(adj[1]);
		visited.set(1);
		for (int i = 0; i < adj.length - 2; i++) {
			int[] road;
			
			//최소 도로 선택 (이미 정복한 도시는 거름)
			while (visited.get( (road = pq.remove())[0] ));
			visited.set(road[0]);
			res += road[1];
			
			//정복한 도시의 주변 도로 add
			for (int[] next : adj[road[0]]) {
				pq.add(next);
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
		List<int[]>[] adj = new List[N + 1];
		
        //init adj
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		
        //parsing
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()),
				B = Integer.parseInt(st.nextToken()),
				C = Integer.parseInt(st.nextToken());
			adj[A].add(new int[] {B, C});
			adj[B].add(new int[] {A, C});
		}
		
		System.out.println(prim(adj) + t * (N - 1) * (N - 2) / 2);
	}
	
}
