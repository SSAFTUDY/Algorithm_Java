import java.io.*;
import java.util.*;

public class BOJ1103 {

    static int N, M;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static char[][] board;
    static boolean[][] visited;
    static int[][] dp; //dp[i][j]: (i, j)에서 최대 n번 움직일 수 있다.

    static boolean isRangedAndNotH(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M && board[r][c] != 'H';
    }

    static int dfs(int r, int c) {
        //밖을 벗어나거나 구멍인 경우 갈 수 없으므로 0
        if (!isRangedAndNotH(r, c)) {
            return 0;
        }

        //이미 방문한 곳에 왔다는 것은 무한으로 돌 수 있다는 뜻 -> 종료
        if (visited[r][c]) {
            System.out.println("-1");
            System.exit(0);
        }

        //이미 dp값 갱신이 이루어진 좌표의 경우 넘어가기
        if (dp[r][c] != -1) {
            return dp[r][c];
        }

        //방문 처리를 하고 4방향으로 돈 값에 대해 가장 많이 움직일 수 있는 방향으로 dp 값 갱신
        visited[r][c] = true;
        int memo = 0;
        for (int dir = 0; dir < 4; dir++) {
            int nr = r + dr[dir] * Character.getNumericValue(board[r][c]);
            int nc = c + dc[dir] * Character.getNumericValue(board[r][c]);

            memo = Math.max(memo, dfs(nr, nc) + 1); //구멍에 빠지거나 밖으로 나가는 것도 1회로 쳐야 함...
        }
        visited[r][c] = false;

        return dp[r][c] = memo; //dp값 갱신해주고 다음으로 넘어가기
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        visited = new boolean[N][M];
        dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(0, 0));
    }

}
