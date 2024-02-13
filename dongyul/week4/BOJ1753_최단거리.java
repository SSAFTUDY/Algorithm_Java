import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1753 {

    static class Edge implements Comparable {

        int e;
        int cost;

        public Edge(int e, int cost) {
            this.e = e;
            this.cost = cost;
        }

        @Override
        public int compareTo(Object o) {
            Edge e = (Edge) o;
            return this.cost - e.cost;
        }
    }

    static int V, E, K;
    static List<Edge>[] edges;
    static int[] dist;

    static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        dist[K] = 0;
        pq.add(new Edge(K,0));
        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            if(curr.cost> dist[curr.e]){
                continue;
            }
            for (Edge edge : edges[curr.e]) {
                if(dist[edge.e] > curr.cost+ edge.cost){
                    dist[edge.e] = curr.cost+ edge.cost;
                    pq.add(new Edge(edge.e, curr.cost+ edge.cost));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int INF = 1_000_000;
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        edges = new List[V + 1];
        for (int i = 0; i <= V; i++) {
            edges[i] = new ArrayList<>();
        }
        dist = new int[V + 1];
        Arrays.fill(dist, INF);
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges[s].add(new Edge(e, c));
        }
        dijkstra();
        for (int i = 1; i <= V; i++) {
            if (dist[i] == INF) {
                sb.append("INF").append("\n");
            } else {
                sb.append(dist[i]).append("\n");
            }
        }
        System.out.println(sb);

    }

}
