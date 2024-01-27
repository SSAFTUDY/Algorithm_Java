import java.util.*;
import java.io.*;

public class Main {
    static int[] parent, l_child, r_child, size;
    static boolean[] isAncestor;
    
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        StringTokenizer st;
        for (int tc = 1; tc < T+1; tc++) {
            st = new StringTokenizer(bf.readLine());
            int v, e, num1, num2, p, c, cur, added_size;
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            num1 = Integer.parseInt(st.nextToken());
            num2 = Integer.parseInt(st.nextToken());
            parent = new int[v+1]; l_child = new int[v+1]; r_child = new int[v+1]; size = new int[v+1];
            isAncestor = new boolean[v+1];
            for (int i = 1; i < v+1; i++) {
                size[i] = 1;
            }
            st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < e; i++) {
                p = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken());
                parent[c] = p;
                if (l_child[p] > 0) {
                    r_child[p] = c;
                }
                else {
                    l_child[p] = c;
                }
                cur = p;
                added_size = size[c];
                while (cur != 0) {
                    size[cur] += added_size;
                    cur = parent[cur];
                }
            }
            
            cur = num1;
            while (cur != 0) {
                isAncestor[cur] = true;
                cur = parent[cur];
            }
            
            cur = num2;
            while (!isAncestor[cur]) {
                cur = parent[cur];
            }
            // System.out.println(Arrays.toString(size));
            System.out.printf("#%d %d %d\n",tc,cur,size[cur]);
        }
    }
}