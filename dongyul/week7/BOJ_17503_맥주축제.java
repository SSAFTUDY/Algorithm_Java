import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ17503 {

    private static class Node implements Comparable<Node>{
        long x;  //선호도
        long y;  //간레벨

        public Node(long x, long y) {
            this.x = x;
            this.y = y;
        }

        //간 레벨 낮은거 부터, 선호도 높은거부터
        @Override
        public  int compareTo(Node o) {
            if(this.y == o.y){
                return Long.compare(o.x,this.x);
            }
            else {
                return Long.compare(this.y,o.y);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Long.parseLong(st.nextToken());   //축제 기간
        long M = Long.parseLong(st.nextToken());   //선호도의 합
        long K = Long.parseLong(st.nextToken());   //맥주 종류

        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (long i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            pq.add(new Node(x,y));
        }

        /**
         * pq에서 빼면서 투포인터
         */
        PriorityQueue<Long> pq2 = new PriorityQueue<>();

        long level = 0;
        long favor = 0;
        for (long i = 0; i < N; i++) {
            Node curr = pq.poll();
            level = curr.y;
            favor+=curr.x;
            pq2.add(curr.x);
        }
        if (favor >= M) {
            System.out.println(level);
            System.exit(0);
        }

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            favor+= curr.x;
            pq2.add(curr.x);
            long temp = pq2.poll();
            level = curr.y;
            favor-= temp;
            if (favor >= M) {
                System.out.println(level);
                System.exit(0);
            }
        }
        System.out.println(-1);
    }
}
