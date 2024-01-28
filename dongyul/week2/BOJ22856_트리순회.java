import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1. 간선의 제일 오른쪽 자식(중위 순회가 끝나는 지점)을 거치는 간선은 1번 지남
 * 2. 이를 제외한 간선은 전부 2번 지남
 * 모두 2번 이동했을 때 -> (N-1)*2에서 1번 case의 이동 횟수를 빼주면 정답
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] right = new int[N+1];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            right[P] = R;
        }
        int curr = 1;
        int cnt = -1;
        while (curr != -1) {
            curr = right[curr];
            cnt += 1;
        }
        System.out.println((N-1)*2-cnt);    //오른
    }
}
