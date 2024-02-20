import java.io.*;
import java.util.*;

public class SWEA3304 {

    static int T;
    static String s1, s2;

    //LCS(i,j) = 0 / LCS(i-1, j-1) + 1 / Max(LCS(i, j-1), LCS(i-1, j))
    static int solve() {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());

            s1 = st.nextToken();
            s2 = st.nextToken();

            System.out.println("#" + tc + " " + solve());
        }
    }

}
