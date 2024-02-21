import java.io.*;
import java.util.*;

public class BOJ11054 {

    static int N, ans = 0;
    static int[] A;
    static int[][] dp;

    // LIS 길이 + LDS 길이 - 1 (중복)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        A = new int[N + 1];
        dp = new int[N + 1][2];
        st = new StringTokenizer(br.readLine());

        //입력과 동시에 LIS 계산
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());

            int tmp = 0;
            for (int j = 1; j < i; j++) {
                if (A[i] > A[j] && dp[j][0] > tmp) {
                    tmp = dp[j][0];
                }
            }

            dp[i][0] = tmp + 1;
        }

        //LDS 계산
        for (int i = N; i >= 1; i--) {
            int tmp = 0;
            for (int j = N; j > i; j--) {
                if (A[i] > A[j] && dp[j][1] > tmp) {
                    tmp = dp[j][1];
                }
            }

            dp[i][1] = tmp + 1;
        }

        for (int i = 1; i <= N; i++) {
            ans = Math.max(ans, dp[i][0] + dp[i][1]);
        }

        System.out.println(ans - 1);
    }

}
