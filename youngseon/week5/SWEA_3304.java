import java.io.*;
import java.util.*;

// 메모리: 33,276 kb, 실행시간 : 178 ms

public class SWEA_3304 {
    static String s1;
    static String s2;
    static int maxVal;
    static int dp[][];
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
 
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
 
            s1 = st.nextToken();
            s2 = st.nextToken();
 
            maxVal =0;
            dp = new int[s1.length() + 1][s2.length() + 1];
 
            for (int i = 1; i <= s1.length(); i++) {
                for (int j = 1; j <= s2.length(); j++) {
                    if (s2.charAt(j - 1) == s1.charAt(i - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                        if (maxVal < dp[i][j]) {
                            maxVal = dp[i][j];
                        }
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
            sb.append("#").append(t + 1).append(" ").append(maxVal).append("\n");
        }
        System.out.println(sb);
    }
}
