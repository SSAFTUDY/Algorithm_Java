import java.io.*;
// 15452 kb, 92 ms
public class BOJ_1904 {

	static int[] DP;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		DP = new int[1000001];

		DP[0] = 0;
		DP[1] = 1;
		DP[2] = 2;

		for (int i = 3; i <= N; i++) {
			DP[i] = (DP[i - 2] + DP[i - 1]) % 15746;

		}

		System.out.println(DP[N]);
	}
}
