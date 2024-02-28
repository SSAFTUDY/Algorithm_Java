/**
 * Fail
 */
import java.io.*;
import java.util.*;

public class Solution {
	
	private static int N, M;
	private static int[] snackA, snackB;

	private static int getMaxSnack() {
		int aIdx = 0, minBIdx = 0, maxBIdx = snackB.length - 1;
		int res = 0;
		boolean isSelectTurn = true;
		
		//Greedy하게 snackB 배치
		while(aIdx < N && minBIdx <= maxBIdx) {
			if (isSelectTurn) {
				if (snackA[aIdx] < snackB[maxBIdx]) {
					res += snackB[maxBIdx--];
				} else {
					res += snackA[aIdx++];
				}
				
				isSelectTurn = false;
			}
			else {
				if (snackA[aIdx] < snackB[minBIdx]) {
					aIdx++;
				} else {
					minBIdx++;
				}
				
				isSelectTurn = true;
			}
		}

		//snackA가 남는 경우 DP로 최댓값 계산
		int[][] dp = {};
		if (aIdx < N) {
			dp = new int[N - aIdx][2];
			
			dp[0][1] = snackA[aIdx];
			for (int i = 1; i < N - aIdx; i++) {
				dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
				dp[i][1] = dp[i - 1][0] + snackA[aIdx + i];
			}
		}
		if (minBIdx <= maxBIdx) {
			dp = new int[maxBIdx - minBIdx + 1][2];
			
			dp[0][1] = snackB[minBIdx];
			for (int i = 1; i <= maxBIdx - minBIdx; i++) {
				dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
				dp[i][1] = dp[i - 1][0] + snackB[minBIdx + i];
			}
		}
		return res + Math.max(dp[dp.length - 1][0], dp[dp.length - 1][1]);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			//parsing
			N = Integer.parseInt(br.readLine());
			snackA = new int[N];
			for (int i = 0; i < N; i++) {
				snackA[i] = Integer.parseInt(br.readLine());
			}
			
			M = Integer.parseInt(br.readLine());
			snackB = new int[M];
			for (int i = 0; i < M; i++) {
				snackB[i] = Integer.parseInt(br.readLine());
			}
			
			//최대/최소를 뽑아가기 편하도록 정렬
			Arrays.sort(snackB);
			sb.append('#').append(tc).append(' ').append(getMaxSnack()).append('\n');
		}
		System.out.print(sb);
	}
	
}
