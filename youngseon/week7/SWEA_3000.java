
import java.io.*;
import java.util.*;

public class SWEA_3000 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			PriorityQueue<Integer> lQueue = new PriorityQueue<>(Collections.reverseOrder());
			PriorityQueue<Integer> rQueue = new PriorityQueue<>();
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());

			lQueue.add(A);
			long sum = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());

				if (n1 > lQueue.peek() && n2 > lQueue.peek()) {  // 두 수가 중간보다 큰 경우
					rQueue.add(n1);
					rQueue.add(n2);
					lQueue.add(rQueue.poll());
				}
				
				else if (n1 < lQueue.peek() && n2 < lQueue.peek()) {  // 두 수가 중간보다 작은 경우
					lQueue.add(n1);
					lQueue.add(n2);
					rQueue.add(lQueue.poll());
				}
				
				else {
					rQueue.add(Math.max(n1, n2));
					lQueue.add(Math.min(n1, n2));
				}

				sum += (lQueue.peek()) % 20171109;
			
			}
			sb.append("#").append(t).append(" ").append(sum % 20171109).append("\n");
		}
		System.out.println(sb);

	}
}
