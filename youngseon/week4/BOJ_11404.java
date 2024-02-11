
import java.io.*;
import java.util.*;

public class BOJ_11404 {

	static int INF = 10_000_001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		int graph[][] = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j) {
					graph[i][j] = 0;
				} else {
					graph[i][j] = INF; // 최대값으로 먼저 초기화
				}
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (graph[a][b] > c) {
				graph[a][b] = c;
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				for (int k = 1; k <= N; k++) {
					graph[j][k] = Math.min(graph[j][k], graph[j][i] + graph[i][k]); // 플로이드 워셜
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (graph[i][j] == INF) {
					graph[i][j] = 0;
				}
				System.out.print(graph[i][j] + " ");
			}
			System.out.println();
		}
	}
}
