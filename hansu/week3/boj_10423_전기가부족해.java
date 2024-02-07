import java.io.*;
import java.util.*;

class Solution {

    private static int[] parent;

    private static boolean union(int a, int b){
        int x = find(a), y = find(b);

        parent[Math.max(x, y)] = Math.min(x, y);
        return x != y;
    }

    private static int find(int x){
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()),
            M = Integer.parseInt(st.nextToken()),
            K = Integer.parseInt(st.nextToken());
        int[][] edges = new int[M][];
        int[] generator = new int[K];

        //init parent
        parent = new int[N + 1];
        for (int i = 0; i <= N; i++){
            parent[i] = i;
        }

        //parse generator
        //발전소들은 이미 같은 그룹인 것으로 침
        st = new StringTokenizer(br.readLine());
        generator[0] = Integer.parseInt(st.nextToken());
        for (int i = 1; i < K; i++){
            generator[i] = Integer.parseInt(st.nextToken());
            union(generator[i - 1], generator[i]);
        }

        //parse edge
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()),
                v = Integer.parseInt(st.nextToken()),
                w = Integer.parseInt(st.nextToken());

            edges[i] = new int[]{u, v, w};
        }

        //kruskal
        int cnt = 0, total = 0;
        Arrays.sort(edges, Comparator.comparingInt(o -> o[2]));
        for (int[] edge : edges){
            if (union(edge[0], edge[1])){
                total += edge[2];
                cnt++;
            }
            if (cnt == N - K){
                break;
            }
        }

        System.out.println(total);
    }

}
