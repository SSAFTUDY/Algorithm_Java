import java.io.*;
import java.util.*;

public class SWEA11315 {

    static int T, N;
    static int[][] dir = {
        {1, 0}, {0, 1}, {1, 1}, {1, -1}
    };
    static char[][] concave;
    static Queue<int[]> q;

    static boolean isRanged(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    static String solve() {
        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur[0], nc = cur[1], cnt = 0;
                for (int j = 0; j < 4; j++) {
                    nr += dir[i][0];
                    nc += dir[i][1];

                    if (isRanged(nr, nc) && concave[nr][nc] == 'o') {
                        cnt++;
                    } else {
                        break;
                    }
                }

                if (cnt == 4) {
                    return "YES";
                }
            }
        }

        return "NO";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());

            concave = new char[N][N];
            q = new LinkedList<>();
            for (int i = 0; i < N; i++) {
                String s = br.readLine();
                for (int j = 0; j < N; j++) {
                    concave[i][j] = s.charAt(j);
                    if (concave[i][j] == 'o') {
                        q.offer(new int[]{i, j});
                    }
                }
            }

            bw.write("#" + tc + " " + solve() + "\n");
        }

        bw.flush();
        bw.close();
    }

}
