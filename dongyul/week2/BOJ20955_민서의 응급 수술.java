import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * cycle이 존재하지 않으면서 모두 이어졌을 때 간선의 개수 -> N-1개
 * 1. 서로 연결되어 있는 집단의 개수를 세줌. (BFS)
 * 2. 이 집단들을 모두 이어줌
 * 3. 이 때 간선이 N-1개 초과면 cycle 발생 -> N-1개로 줄여줌 (어떤 간선을 제거해야하는지는 알 필요 없음)
 * answer = 모두 이어주기 + cycle 없애기 = (집단 개수 -1) + (초과 간선 개수 - (N-1))
 */
public class Main {

    static int N;
    static int M;
    static List<Integer>[] graph;
    static int group = 0; //집단의 개수
    static int[] visited;

    static void BFS(int start) {
        group +=1;
        LinkedList<Integer> deque = new LinkedList();
        deque.add(start);
        visited[start] = 1;
        while (!deque.isEmpty()){
            int curr = deque.removeFirst();
            for(int next : graph[curr]){
                if(visited[next] == 0){
                    deque.addLast(next);
                }
            }
            visited[curr] = 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new List[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u-1].add(v-1);
            graph[v-1].add(u-1);
        }
        visited = new int[N];
        for (int i = 0; i < N; i++) {
            if (visited[i] == 0) {
                BFS(i);
            }
        }
        System.out.println(M + (group -1) - (N-1) + (group -1));
    }
}
