import java.io.*;
import java.util.*;

public class BOJ_1715 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int result = 0;
		
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		
		for(int i=0; i<N;i++) {
			queue.add(Integer.parseInt(br.readLine()));
		}
		
		while(queue.size()>=2) {
			int a = queue.poll();
			int b = queue.poll();
			result += a+b;
			queue.add(a+b);
		}
	
		System.out.println(result);
	}
}

