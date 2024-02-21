import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3282 {

	static int[] V;
	static int[] C;
	static int[][] dp;
	static int maxValue, N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < T; i++) {
			maxValue = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			V = new int[N + 1];
			C = new int[N + 1];
			
			for (int j = 1; j <= N; j++) {
				st = new StringTokenizer(br.readLine());
				V[j] = Integer.parseInt(st.nextToken());
				C[j] = Integer.parseInt(st.nextToken());
			}

			calculate();
			sb.append("#").append(i + 1).append(" ").append(dp[N][K]).append("\n");
		}

		System.out.println(sb);
	}

	private static void calculate() {
		// TODO Auto-generated method stub
		dp = new int[N + 1][K + 1];

		for (int i = 1; i < N + 1; i++) { // i-> 세로축 (V), k -> 가로축 (C)
			for (int k = 1; k < K + 1; k++) {
				if (k < V[i]) { // 최대 무게보다 초과인 경우
					dp[i][k] = dp[i - 1][k];
				} else {
					dp[i][k] = Math.max(C[i] + dp[i - 1][k - V[i]], dp[i - 1][k]);

				}
			}

		}
	}
}
