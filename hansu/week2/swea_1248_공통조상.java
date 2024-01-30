import java.io.*;
import java.util.*;

public class Solution {

    private static int[] parent, subtreeSizes;

    private static void union(int p, int c){
        parent[c] = p;
        updateSubtreeSize(c, subtreeSizes[c]);
    }

    /** 각 노드가 갖고 있는 subtree 크기 업데이트 */
    private static void updateSubtreeSize(int c, int addedChildren){
        while (c != parent[c]) {
            subtreeSizes[parent[c]] += addedChildren;
            c = parent[c];
        }
    }

    /** 하나씩 거슬러 올라가며 공통 조상 탐색 */
    private static int getCommonAncestor1(int A, int B){
        BitSet bs = new BitSet();

        while (A > 1 || B > 1) {
            if (A > 1 && bs.get(A)) return A;
            bs.set(A);
            if (B > 1 && bs.get(B)) return B;
            bs.set(B);
            A = parent[A];
            B = parent[B];
        }
        return 1;
    }

    public static void main(String[] args) throws IOException {
   	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken()), E = Integer.parseInt(st.nextToken()),
                A = Integer.parseInt(st.nextToken()), B = Integer.parseInt(st.nextToken());

            //init
            parent = new int[V + 1];
            subtreeSizes = new int[V + 1];
            Arrays.fill(subtreeSizes, 1);
            for (int i = 0; i <= V; i++) parent[i] = i;

            //parse data
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++)
                union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

//            int res = getCommonAncestor1(A, B);
            int res = getCommonAncestor2(V, A, B);
            sb.append('#').append(tc).append(' ').append(res).append(' ').append(subtreeSizes[res]).append('\n');
        }
        
        System.out.println(sb);
    }
    
}
