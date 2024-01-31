import java.io.*;
import java.util.*;

public class SWEA1248 {

    static final int LEFT = 0, RIGHT = 1;
    static int T, V, E, cnt;
    static int[][] tree;
    static int[] depth, parent;
    static boolean[] visited;

    static void setDepth(int node, int dep) {
        depth[node] = dep;

        if (tree[node][LEFT] != 0) {
            setDepth(tree[node][LEFT], dep + 1);
        }
        if (tree[node][RIGHT] != 0) {
            setDepth(tree[node][RIGHT], dep + 1);
        }
    }

    static int getLCA(int a, int b) {
        //기본 LCA 알고리즘 적용 -> 둘의 depth를 맞추고, 부모가 같아질 때까지 타고 올라가기
        while (depth[a] > depth[b]) {
            a = parent[a];
        }
        while (depth[a] < depth[b]) {
            b = parent[b];
        }

        while (parent[a] != parent[b]) {
            a = parent[a];
            b = parent[b];
        }

        return parent[a];
    }

    static void countSubTree(int root) {
        cnt++;

        //자식이 없으면 종료
        if (tree[root][LEFT] == 0 && tree[root][RIGHT] == 0) {
            return;
        }

        if (tree[root][LEFT] != 0 && !visited[tree[root][LEFT]]) {
            visited[tree[root][LEFT]] = true;
            countSubTree(tree[root][LEFT]);
        }
        if (tree[root][RIGHT] != 0 && !visited[tree[root][RIGHT]]) {
            visited[tree[root][RIGHT]] = true;
            countSubTree(tree[root][RIGHT]);
        }
    }

    //union-find로 푸는 방법과 LCA로 푸는 방법 2개가 있다고 생각하는데
    //일단은 트리푸는 주간이니까 LCA로 풀었음. (뭐가 더 효율적일까)
    /*
        LCA 방식
        1. 트리의 노드들에 대해 root 기준으로 depth 기록해두기
        2. 두 타겟 노드의 depth가 다르면 맞추기
        3. depth가 동일하게 됐다면, 각각 부모를 한 번씩 올라가면서 같은 부모인지 확인
     */
    static int[] solve(int a, int b) {
        cnt = 0;

        //항상 1번 노드가 root이므로 root부터 depth 0으로 맞춰서 depth 세팅
        setDepth(1, 0);
        int lca = getLCA(a, b);
        countSubTree(lca);

        return new int[] {lca, cnt};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree = new int[V + 1][2]; //0: left, 1: right;
            depth = new int[V + 1]; //해당 노드의 depth
            parent = new int[V + 1]; //해당 노드의 부모
            visited = new boolean[V + 1]; //해당 노드의 방문 여부

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++) {
                int p = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                if (tree[p][LEFT] == 0) {
                    tree[p][LEFT] = c;
                } else {
                    tree[p][RIGHT] = c;
                }

                parent[c] = p;
            }

            int[] res = solve(a, b);
            bw.write("#" + tc + " " + res[0] + " " + res[1] + "\n");
        }

        bw.flush();
        bw.close();
    }

}
