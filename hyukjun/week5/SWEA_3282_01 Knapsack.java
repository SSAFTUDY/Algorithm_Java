package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3282 {

	final static int WEIGHT = 0;
	final static int VALUE = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int TC = 1; TC <= T; TC++) {
			sb.append('#').append(TC).append(' ');

			// get input
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
			int[][] stuff = new int[N + 1][2];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				stuff[i][WEIGHT] = Integer.parseInt(st.nextToken());
				stuff[i][VALUE] = Integer.parseInt(st.nextToken());
			}

			// process
			int[][] dp = new int[N + 1][K + 1];
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= K; j++) {
					if (stuff[i][WEIGHT] > j) {
						dp[i][j] = dp[i - 1][j];
					} else {
						dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - stuff[i][WEIGHT]] + stuff[i][VALUE]);
					}
				}
			}
			
			// output
			sb.append(dp[N][K]).append('\n');
		}
		System.out.println(sb);
	}

}
