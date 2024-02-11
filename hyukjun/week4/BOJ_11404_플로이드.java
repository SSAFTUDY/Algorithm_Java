package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11404 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		// get input
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[][] arr = new int[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			if (arr[start][dest] != 0) {
				arr[start][dest] = Math.min(arr[start][dest], cost);
			} else {
				arr[start][dest] = cost;
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i != j && arr[i][j] == 0)
					arr[i][j] = Integer.MAX_VALUE;
			}
		}

		// process
		// Floyd-Warshall
		for (int curNode = 1; curNode <= N; curNode++) {
			for (int i = 1; i <= N; i++) {
				if (curNode == i || arr[i][curNode] == Integer.MAX_VALUE)
					continue;
				for (int j = 1; j <= N; j++) {
					if (arr[curNode][j] == Integer.MAX_VALUE)
						continue;
					arr[i][j] = Math.min(arr[i][j], arr[i][curNode] + arr[curNode][j]);
				}
			}
		}

		// output
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i != j && arr[i][j] == Integer.MAX_VALUE)
					sb.append(0).append(' ');
				else
					sb.append(arr[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

}
