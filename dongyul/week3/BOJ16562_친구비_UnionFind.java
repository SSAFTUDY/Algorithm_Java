import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 비용이 적은 친구를 부모로 두며 union find
 */

public class Main {

    static int find(int x) {
        while (x != parent[x]) {
            x = parent[x];
        }
        return x;
    }

    static void union(int a, int b) {
        if (cost[a] > cost[b]) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }

    static int[] parent;
    static int[] cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        cost = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(find(a),find(b));
        }

        int sum = 0;
        for(int i = 1; i<=N; i++){
            if(parent[i] == i){
                sum+=cost[i];
            }
        }

        if (K >= sum) {
            System.out.println(sum);
        } else {
            System.out.println("Oh no");
        }
    }

}
