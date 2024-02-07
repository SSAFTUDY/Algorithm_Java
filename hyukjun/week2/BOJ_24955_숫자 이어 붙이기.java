package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_24955 {

	static String[] houses;
	static List<Integer>[] neighbor;
	static int[] visitedFrom;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		// get input
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), Q = Integer.parseInt(st.nextToken());

		// 집 번호
		houses = new String[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			houses[i] = st.nextToken();
		}

		// 간선 정보
		neighbor = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			neighbor[i] = new ArrayList<>();
		}
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int h1 = Integer.parseInt(st.nextToken());
			int h2 = Integer.parseInt(st.nextToken());
			neighbor[h1].add(h2);
			neighbor[h2].add(h1);
		}

		// process
		// 놀이 정보
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			visitedFrom = new int[N + 1];
			BigInteger output = bfs(from, to);
			sb.append(output.remainder(new BigInteger("1000000007"))).append('\n');
		}
		System.out.println(sb);
	}

	// 역방향으로 순회
	private static BigInteger bfs(int to, int from) {
		Deque<Integer> deque = new ArrayDeque<>();
		visitedFrom[from] = -1;
		deque.addLast(from);

		while (!deque.isEmpty()) {
			int node = deque.pollFirst();
			for (int i = 0; i < neighbor[node].size(); i++) {
				int adj = neighbor[node].get(i);
				if(visitedFrom[adj] == 0) {
					visitedFrom[adj] = node;
					deque.addLast(adj);
					if(adj == to) {
						break;
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while(to != -1) {
			sb.append(houses[to]);
			to = visitedFrom[to];
		}
		
		return new BigInteger(sb.toString());
	}

}
