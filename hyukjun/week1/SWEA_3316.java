import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

// index
//	D	C	B	A
//	3	2	1	0

public class Swea_3316 {

	public static final String tc = "2\r\n" + "BC\r\n" + "ADCBBACDCBCBACBDCABDCBA";

	public static char[] managers;
	public static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// br = new BufferedReader(new StringReader(tc));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");

			// get input
			managers = br.readLine().toCharArray();
			dp = new int[managers.length][16];

			// process
			int output = solve();

			// output
			sb.append(output).append("\n");
		}
		System.out.println(sb);
	}

	private static int solve() {
		// 1일차
		for (int i = 1; i < 16; i++) {
			if ((i & 1) == 0)
				continue;
			if ((i & (1 << managers[0] - 'A')) == 0)
				continue;

			dp[0][i] = 1;
		}

		// dp
		for (int i = 1; i < managers.length; i++) {
			// k : 이전 단계에서의 값들
			// j : 현재 단계에서의 값들
			for (int j = 1; j < 16; j++) {
				for (int k = 1; k < 16; k++) {
					if ((j & (1 << managers[i] - 'A')) == 0)
						continue;

					// 겹치는 것이 없다면 더해주지 않아도 되니까
					if ((j & k) == 0)
						continue;
					
					// 머지
					dp[i][j] += dp[i - 1][k]; 
					dp[i][j] %= 1000000007;
				}
			}
		}

		int sum = 0;
		for (int i = 1; i < 16; i++) {
			sum += dp[managers.length - 1][i];
			sum %= 1000000007;
		}
		
		return sum;
	}
}
