import java.io.*;
import java.util.*;

public class BOJ1976 {

    static int N, M;
    static int[] parents;

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a < b) {
            parents[b] = a;
        } else {
            parents[a] = b;
        }
    }

    static int find(int x) {
        if (parents[x] == x) {
            return x;
        }

        return parents[x] = find(parents[x]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int isConnected = Integer.parseInt(st.nextToken());
                if (isConnected == 1) {
                    union(i, j);
                }
            }
        }

        int cur = 0, nxt = 0;
        st = new StringTokenizer(br.readLine());
        cur = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M - 1; i++) {
            nxt = Integer.parseInt(st.nextToken());
            if (find(cur) != find(nxt)) {
                System.out.println("NO");
                return;
            }
            cur = nxt;
        }

        System.out.println("YES");
    }

}
