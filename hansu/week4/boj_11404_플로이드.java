import java.io.*;
import java.util.*;

class Solution {

    private static final int INF = 100_000_000;

    private static void floyd(int[][] adj){
        int N = adj.length;

        for (int k = 0; k < N; k++)
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine()), M = Integer.parseInt(br.readLine());
        int[][] adj = new int[N][N];

        //initializing
        for (int i = 0; i < N; i++){
            Arrays.fill(adj[i], INF);
            adj[i][i] = 0;
        }

        //parsing
        for (int i = 0; i < M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()),
                b = Integer.parseInt(st.nextToken()),
                c = Integer.parseInt(st.nextToken());
            adj[a - 1][b - 1] = Math.min(adj[a - 1][b - 1], c);
        }

        floyd(adj);

        //print
        for (int[] row : adj){
            for (int e : row){
                sb.append(e == INF ? 0 : e).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

}
