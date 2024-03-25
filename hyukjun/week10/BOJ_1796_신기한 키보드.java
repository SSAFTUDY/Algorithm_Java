package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class BOJ_1796 {

	static int[][] startToEndIdx;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// get input
		char[] arr = br.readLine().toCharArray();

		// process
		startToEndIdx = new int[charToIdx('z')][2];

		// 각 문자별로 가장 왼쪽 위치와 가장 오른쪽 위치를 저장
		char startWord = 'z' - 'a';
		char lastWord = 'a' - 'a';
		for (int i = 0; i < arr.length; i++) {
			char c = arr[i];
			startWord = startWord < c ? startWord : c;
			lastWord = lastWord > c ? lastWord : c;
			if (startToEndIdx[charToIdx(c)][0] == -1) {
				startToEndIdx[charToIdx(c)][0] = i;
				startToEndIdx[charToIdx(c)][1] = i;
			} else {
				startToEndIdx[charToIdx(c)][0] = Math.min(startToEndIdx[charToIdx(c)][0], i);
				startToEndIdx[charToIdx(c)][1] = Math.max(startToEndIdx[charToIdx(c)][1], i);
			}
		}

		// 가장 왼쪽 -> 오른쪽으로 가는 경우
		// 가장 왼쪽 <- 오른쪽으로 가는 경우
		// 두가지 경우 중 작은 경우의 수를 선택
		int[][] dp = new int[charToIdx('z')][2];
		int dist = startToEndIdx[startWord][1] - startToEndIdx[startWord][0];
		dp[0][0] = startToEndIdx[startWord][1] + dist;
		dp[0][1] = startToEndIdx[startWord][0] + dist;
		for (int i = 1; i < startToEndIdx.length; i++) {
			if (startToEndIdx[i][0] == -1)
				continue;

			dist = startToEndIdx[i][1] - startToEndIdx[i][0];
			dp[i][0] = Math.min(dp[startWord][0] + Math.abs(startToEndIdx[i][1] - startToEndIdx[startWord][0]),
					dp[startWord][1] + Math.abs(startToEndIdx[i][1] - startToEndIdx[startWord][1]) + dist);
			dp[i][1] = Math.min(dp[startWord][0] + Math.abs(startToEndIdx[i][0] - startToEndIdx[startWord][0]),
					dp[startWord][1] + Math.abs(startToEndIdx[i][0] - startToEndIdx[startWord][1]) + dist);

			startWord = i;
		}

		// output
		System.out.println(Math.min(dp[lastWord][0], dp[lastWord][1]) + arr.length);
	}

	private static int charToIdx(char c) {
		return c - 'a';
	}

}
