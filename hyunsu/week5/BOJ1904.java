import java.io.*;
import java.util.*;

public class BOJ1904 {

    static int N;
    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dp = new long[N + 1]; //1로 끝나는 경우 / 2로 끝나는 경우

        if (N == 1) {
            System.out.println(1);
        } else if (N == 2) {
            System.out.println(2);
        } else {
            dp[1] = 1; // 1
            dp[2] = 2; // 11, 00

            for (int i = 3; i <= N; i++) {
                dp[i] = (dp[i - 1] + dp[i - 2]) % 15746;
            }

            System.out.println(dp[N] % 15746);
        }
    }

}
