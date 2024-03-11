// 메모리 : 16740kb, 시간 : 852ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1780 {

	static int minus = 0;
	static int zero = 0;
	static int plus = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		// get input
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// process
		solve(arr, new int[] { 0, 0 }, N);
		
		// output
		sb.append(minus).append('\n').append(zero).append('\n').append(plus);
		System.out.println(sb);
	}

	private static void solve(int[][] arr, int[] start, int length) {
		// valid
		int first = arr[start[0]][start[1]];
		boolean isValid = true;
		for (int i = start[0]; i < start[0] + length; i++) {
			for (int j = start[1]; j < start[1] + length; j++) {
				if (first != arr[i][j]) {
					isValid = false;
					break;
				}
			}
		}

		if (isValid) {
			if (first == -1) {
				minus++;
			} else if (first == 0) {
				zero++;
			} else {
				plus++;
			}
		} else {
			solve(arr, new int[] { start[0], start[1] }, length / 3);
			solve(arr, new int[] { start[0] + length / 3, start[1] }, length / 3);
			solve(arr, new int[] { start[0] + length * 2 / 3, start[1] }, length / 3);

			solve(arr, new int[] { start[0], start[1] + length / 3 }, length / 3);
			solve(arr, new int[] { start[0] + length / 3, start[1] + length / 3 }, length / 3);
			solve(arr, new int[] { start[0] + length * 2 / 3, start[1] + length / 3 }, length / 3);

			solve(arr, new int[] { start[0], start[1] + length * 2 / 3 }, length / 3);
			solve(arr, new int[] { start[0] + length / 3, start[1] + length * 2 / 3 }, length / 3);
			solve(arr, new int[] { start[0] + length * 2 / 3, start[1] + length * 2 / 3 }, length / 3);
		}
	}
}
