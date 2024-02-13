import java.io.*;
import java.util.*;

class Solution {

    private static int bfs(List<Integer>[] adj, int N, int S, int E){
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        int time = 0;

        q.add(S);
        visited[S] = true;
        while (!q.isEmpty()){
            int size = q.size();

            time++;
            for (int i = 0; i < size; i++) {
                int now = q.remove();

                if (now - 1 == E || now + 1 == E){
                    return time;
                }

                if (now > 1 && !visited[now - 1]) {
                    visited[now - 1] = true;
                    q.add(now - 1);
                }
                if (now < N - 1 && !visited[now + 1]) {
                    visited[now + 1] = true;
                    q.add(now + 1);
                }
                for (int v : adj[now]) {
                    if (v == E){
                        return time;
                    }
                    if (!visited[v]) {
                        visited[v] = true;
                        q.add(v);
                    }
                }
            }
        }
        return time;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken()), E = Integer.parseInt(st.nextToken());
        List<Integer>[] adj = new List[N + 1];

        //init adj
        for (int i = 1; i <= N; i++){
            adj[i] = new ArrayList<>();
        }

        //parse adj
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());

            adj[a].add(b);
            adj[b].add(a);
        }

        System.out.println(bfs(adj, N, S, E));
    }

}
