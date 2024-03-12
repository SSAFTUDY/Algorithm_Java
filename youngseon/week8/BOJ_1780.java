
import java.io.*;
import java.util.*;

// 318168 kb, 844 ms

public class BOJ_1780 {

	static int[][] arr;
	static int[] result = new int[3];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		arr = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		calculate(0, 0, N);
		sb.append(result[0]).append("\n").append(result[1]).append("\n").append(result[2]);
		System.out.println(sb);
	}

	private static void calculate(int i, int j, int size) {
		if (!isSame(i, j, size)) {
			calculate(i, j, size / 3);
			calculate(i, j + size / 3, size / 3);
			calculate(i, j + size / 3 * 2, size / 3);
			calculate(i + size / 3, j, size / 3);
			calculate(i + size / 3, j + size / 3, size / 3);
			calculate(i + size / 3, j + size / 3 * 2, size / 3);
			calculate(i + size / 3 * 2, j, size / 3);
			calculate(i + size / 3 * 2, j + size / 3, size / 3);
			calculate(i + size / 3 * 2, j + size / 3 * 2, size / 3);
		}
	}

	private static boolean isSame(int i, int j, int size) {
		int n = arr[i][j];
		for (int x = i; x < i + size; x++) {
			for (int y = j; y < j + size; y++) {
				if (arr[x][y] != n) {
					return false;
				}

			}
		}
		if (n == -1) {
			result[0]++;
		} else if (n == 0) {
			result[1]++;
		} else {
			result[2]++;
		}
		return true;
	}

}
