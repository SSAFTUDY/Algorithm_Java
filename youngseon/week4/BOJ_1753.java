import java.io.*;
import java.util.*;

public class BOJ_1753 {
	static List<Node>[] list;
	static int[] dp;
	static int N, M, K;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());

		int[][] arr = new int[M][3];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			arr[i] = new int[] { a, b, c };
		}
		solution(arr);
	}

	// Dijkstra 배열 초기화
	static void solution(int maps[][]) {
		visited = new boolean[N + 1];
		dp = new int[N + 1];
		list = new ArrayList[N + 1];

		for (int i = 1; i < N + 1; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			int a = maps[i][0];
			int b = maps[i][1];
			int c = maps[i][2];

			list[a].add(new Node(b, c));
		}

		dijkstra(K);
		for (int i = 1; i <= N; i++) {
			if (dp[i] >= Integer.MAX_VALUE) {
				System.out.println("INF");
			} else {
				System.out.println(dp[i]);
			}
		}
	}

	// 힙을 이용한 개선된 다익스트라 알고리즘
	static void dijkstra(int start) {
		Queue<Node> q = new PriorityQueue<>();
		Arrays.fill(dp, Integer.MAX_VALUE);

		q.add(new Node(start, 0));
		dp[start] = 0;

		while (!q.isEmpty()) {
			Node node = q.poll();
			int to = node.to;

			if (visited[to])
				continue;
			else
				visited[to] = true;

			for (Node nxt : list[to]) {
				if (dp[nxt.to] >= dp[to] + nxt.weight) {
					dp[nxt.to] = dp[to] + nxt.weight;
					q.add(new Node(nxt.to, dp[nxt.to]));
				}
			}
		}
	}
}

class Node implements Comparable<Node> {
	int to;
	int weight;

	Node(int to, int weight) {
		this.to = to;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node o) {
		return Integer.compare(this.weight, o.weight);
	}
}
