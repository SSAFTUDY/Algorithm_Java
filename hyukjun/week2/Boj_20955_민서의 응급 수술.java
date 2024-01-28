package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Boj_20955 {

	// 트리란, 사이클이 존재하지 않는 연결 그래프

	static boolean[] check;
	static List<Integer>[] neighbor;
	static Set<Integer> cycle;
	static int count = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// get input
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		check = new boolean[N + 1];
		check[0] = true;
		neighbor = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			neighbor[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			neighbor[u].add(v);
			neighbor[v].add(u);
		}

		// process
		for (int i = 1; i <= N; i++) {
			if (check[i]) {
				continue;
			}
			check[i] = true;
			cycle = new HashSet<>();
			dfs(i, -1);
			// 집합 안에서 cycle을 끊는 연산의 횟수를 더해줌
			count += cycle.size() / 2;
			// 집합의 갯수를 더해줌
			count++;
		}

		// output
		// 집합을 연결하는 횟수 = 집합들의 갯수 - 1
		System.out.println(count - 1);
	}

	private static void dfs(int startIdx, int parent) {
		for (int i = 0; i < neighbor[startIdx].size(); i++) {
			int adj = neighbor[startIdx].get(i);
			if(adj == parent) {
				continue;
			}
			
			if(!check[adj]) {
				check[adj] = true;
				dfs(adj, startIdx);
			} else {
				// dfs 순회 시 parent가 아닌데 이미 check된 노드가 나온다면 cycle
				cycle.add(startIdx);
				cycle.add(adj);
			}
		}
	}

}
