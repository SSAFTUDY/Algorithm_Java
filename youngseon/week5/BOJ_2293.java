import java.util.*;
import java.io.*;

public class BOJ_2293 {
	
	//메모리 :11688kb, 시간 :88ms	

	static int coins[];
	static int dp[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		coins = new int[n];

		for (int i = 0; i < n; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		dp = new int[k + 1];
		dp[0] = 1;
		for (int i = 0; i < n; i++) {
			int coin = coins[i];
			for (int j = coin; j < k + 1; j++) {
				dp[j] = dp[j] + dp[j - coin];  // dp의 인덱스가 해당 동전의 값보다 크거나 같을때, 연산
			}
		}
		System.out.println(dp[k]);  // k원
	}
}
