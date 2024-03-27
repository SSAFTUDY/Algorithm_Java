// 예제도 맞지 않음...

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BOJ_1796 {

	static int[][] startToEndIdx;
	static final int START = 0;
	static final int END = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// get input
		char[] arr = br.readLine().toCharArray();

		// process
		// 인덱스가 비어있지 않는 배열 만들기
		char[] tmp = Arrays.copyOf(arr, arr.length);
		Arrays.sort(tmp);
		Map<Character, Integer> charToIdx = new HashMap<>();
		int count = 0;
		for (int i = 0; i < tmp.length; i++) {
			char c = tmp[i];
			if (!charToIdx.containsKey(c)) {
				charToIdx.put(c, count++);
			}
		}
		startToEndIdx = new int[charToIdx.size()][2];

		// 각 문자별로 가장 왼쪽 위치와 가장 오른쪽 위치를 저장
		for (int i = 0; i < arr.length; i++) {
			char c = arr[i];
			if (startToEndIdx[charToIdx.get(c)][START] == -1) {
				startToEndIdx[charToIdx.get(c)][START] = i;
				startToEndIdx[charToIdx.get(c)][END] = i;
			} else {
				startToEndIdx[charToIdx.get(c)][START] = Math.min(startToEndIdx[charToIdx.get(c)][START], i);
				startToEndIdx[charToIdx.get(c)][END] = Math.max(startToEndIdx[charToIdx.get(c)][END], i);
			}
		}

		int[][] dp = new int[charToIdx.size()][2];
		int dist = startToEndIdx[0][END] - startToEndIdx[0][START];
		dp[0][START] = startToEndIdx[0][END] + dist; // 0에서 END까지 간 후 다시 START로 옴
		dp[0][END] = startToEndIdx[0][START] + dist; // 0에서 START까지 간 후 END로 옴
		for (int i = 1; i < dp.length; i++) {
			dist = startToEndIdx[i][END] - startToEndIdx[i][START];

			// 1. 현재 위치의 START로 옴
			// 이전 위치의 END에서 현재 위치의 START로 오는 경우와
			// 이전 위치의 START에서 현재 위치의 START로 오는 경우 비교
			dp[i][START] = Math.min(dp[i - 1][END] + Math.abs(startToEndIdx[i - 1][END] - startToEndIdx[i][END]),
									dp[i - 1][START] + Math.abs(startToEndIdx[i - 1][START] - startToEndIdx[i][END])) + dist;

			// 2. 현재 위치의 END로 옴
			// 이전 위치의 END에서 현재 위치의 END로 오는 경우와
			// 이전 위치의 START에서 현재 위치의 END로 오는 경우 비교
			dp[i][END] = Math.min(dp[i - 1][END] + Math.abs(startToEndIdx[i - 1][END] - startToEndIdx[i][START]),
								  dp[i - 1][START] + Math.abs(startToEndIdx[i - 1][START] - startToEndIdx[i][START])) + dist;
		}

		// output
		System.out.println(Math.min(dp[charToIdx.size() - 1][START], dp[charToIdx.size() - 1][END]) + arr.length);
	}

}
