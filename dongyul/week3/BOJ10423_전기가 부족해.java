import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ10423 {

    /**
     * Kruskal을 진행
     * find 했을 때 조상이 발전소면 발전소와 연결 되어 있는 것!
     * 두 정점이 둘 다 발전소와 연결 되어 있으면 union 하면 안됨
     * 하나만 연결되어 있거나 둘 다 연결되어 있지 않고 싸이클을 발생시키지 않는다면 union 진행
     */

    static class Edge{
        int s;
        int e;
        int cost;

        public Edge(int s, int e, int cost) {
            this.s = s;
            this.e = e;
            this.cost = cost;
        }
    }

    static int N;
    static int M;
    static int K;
    static int[] parent;
    static boolean[] connected; //발전소인지 체크하는 배열, 발전소인 정점만 1

    static int find(int x){
        while(x!=parent[x]){
            x = parent[x];
        }
        return x;
    }

    static void union(int x, int y){
        //만약 하나만 발전소가 있으면 x는 발전소가 있는 정점
        //둘 다 없으면 순서 상관 없으므로 union
        int xP = find(x);
        int yP = find(y);
        parent[yP] = xP;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        parent = new int[N+1];
        for(int i = 0; i<N+1; i++){
            parent[i] = i;
        }

        connected = new boolean[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<K; i++){
            connected[Integer.parseInt(st.nextToken())] = true; //발전소만 값 1
        }

        List<Edge> edges = new ArrayList<>();
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges.add(new Edge(u,v,w));
        }
        Collections.sort(edges, Comparator.comparingInt(o->o.cost));    //kruskal을 위해 정렬

        int answer = 0;
        for (Edge edge : edges) {
            //둘 다 조상이 발전소일 때(이미 발전소와 연결 되어 있을 때)
            if(connected[find(edge.s)] && connected[find(edge.e)]){
                continue;
            }
            //s,e 에서 s는 발전소와 연결되어 있고 e는 연결되어 있지 않을 때
            else if(connected[find(edge.s)] && !connected[find(edge.e)]){
                union(edge.s, edge.e);
            }
            //s,e 에서 e는 발전소와 연결되어 있고 s는 연결되어 있지 않을 때
            else if(!connected[find(edge.s)] && connected[find(edge.e)]){
                union(edge.e, edge.s);  //e가 발전소와 연결되어 있으므로 첫번 째 파라미터로 주기.
            }
            //둘 다 발전소와 연결이 되어 있지 않을 때
            else{
                if(find(edge.s)!=find(edge.e)){ //싸이클 체크!
                    union(edge.s, edge.e);
                }
                else{
                    continue;
                }
            }
            answer+=edge.cost;  //union이 일어났다면 비용 더해준다.
        }
        System.out.println(answer);
    }
}
