import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2293 {

    /**
     *  3 10
     *  1 2 5
     *  7원이라 가정하면 2+5와 5+2는 같은 경우. 이 경우를 제거하는게 핵심
     *  이를 위해 2원짜리로 만들 수 있는 경우를 전부 깔아둠 -> dp[2] = 1 설정
     *  이후 5원짜리로 만들 수 있는 경우를 추가하면 dp[7]에서는 2+5만 계산되어 중복을 방지할 수 있다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(coins);

        int[] dp = new int[k+1];

        for (int coin : coins) {
            if(coin>k){
                break;
            }
            dp[coin] += 1;
            for (int i = 1; i <= k; i++) {
                if(i-coin>=0) {
                    dp[i] += dp[i - coin];
                }
            }
        }
        System.out.println(dp[k]);
    }

}
