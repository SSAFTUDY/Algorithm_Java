
import java.io.*;
import java.util.*;

public class SWEA_2930 {

	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
			
			sb.append("#").append(t).append(" ");
			int N = Integer.parseInt(br.readLine());
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int order = Integer.parseInt(st.nextToken());
				if (order == 1) {
					int num = Integer.parseInt(st.nextToken());
					queue.add(num);
				}
				if (order == 2) {
					if(!queue.isEmpty()) {
						sb.append(queue.poll()).append(" ");
					}
					else {
						sb.append(-1).append(" ");
					}
				}
			}
			sb.append("\n");

		}

		System.out.println(sb);
	}
}
