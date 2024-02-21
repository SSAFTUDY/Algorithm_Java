import java.io.*;
import java.util.*;

public class SWEA3282 {

    static final int VOLUME = 0, COST = 1;
    static int T, N, K;
    static int[][] dp, item;

    static int solve() {
        for (int n = 1; n <= N; n++) {
            for (int v = 1; v <= K; v++) {
                if (item[n][VOLUME] <= v) {
                    dp[n][v] = Math.max(item[n][COST] + dp[n - 1][v - item[n][VOLUME]],
                        dp[n - 1][v]);
                } else {
                    dp[n][v] = dp[n - 1][v];
                }
            }
        }

        return dp[N][K];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            dp = new int[N + 1][K + 1];

            item = new int[N + 1][2];
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                item[i][0] = Integer.parseInt(st.nextToken());
                item[i][1] = Integer.parseInt(st.nextToken());
            }

            System.out.println("#" + tc + " " + solve());
        }
    }

}
