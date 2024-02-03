import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 그룹 찾고 그룹에서 가장 비용 적은 사람과 친구하기
 */

public class Main {

    static class Node {

        List<Node> children;
        int index;

        public Node(int index) {
            this.index = index;
            children = new ArrayList<>();
        }
    }

    static int N, M, K;
    static int[] cost;
    static Node[] nodes;
    static boolean visited[];

    //BFS 돌면서 최소비용 같이 찾아줌
    private static int BFS(int start) {
        Deque<Integer> deque = new ArrayDeque<>();
        int min = 10_000;
        deque.addLast(start);
        while (!deque.isEmpty()) {
            int curr = deque.removeFirst();
            if (!visited[curr]) {
                visited[curr] = true;
                min = Math.min(min, cost[curr]);
                for (Node node : nodes[curr].children) {
                    deque.addLast(node.index);
                }
            }
        }
        return min;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nodes = new Node[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new Node(i);
        }

        cost = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            nodes[a].children.add(nodes[b]);
            nodes[b].children.add(nodes[a]);
        }

        int sum = 0;
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                sum += BFS(i);
            }
        }
        if (K >= sum) {
            System.out.println(sum);
        } else {
            System.out.println("Oh no");
        }
    }

}
