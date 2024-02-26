package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_10427 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int T = 0; T < TC; T++) {

			// get input
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[] arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			// process
			// 1 3 4 5 5 8
			// 0 1 2 3 4 5
			Arrays.sort(arr);
			long output = 0;
			for (int choice = 1; choice < N; choice++) {
				// 현재 인덱스와, 그 전 인덱스를 choice번 선택
				long minSum = Long.MAX_VALUE;
				for (int i = choice; i < N; i++) {
					long currentSum = 0;
					for (int j = 0; j < choice; j++) {
						currentSum += arr[i] - arr[i - j - 1];
					}
					minSum = Math.min(minSum, currentSum);
				}
				output += minSum;
			}

			// output
			sb.append(output).append('\n');

		}

		System.out.println(sb);

	}

}
