import java.io.*;
import java.util.*;

public class BOJ16562 {

    static int N, M, k, ans = 0;
    static int[] A;
    static int[] parents;
    static boolean[] visited;

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        //친구비가 제일 적게 드는 쪽을 그룹의 대장으로
        if (A[a] < A[b]) {
            parents[b] = a;
        } else {
            parents[a] = b;
        }
    }

    static int find(int x) {
        if (x == parents[x]) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        A = new int[N + 1];
        parents = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            parents[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            union(v, w);
        }

        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            int friend = find(i);

            if (!visited[friend]) {
                ans += A[friend];
                visited[friend] = true;
            }

            if (ans > k) {
                System.out.println("Oh no");
                return;
            }
        }

        System.out.println(ans);
    }

}
