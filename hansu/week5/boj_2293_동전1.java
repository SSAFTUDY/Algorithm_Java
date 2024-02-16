/**
 * 메모리: 11,548kb
 * 시간: 80ms
 */
import java.io.*;
import java.util.*;

public class Solution {

    private static int getNumOfCases(int[] coins, int K){
        int[] dp = new int[K + 1];

        dp[0] = 1;
        for(int coin : coins){
            for (int i = coin; i <= K; i++){
                dp[i] += dp[i - coin];
            }
        }
        return dp[K];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
        int[] coins = new int[N];

        for (int i = 0; i < N; i++){
            coins[i] = Integer.parseInt(br.readLine());
        }
        System.out.println(getNumOfCases(coins, K));
    }

}
