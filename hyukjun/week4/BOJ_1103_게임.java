package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1103 {

	static int N;
	static int M;
	static int[][] map;
	static int[][] dp;
	static boolean[][] isChecked;

	static int max;
	static int[] drow = { 1, -1, 0, 0 };
	static int[] dcol = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// get input
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dp = new int[N][M];
		isChecked = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String in = br.readLine();
			for (int j = 0; j < M; j++) {
				if (in.charAt(j) == 'H') {
					map[i][j] = -1;
				} else {
					map[i][j] = in.charAt(j) - '0';
				}
			}
		}

		// process
		max = -99;
		dfs(0, 0);

		// output
		if (max == Integer.MAX_VALUE)
			System.out.println("-1");
		else
			System.out.println(max);
	}

	private static int dfs(int row, int col) {
		// dfs가 끝나면, 끝나기 전 까지의 최대 count 횟수를 dp 배열에 저장해두기
		// dp 배열에 값이 있으면 그 값을 사용
		// dp 배열에 값이 없으면 dfs 진행

		int curValue = map[row][col];
		int curMax = 0;
		
		// 현재 위치의 방문처리
		// 4 방향에서 갈 수 있는 최대 count를 게산
		isChecked[row][col] = true;
		for (int dir = 0; dir < 4; dir++) {
			int nextRow = row + drow[dir] * curValue;
			int nextCol = col + dcol[dir] * curValue;
			int count = 0;
			if (!isValid(nextRow, nextCol) || map[nextRow][nextCol] == -1) {
				// 다음 위치가 배열 범위를 벗어나거나 구멍이라면 count = 1
				count = 1;
			} else {
				if (isChecked[nextRow][nextCol]) {
					// 만약 한번 dfs 순회를 할 때 이미 방문한 위치를 방문할 수 있다면 무한 루프임
					max = Integer.MAX_VALUE;
					return max;
				} else if (dp[nextRow][nextCol] != 0) {
					// 만약 dp 값이 존재하면 해당 값을 이용
					count = dp[nextRow][nextCol] + 1;
				} else {
					// 그렇지 않다면 다음 위치에서 부터 dfs 순회
					count = dfs(nextRow, nextCol) + 1;
				}
			}
			curMax = Math.max(curMax, count);
			// dp 값 저장
			dp[row][col] = curMax;
			// 최대값 계산
			max = Math.max(curMax, max);
		}
		isChecked[row][col] = false;
		return curMax;
	}

	private static boolean isValid(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < M;
	}
}
