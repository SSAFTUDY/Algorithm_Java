import java.io.*;
import java.util.*;

public class BOJ_1103 {

	static int arr[][];
	static int dp[][];

	static boolean visited[][];
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static int N, M, result;
	static int rotate;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		dp = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				if (s.charAt(j) == 'H') {
					arr[i][j] = -1; // H인 곳은 -1로 채움
				} else {
					arr[i][j] = s.charAt(j) - '0';
				}
			}
		}
		dfs(0, 0, 1);
		
		System.out.println(dp[0][0]);

	}

	private static void dfs(int i, int j, int count) {

		visited[i][j] = true;
	
		for (int n = 0; n < 4; n++) {
			int new_x = i + dx[n] * arr[i][j];
			int new_y = j + dy[n] * arr[i][j];
	
			if (new_x >= 0 && new_x < N && new_y >= 0 && new_y < M && arr[new_x][new_y] != -1) {
				
				if (visited[new_x][new_y]) {
					System.out.println(-1);
					System.exit(0);
				}
				
				if(dp[new_x][new_y] ==0) {
					dfs(new_x, new_y, count + 1);
				}
				
				dp[i][j] = Math.max(dp[i][j], dp[new_x][new_y] + 1);
				dfs(new_x, new_y, count + 1);
				
			}
			else {
				dp[i][j] = Math.max(dp[i][j], 1);
			}
		}
		visited[i][j] = false;
	}
}
