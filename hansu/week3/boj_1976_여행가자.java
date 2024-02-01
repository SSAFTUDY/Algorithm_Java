import java.io.*;
import java.util.*;

class Solution {

    private static int[] parent;

    private static void union(int a, int b){
        int x = find(a), y = find(b);
        parent[Math.max(x, y)] = Math.min(x, y);
    }

    private static int find(int x){
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()), M = Integer.parseInt(br.readLine());

        //init parent
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++){
            parent[i] = i;
        }

        //union-find
        for (int i = 1; i <= N; i++){
            String line = br.readLine();
            for (int j = 1; j <= N; j++){
                if (line.charAt(j * 2 - 2) == '1'){
                    union(i, j);
                }
            }
        }

        //check
        StringTokenizer st = new StringTokenizer(br.readLine());
        int root = find(Integer.parseInt(st.nextToken()));
        for (int i = 0; i < M - 1; i++){
            if (find(Integer.parseInt(st.nextToken())) != root){
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

}
