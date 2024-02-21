import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA3282 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            sb.append("#").append(t+1).append(" ");
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[] v = new int[N];
            int[] c = new int[N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                v[i] = Integer.parseInt(st.nextToken());
                c[i] = Integer.parseInt(st.nextToken());
            }

            int[][] dp = new int[N+1][K+1];
            /**
             * dp[i][j] -> i: 현재 물건의 번호 j: 가방의 최대 부피 (해당 부피까지 넣을 수 있음, K까지 가는 과정)
             * 현재 물건을 안넣을 경우 -> i-1까지 j만큼 넣었을 때와 동일
             * 현재 물건을 넣어서 부피가 j가 되는 경우 -> i-1까지 j-v[i] 만큼 넣고 이번에 v[i] 만큼 부피를 채우고 가치(c[i])를 더해주기
             * dp의 탐색 과정 -> 1개를 넣어서 1,2,3...K 만큼 부피를 채웠을 때 가치의 최대 합
             * 2개를 넣어서 1,2,3,...K만큼 부피를 채웠을 때 가치의 최대합 -> N개를 채웠을 때 dp[N][K]
             */
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <=K ; j++) {
                    if(v[i] <= j){   //현재 물건의 부피가 가방에 넣을 수 있는 최대 부피 보다 작은 경우
                        dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-v[i]] + v[i]);
                    }else{
                        dp[i][j] = dp[i-1][j];
                    }
                }
            }
            sb.append(dp[N][K]).append("\n");
        }
        System.out.println(sb);
    }

}
