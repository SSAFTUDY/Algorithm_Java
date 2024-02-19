package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2293 {

	static int N;
	static int K;
	static int[] money;
	static int[] dp;
	static int output;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// get input
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		money = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			money[i] = Integer.parseInt(br.readLine());
		}

		// process
		dp = new int[K + 1];
		dp[0] = 1;
		for (int idx = 1; idx <= N; idx++) {
			for (int moneySum = money[idx]; moneySum <= K; moneySum++) {
				// 이전 dp[moneySum] 값에 현재 dp[moneySum - money[idx]]값 더하기
				dp[moneySum] += dp[moneySum - money[idx]];
			}
		}

		// output
		System.out.println(dp[K]);
	}
}
