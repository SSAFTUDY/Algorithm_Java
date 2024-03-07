import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(st.nextToken()));
        }
        int[] children = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            children[i] = Integer.parseInt(st.nextToken());
        }
        int answer = 1;
        for (int i = 0; i < M; i++) {
            int curr = pq.poll();
            curr -= children[i];
            if(curr<0) {
                answer = 0;
                break;
            }
            else if(curr>0) {
                pq.add(curr);
            }
        }
        System.out.println(answer);
    }

}
