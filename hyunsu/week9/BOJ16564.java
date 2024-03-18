import java.io.*;
import java.util.*;

public class BOJ16564 {

    static int N, K;
    static int[] X;

    // 가장 작은 값 ~ 그 작은 값이 K를 다 더한 값
    // 팀 목표레벨 = K를 나눠 더해줬을 때 가장 큰 mid
    // mid보다 현재 값이 작다 -> mid 값이 정답이 될 수 있다.
    // mid보다 현재 값이 크다 -> 총 더한 값이 K보다 크다 -> 값의 범위를 r - 1로 줄인다.
    static int solve() {
        int l = X[0], r = X[0] + K, ret = X[0];
        while (l <= r) {
            int mid = (l + r) / 2;

            long sum = 0L;
            int diff;
            for (int i = 0; i < N; i++) {
                diff = mid - X[i];
                if (diff > 0) {
                    sum += diff;
                }
            }

            if (sum <= K) {
                ret = Math.max(ret, mid);
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        X = new int[N];
        for (int i = 0; i < N; i++) {
            X[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(X);
        System.out.println(solve());
    }

}
