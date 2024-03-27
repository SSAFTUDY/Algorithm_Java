package samsung01;

import java.io.*;
import java.util.*;

public class BOJ17406 {
	static int arr[][], original[][];
	static int[][] rInfo;
	static int min = Integer.MAX_VALUE;
	static int N, M, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		rInfo = new int[K][3];

		arr = new int[N + 1][M + 1];
		original = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				original[i][j] = arr[i][j];
			}
		}
		int min = Integer.MAX_VALUE;

		int[] input = new int[K];
		int[] output = new int[K];
		boolean[] visited = new boolean[K];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			rInfo[i][0] = r;
			rInfo[i][1] = c;
			rInfo[i][2] = s;
			input[i] = i;
		}

		
		permutation(input, output, visited, K, K);
		System.out.println(min);

	}

	private static void rotate(int r, int c, int s) {
		// (r,c)가 중심
		int start_x = r - s;
		int start_y = c - s;
		int end_x = r + s;
		int end_y = c + s;
		int tmp;

		for (int i = 0; i < s; i++) {

			int left_top = arr[start_x][start_y];
			int right_top = arr[start_x][end_y];
			int right_bottom = arr[end_x][end_y];
			int left_bottom = arr[end_x][start_y];

			for (int j = end_y; j >= start_y; j--) {
				arr[start_x][j] = arr[start_x][j - 1];
			}
			for (int j = end_x; j >= start_x + 1; j--) {
				if (j == start_x + 1) {
					arr[start_x + 1][end_y] = right_top;
				} else {
					arr[j][end_y] = arr[j - 1][end_y];
				}
			}

			for (int j = start_y; j < end_y; j++) {
				if (j == end_y - 1) {
					arr[end_x][j] = right_bottom;
				} else {
					arr[end_x][j] = arr[end_x][j + 1];
				}
			}

			for (int j = start_x; j < end_x; j++) {
				if (j == end_x - 1) {
					arr[j][start_y] = left_bottom;
				} else {
					arr[j][start_y] = arr[j + 1][start_y];
				}
			}
			start_x += 1;
			start_y += 1;
			end_x -= 1;
			end_y -= 1;
		}

	}

	private static void permutation(int input[], int output[], boolean visited[], int depth, int r) {
		if (depth == r) {
			System.out.println("output");
			for (int i = 0; i < r; i++) {
				System.out.print(output[i] + " ");
				rotate(rInfo[i][0], rInfo[i][1], rInfo[i][2]);
			}
			findMin();
			arr = original;
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				output[depth] = input[i];
				permutation(input, output, visited, depth + 1, r);
				visited[i] = false;
			}
		}
	}

	private static void findMin() {
		for (int l = 1; l <= N; l++) {
			int sum = 0;
			for (int j = 1; j <= M; j++) {
				sum += arr[l][j];
			}
			min = Math.min(min, sum);
		}
	}
}
