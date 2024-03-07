import java.io.*;
import java.util.*;


class Solution {
	static int N, M, K;
	static class Beer {
		int favor;
		int level;
		Beer(int favor, int level) {
			this.favor = favor;
			this.level = level;
		}
	}
	static PriorityQueue<Beer> level_pq = new PriorityQueue<Beer>(new Comparator<Beer>() {
		@Override
		public int compare(Beer o1, Beer o2) {
			if (o1.level == o2.level) {
				return o2.favor - o1.favor;
			}
			else { 
				return o1.level - o2.level;
			}
		}
	});
	static PriorityQueue<Beer> favor_pq = new PriorityQueue<Beer>(new Comparator<Beer>() {
		@Override
		public int compare(Beer o1, Beer o2) {
			if (o1.favor == o2.favor) {
				return 0;
			}
			else { 
				return o1.favor - o2.favor;
			}
		}
	});
	
	
	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
//		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			level_pq.add(new Beer(v, c));
		}
		int ttl = 0;
		int ans = 0;
		for (int i = 0; i < N; i++) {
			Beer beer = level_pq.poll();
			ttl += beer.favor;
			if (i == N-1) {
				ans = beer.level;
			}
			favor_pq.add(beer);
			
		}
		while(!level_pq.isEmpty() && ttl < M) {
			Beer beer = level_pq.poll();
			ttl += beer.favor;
			ans = beer.level;
			favor_pq.add(beer);
			beer = favor_pq.poll();
			ttl -= beer.favor;
		}
		if (ttl < M) {
			System.out.println(-1);
		}
		else {
			System.out.println(ans);
		}
	}
}