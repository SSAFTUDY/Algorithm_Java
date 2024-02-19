/**
 * 메모리: 11,832kb
 * 시간: 96ms
 */
import java.io.*;

class Solution {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[2];
		
		dp[0] = 1;
		for (int i = 1; i <= N; i++) {
			dp[i % 2] = (dp[0] + dp[1]) % 15746;
		}
		System.out.println(dp[N % 2]);
	}
	
}
