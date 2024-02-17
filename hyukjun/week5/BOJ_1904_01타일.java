package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1904 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// get input
		int N = Integer.parseInt(br.readLine());

		// process
		int[] dp = new int[1_000_001];
		dp[1] = 1;
		dp[2] = 2;
		for (int i = 3; i <= N; i++) {
			dp[i] = (dp[i - 1] % 15746 + dp[i - 2] % 15746) % 15746;
		}

		// output
		System.out.println(dp[N]);
	}

}
