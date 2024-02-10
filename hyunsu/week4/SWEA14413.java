import java.io.*;
import java.util.*;

public class SWEA14413 {

    static int T, N, M;
    static char[][] A;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static boolean[][] visited;

    static boolean isRanged(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    static boolean bfs(int r, int c) {
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(r, c, A[r][c]));
        visited[r][c] = true;

        while (!q.isEmpty()) {
            Pos cur = q.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nr = cur.r + dr[dir];
                int nc = cur.c + dc[dir];

                if (!isRanged(nr, nc) || visited[nr][nc]) {
                    continue;
                }
                if (A[nr][nc] == cur.color) {
                    return false;
                }
                if (A[nr][nc] == '?') {
                    A[nr][nc] = cur.color == '#' ? '.' : '#';
                }

                q.offer(new Pos(nr, nc, A[nr][nc]));
                visited[nr][nc] = true;
            }
        }

        return true;
    }

    static String solve() {
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (A[i][j] != '?' && !visited[i][j]) {
                    if (!bfs(i, j)) {
                        return "impossible";
                    }
                }
            }
        }

        return "possible";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            A = new char[N][M];
            for (int i = 0; i < N; i++) {
                String s = br.readLine();
                for (int j = 0; j < M; j++) {
                    A[i][j] = s.charAt(j);
                }
            }

            bw.write("#" + tc + " " + solve() + "\n");
        }

        bw.flush();
        bw.close();
    }

    static class Pos {

        int r, c, color;

        public Pos(int r, int c, int color) {
            this.r = r;
            this.c = c;
            this.color = color;
        }
    }
}
