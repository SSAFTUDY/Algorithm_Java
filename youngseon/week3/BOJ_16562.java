
import java.io.*;
import java.util.*;

public class BOJ_16562 {

	static int[] friendCost; // 친구비
	static int[] parent; // 대표 노드 배열
	static boolean[] visited; // 친구 맺었는지

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int cost = 0;

		friendCost = new int[N + 1];
		parent = new int[N + 1];
		visited = new boolean[N + 1];

		st = new StringTokenizer(br.readLine()); // 친구비, 대표 노드 초기화
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
			friendCost[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < M; i++) { // 친구 연결하는 로직
			st = new StringTokenizer(br.readLine());
			int f1 = Integer.parseInt(st.nextToken());
			int f2 = Integer.parseInt(st.nextToken());

			union(f1, f2);
		}

		for (int i = 1; i <= N; i++) { // 대표 배열 정리
			parent[i] = find(i);
		}

		for (int i = 1; i <= N; i++) {

			if (visited[i]) { // 이미 친구 상태이면 패스
				continue;
			}
			int minCost = friendCost[i];
			visited[i] = true;
			for (int j = i + 1; j <= N; j++) {
				if (parent[i] == parent[j]) {
					visited[j] = true;
					if (minCost > friendCost[j]) { // 친구비가 더 싸다면 갱신
						minCost = friendCost[j];
					}
				}
			}			
			cost += minCost;
		}
		if(cost<=k) {
			System.out.println(cost);
		}
		else {
			System.out.println("Oh no");
		}

	}

	private static void union(int f1, int f2) {
		f1 = find(f1);
		f2 = find(f2);
		if (f1 != f2) {
			parent[f2] = f1;
		}
	}

	private static int find(int n) {
		if (n == parent[n]) {
			return n;
		}
		return parent[n] = find(parent[n]);
	}
}
