import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    /**
     * 양쪽으로 한 번씩 증가하는 부분 문자열을 구해서 값 더하면 될듯..?
     *
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];
        for (int k = 0; k < N; k++){
            dp[k] = 1;
            for (int i = 0; i < k; i++){
                if(arr[i] < arr[k]){
                    dp[k] = Math.max(dp[k], dp[i] + 1);
                }
            }
        }

        int[] dp2 = new int[N];
        for (int k = 0; k < N; k++){
            dp2[k] = 1;
            for (int i = 0; i < k; i++){
                if(arr[i] > arr[k]){
                    dp2[k] = Math.max(dp[k], dp[i] + 1);
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            answer = Math.max(answer,  dp[i] + dp2[i]-1);
        }
        System.out.println(answer);
    }

}
