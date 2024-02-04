import java.io.*;
import java.util.*;

class Solution {

    private static int[] parent;
    private static int res = 0;

    private static boolean union(int[] edge) {
        int x = find(edge[0]), y = find(edge[1]);

        if (x == y) {
            return false;
        }
        res += edge[2];
        parent[Math.max(x, y)] = Math.min(x, y);
        return true;
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken()), E = Integer.parseInt(st.nextToken()), cnt = 0;
        int[][] edges =  new int[E][3];

        //init parent
        parent = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        //parsing edges
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            edges[i][2]  = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(edges, Comparator.comparingInt(o -> o[2]));
        for (int[] edge : edges) {
            if (union(edge)) {
                if (++cnt == V - 1) {
                    break;
                }
            }
        }
        System.out.println(res);
    }

}
