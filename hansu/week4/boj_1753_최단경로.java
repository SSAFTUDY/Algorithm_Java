import java.io.*;
import java.util.*;

/**
 * 메모리: 128,916kb
 * 시간 : 968ms
 */
class Solution {

    private static final int INF = 1_000_000;
    private static List<Edge>[] adj;
    private static int[] dist;

    private static class Vertex implements Comparable<Vertex>{
        int v, dist;

        Vertex(int v, int dist){
            this.v = v;
            this.dist = dist;
        }

        @Override
        public int compareTo(Vertex v){
            return this.dist - v.dist;
        }
    }

    private static class Edge{
        int to, cost;

        Edge(int to, int cost){
            this.to = to;
            this.cost = cost;
        }
    }

    private static void dijkstra(int V, int K){
        Queue<Vertex> pq = new PriorityQueue<>();
        BitSet check = new BitSet();

        dist[K] = 0;
        pq.add(new Vertex(K, 0));
        for (int i = 0; !pq.isEmpty() && i < V; i++) {
            //이미 선택된 노드는 패스
            while (check.get(pq.element().v)){
                pq.remove();
                if (pq.isEmpty()){
                    return;
                }
            }

            //선택 안된 노드중에서 가장 가까운 노드 선택
            Vertex minV = pq.remove();
            check.set(minV.v);
            dist[minV.v] = minV.dist;

            //선택된 노드의 주변 노드 삽입
            for (Edge edge : adj[minV.v]) {
                if (!check.get(edge.to)) {
                    pq.add(new Vertex(edge.to, dist[minV.v] + edge.cost));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken()),
            E = Integer.parseInt(st.nextToken()),
            K = Integer.parseInt(br.readLine());

        //init vertices
        dist = new int[V + 1];
        Arrays.fill(dist, INF);

        //init adj
        adj = new List[V + 1];
        for (int i = 1; i <= V; i++){
            adj[i] = new ArrayList<>();
        }

        //parse adj
        for (int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()),
                b = Integer.parseInt(st.nextToken()),
                c = Integer.parseInt(st.nextToken());

            adj[a].add(new Edge(b, c));
        }

        //Dijkstra
        dijkstra(V, K);

        //print
        for (int i = 1; i <= V; i++){
            sb.append(dist[i] < INF ? dist[i] : "INF").append('\n');
        }
        System.out.println(sb);
    }

}
