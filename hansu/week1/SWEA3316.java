import java.io.*;

public class Solution {
	private static final int P = 1000000007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			String managers = br.readLine();
			int[] managerBit = new int[managers.length()];
			int[][] dp = new int[managers.length()][16];
			long res = 0;

			for (int i = 0; i < managers.length(); i++)
				managerBit[i] = 1 << managers.charAt(i) - 'A';
			for (int caseBit = 1; caseBit < 16; caseBit++)
				if (caseBit % 2 == 1 && (caseBit & managerBit[0]) > 0)
					dp[0][caseBit] = 1;

			for (int i = 1; i < dp.length; i++)
				for (int caseBit = 1; caseBit < 16; caseBit++)
					if ((caseBit & managerBit[i]) == 0)
						dp[i][caseBit] = 0;
					else
						for (int preCaseBit = 1; preCaseBit < 16; preCaseBit++)
							if ((caseBit & preCaseBit) > 0)
								dp[i][caseBit] = (dp[i][caseBit] + dp[i - 1][preCaseBit]) % P;
			for (int n : dp[dp.length - 1])
				res = (res + n) % P;
			sb.append('#').append(tc).append(' ').append(res).append('\n');
		}
		System.out.println(sb);
	}
}
