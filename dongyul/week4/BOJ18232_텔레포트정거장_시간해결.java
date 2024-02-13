import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ18232 {

    static class Edge {

        int e;
        int cost;

        public Edge(int e, int cost) {
            this.e = e;
            this.cost = cost;
        }
    }

    static int N, S, E;
    static List<Integer>[] edge;
    static int[] visited;

    static void BFS(int start) {
        Deque<Edge> deque = new ArrayDeque<>();
        deque.add(new Edge(start, 0));

        while (!deque.isEmpty()) {
            Edge curr = deque.removeFirst();
            int s = curr.e;
            int cost = curr.cost;
            if(s == E){
                break;
            }
            for (Integer i : edge[s]) {
                if (visited[i] == 0 && i!=start) {
                    visited[i] = cost+1;
                    deque.add(new Edge(i, cost + 1));
                }
            }
            if (s > 1 && visited[s - 1] == 0 && s-1!=start) {
                visited[s-1] = cost+1;
                deque.add(new Edge(s - 1, cost + 1));
            }
            if (s < N && visited[s + 1] == 0&& s+1!=start) {
                visited[s+1] = cost+1;
                deque.add(new Edge(s + 1, cost + 1));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        visited = new int[N + 1];
        edge = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            edge[i] = new ArrayList();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            edge[s].add(e);
            edge[e].add(s);
        }
        BFS(S);
        System.out.println(visited[E]);
    }

}
