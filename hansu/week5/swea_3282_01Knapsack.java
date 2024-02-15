/**
 * 메모리: 23,832kb
 * 시간: 124ms
 */
import java.io.*;
import java.util.*;

public class Solution {

    private static int knapsack(int[] volumes, int[] values, int N, int K){
        //{n, k}: 용량 k짜리 배낭에 1~n까지의 아이템들을 넣을 때의 최대 value
        int[][] dp = new int[N + 1][K + 1];
        
        for (int n = 1; n <= N; n++){
            for (int k = 1; k <= K; k++){
                if (k >= volumes[n]){
                    dp[n][k] = Math.max(dp[n - 1][k], dp[n - 1][k - volumes[n]] + values[n]);
                }
                else{
                    dp[n][k] = dp[n - 1][k];
                }
            }
        }
        return dp[N][K];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
            int[] volumes = new int[N + 1], values = new int[N + 1];

            for (int i = 1; i <= N; i++){
                st = new StringTokenizer(br.readLine());
                volumes[i] = Integer.parseInt(st.nextToken());
                values[i] = Integer.parseInt(st.nextToken());
            }

            sb.append('#').append(tc).append(' ').append(knapsack(volumes, values, N, K)).append('\n');
        }
        System.out.println(sb);
    }

}
