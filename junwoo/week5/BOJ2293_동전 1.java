import java.util.*;
import java.io.*;

public class Main {
	static int N, K;
	static int[] value;
	static int[] prev_dp, current_dp;
	
	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("res/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		value = new int[N+1];
		prev_dp = new int[K+1]; current_dp = new int[K+1];
		prev_dp[0] = 1;
		for (int i = 1; i < N+1; i++) {
			value[i] = Integer.parseInt(br.readLine());
		}
		for (int i = 1; i < N+1; i++) {
			for (int j = 0; j < K+1; j++) {
				if (j < value[i]) {
					current_dp[j] = prev_dp[j];
				}
				else {
					current_dp[j] = prev_dp[j] + current_dp[j-value[i]];
				}
			}
			int[] temp = prev_dp;
			prev_dp = current_dp;
			current_dp = temp;
		}
		System.out.println(prev_dp[K]);
	}
}