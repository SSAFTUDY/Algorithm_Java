import java.io.*;
import java.util.*;

public class BOJ2792 {

    static int N, M;
    static int[] gems;

    static int solve() {
        int l = 1, r = 1000000001, ret = 0;
        while (l <= r) {
            int mid = (l + r) / 2;
            long cnt = 0L;
            // 보석의 수를 증가시키면서 모든 보석을 N명 이하로 나눠줄 수 있는지 확인
            for (int i = 0; i < M; i++) {
                cnt += (gems[i] / mid);
                if (gems[i] % mid != 0) {
                    cnt++;
                }
            }

            if (cnt <= N) {
                ret = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        gems = new int[M];
        for (int i = 0; i < M; i++) {
            gems[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(solve());
    }

}
