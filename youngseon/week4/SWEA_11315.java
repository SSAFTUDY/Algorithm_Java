
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_11315 {

	static char[][] arr;
	static boolean[][] visited;
	static int N, result;
	static int[] dx = { 0, 1, -1, 0, 1, 1, -1, -1 };
	static int[] dy = { 1, 0, 0, -1, 1, -1, 1, -1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for (int t = 0; t < T; t++) {
			result = 0;
			N = Integer.parseInt(br.readLine());
			arr = new char[N][N];
			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				arr[i] = br.readLine().toCharArray();
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j] && arr[i][j] == 'o') {
						for (int k = 0; k < 8; k++) {
							dfs(i, j, k, 1);
							if(result ==1) {
								break;
							}
						}
					}
					if(result ==1) {
						break;
					}
				}
				if(result ==1) {
					break;
				}
			}
			if (result == 0) {
				sb.append("#").append(t + 1).append(" NO\n");
			} else {
				sb.append("#").append(t + 1).append(" YES\n");
			}
		}
		System.out.println(sb);
	}

	private static void dfs(int x, int y, int dir, int count) {
		if (count == 5) {
			result = 1;
			return;
		}

		visited[x][y] = true;

		int new_x = x + dx[dir];
		int new_y = y + dy[dir];
		if (new_x >= 0 && new_x < N && new_y >= 0 && new_y < N && !visited[new_x][new_y] && arr[new_x][new_y] == 'o') {
			visited[new_x][new_y] = true;
			dfs(new_x, new_y, dir, count + 1);
			visited[new_x][new_y] = false;
		}

	}

}
