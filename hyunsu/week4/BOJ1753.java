import java.util.*;
import java.io.*;

public class BOJ1753 {

    static int V, E, K;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static List<Edge>[] adj;
    static int[] dist;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine()) - 1;
        dist = new int[V];
        check = new boolean[V];

        adj = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            adj[u].add(new Edge(v, w));
        }

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[K] = 0; // 시작점은 0

        pq.offer(new Edge(K, 0));
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (check[cur.v]) {
                continue;
            }

            for (Edge nxt : adj[cur.v]) {
                if (!check[nxt.v] && dist[nxt.v] > dist[cur.v] + nxt.weight) {
                    dist[nxt.v] = dist[cur.v] + nxt.weight;
                    pq.offer(new Edge(nxt.v, dist[nxt.v]));
                }
            }

            check[cur.v] = true;
        }

        for (int i = 0; i < V; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(dist[i]);
            }
        }
    }

    static class Edge implements Comparable<Edge> {

        int v, weight;

        public Edge(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}
