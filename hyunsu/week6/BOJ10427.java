import java.io.*;
import java.util.*;

public class BOJ10427 {

    static int T, N;
    static int[] A;
    static long[] sum;
    
    static long calc(int m) {
        long min = Long.MAX_VALUE;
        for (int i = m; i <= N; i++) {
            min = Math.min(min, ((long) A[i] * m) - sum[i] + sum[i - m]);
        }
        
        return min;
    }

    // 추가로 갚아야 할 돈 = 가장 큰 빌린 돈 * M - 빌린 돈의 합
    // 빌린 동 중 가장 큰 돈 -> 최소, 빌린 돈의 합 -> 최대 일 때 최솟값
    static long solve() {
        long ans = 0L;
        Arrays.sort(A);
        
        sum = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            sum[i] = A[i] + sum[i - 1]; //누적합
        }
        
        for (int i = 1; i <= N; i++) {
            ans += calc(i);
        }
        
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());

            A = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            bw.write(solve() + "\n");
        }

        bw.flush();
        bw.close();
    }

}
