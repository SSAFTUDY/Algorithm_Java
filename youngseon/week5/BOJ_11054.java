import java.io.*;
import java.util.*;

public class BOJ_11054 {

	// 메모리 : 12288, 시간 :120ms
	static int[] arr;
	static int[] dp;
	static int[] dp2;
	static boolean asc;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		arr = new int[N + 2];
		dp = new int[N + 2];
		dp2 = new int[N + 2];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i <= N; i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (arr[j] < arr[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}

		for (int i = N; i >= 0; i--) {
			for (int j = i + 1; j <= N + 1; j++) {
				if (arr[i] > arr[j]) {
					dp2[i] = Math.max(dp2[i], dp2[j] + 1);
				}
			}
		}


		int maxVal = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = N; j >= i; j--) {
				maxVal = Math.max(maxVal, dp[i] + dp2[j] - 1);
			}
		}
		System.out.println(maxVal);
	}
}
