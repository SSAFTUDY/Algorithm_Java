import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1904 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        if(N == 1){
            System.out.println(1);
            System.exit(0);
        }
        int[] dp = new int[N+1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= N; i++) {
//            dp[i] += (dp[i-2])%15746; //00을 붙이는 경우
//            dp[i] += (dp[i-1])%15746; //1을 붙이는 경우
            dp[i] += (dp[i-2] + dp[i-1]) % 15746;
        }
        System.out.println(dp[N]);
    }

}
