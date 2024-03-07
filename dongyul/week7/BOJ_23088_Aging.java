import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ23088 {

    private static class Node implements Comparable<Node> {

        int t;  //실행 요청된 시점
        int p;  //초기 우선순위
        int b;  //프로세스 실행시간
        int idx;    //프로세스 인덱스

        public Node(int t, int p, int b, int idx) {
            this.t = t;
            this.p = p;
            this.b = b;
            this.idx = idx;
        }

        @Override
        public String toString() {
            return "Node{" +
                "t=" + t +
                ", p=" + p +
                ", b=" + b +
                ", idx=" + idx +
                '}';
        }

        @Override
        public int compareTo(Node o) {
            if (this.t == o.t) {
                if (this.p == o.p) {
                    if (this.b == o.b) {
                        return this.idx - o.idx;
                    } else {
                        return this.b - o.b;
                    }
                } else {
                    return o.p - this.p;    //내림차순
                }
            } else {
                return this.t - o.t;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int idx = 1;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Node[] nodes = new Node[N + 1];

        //input t의 오름차순으로 주어짐!!!
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            Node node = new Node(t, p, b, idx);
            nodes[idx++] = node;
        }
        int totalTime = 0;
        int i = 2;
        pq.add(nodes[1]);
        List<Integer> result = new ArrayList<>();

        while (i <= N) {
            if (!pq.isEmpty()) {
                Node curr = pq.poll();
                result.add(curr.idx);
                totalTime += curr.b;
            }
            if (totalTime < nodes[i].t) {
                totalTime = nodes[i].t;
            } else {
                while (i<=N && totalTime >= nodes[i].t) {
                    Node node = new Node(0, nodes[i].p + (totalTime - nodes[i].t),
                        nodes[i].b, nodes[i].idx);
                    pq.add(node);
                    i++;
                }
            }
        }
        while (!pq.isEmpty()){
            Node curr = pq.poll();
            result.add(curr.idx);
        }
        for (Integer integer : result) {
            sb.append(integer).append(" ");
        }
        System.out.println(sb);
    }

}
