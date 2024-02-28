import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1715 {

    /**
     * pq에서 빼서 더하면서 넣고 빼기 반복
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        int answer = 0;
        while (!pq.isEmpty()) {
            if(pq.size() == 1){
                break;
            }
            int curr = pq.poll();
            int curr2 = pq.poll();
            answer += (curr+curr2);
            pq.add(curr+curr2);
        }
        System.out.println(answer);
    }

}
