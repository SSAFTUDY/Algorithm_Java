import java.io.*;
import java.util.*;

public class Solution {

    private static int[] parent, subtreeSizes;

    private static void union(int p, int c){
        parent[c] = p;
        updateSubtreeSize(c, subtreeSizes[c]);
    }

    private static void updateSubtreeSize(int c, int addedChildren){
        while (c != parent[c]) {
            subtreeSizes[parent[c]] += addedChildren;
            c = parent[c];
        }
    }

    // 1. java.util.BitSet을 이용한 풀이
    private static int getCommonAncestor1(int A, int B){
        BitSet bs = new BitSet();

        while (true) {
            if (bs.get(A)) return A;
            bs.set(A);
            if (bs.get(B)) return B;
            bs.set(B);
            A = parent[A];
            B = parent[B];
        }
    }

    // 2. bit 연산을 이용한 풀이
    private static int getCommonAncestor2(int V, int A, int B){
        long[] bits = new long[V / 64 + 1];

        while (true) {
            if ((bits[A / 64] & (1L << A % 64)) > 0) return A;
            bits[A / 64] ^= (1L << A % 64);
            if ((bits[B / 64] & (1L << B % 64)) > 0) return B;
            bits[B / 64] ^= (1L << B % 64);
            A = parent[A];
            B = parent[B];
        }
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

            int res = getCommonAncestor1(A, B);
//            int res = getCommonAncestor2(V, A, B);
            sb.append('#').append(tc).append(' ').append(res).append(' ').append(subtreeSizes[res]).append('\n');
        }
        System.out.println(sb);
    }

}
