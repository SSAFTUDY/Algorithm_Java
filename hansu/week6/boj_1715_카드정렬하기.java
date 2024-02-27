/**
 * 메모리: 24896kb
 * 시간: 364ms
 */
import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int N = Integer.parseInt(br.readLine()), sum = 0;

        for (int i = 0; i < N; i++) {
        	pq.add(Integer.parseInt(br.readLine()));
        }

        while(pq.size() > 1) {
        	int tmp = pq.remove() + pq.remove();
        	
        	sum += tmp;
        	pq.add(tmp);
        }
        
        System.out.println(sum);
    }

}
