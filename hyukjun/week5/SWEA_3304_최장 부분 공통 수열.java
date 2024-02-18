package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3304 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int TC = 1; TC <= T; TC++) {
			sb.append('#').append(TC).append(' ');

			// get input
			st = new StringTokenizer(br.readLine());
			String s1 = st.nextToken();
			String s2 = st.nextToken();

			// process
			char[] c1 = new char[s1.length() + 1];
			for (int i = 1; i < c1.length; i++) {
				c1[i] = s1.charAt(i - 1);
			}

			char[] c2 = new char[s2.length() + 1];
			for (int i = 1; i < c2.length; i++) {
				c2[i] = s2.charAt(i - 1);
			}

			// s1 = acaykp
			// s2 = capcak
			int[][] dp = new int[c1.length][c2.length];
			for (int i = 1; i < c1.length; i++) {
				for (int j = 1; j < c2.length; j++) {
					if (c1[i] != c2[j]) {
						dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
					} else {
						dp[i][j] = dp[i - 1][j - 1] + 1;
					}
				}
			}

			// output
			sb.append(dp[c1.length - 1][c2.length - 1]).append('\n');
		}
		System.out.println(sb);
	}

}
