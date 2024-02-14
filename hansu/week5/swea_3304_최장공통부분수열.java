/**
 * 메모리: 29,240kb
 * 시간: 166ms
 */

import java.io.*;
import java.util.*;

class Solution {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String s1 = st.nextToken(), s2 = st.nextToken();
			int N = s1.length(), M = s2.length();
			int[][] dp = new int[N + 1][M + 1];

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					
					if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
						dp[i][j] = dp[i - 1][j - 1] + 1;
					}
					else {
						dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
					}
					
				}
			}
			sb.append('#').append(tc).append(' ').append(dp[N][M]).append('\n');
		}
		System.out.println(sb);
	}
	
}