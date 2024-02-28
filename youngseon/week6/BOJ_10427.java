import java.io.*;
import java.util.*;

public class BOJ_10427 {
	static int result, selectCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for (int t = 0; t < T; t++) {

			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[] arr = new int[N + 1];
			int S = 0;

			for (int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 2; i <= N; i++) {
				selectCnt = i;
				result = Integer.MAX_VALUE;
				boolean[] visited = new boolean[N + 1];
				combination(arr, visited, 1, N, i, 0, 0);
				S += result;
			}
			System.out.println(S);
		}
	}

	static void combination(int[] arr, boolean[] visited, int start, int n, int r, int sum, int maxNum) {
		if (r == 0) {
			result = Math.min(result, selectCnt * maxNum - sum);
		}

		for (int i = start; i <= n; i++) {
			visited[i] = true;
			combination(arr, visited, i + 1, n, r - 1, sum + arr[i], Math.max(maxNum, arr[i]));
			visited[i] = false;
		}
	}
}
