// 40588kb,	444ms
import java.io.*;
import java.util.*;

public class BOJ_23757 {

	static int[] want;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			queue.add(Integer.parseInt(st.nextToken()));
		}

		want = new int[M + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			want[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= M; i++) {
			int out = queue.poll();
			if(out < want[i]) {
				System.out.println(0);
				return;
			}
			int remain = out - want[i];
			if(remain > 0) {
				queue.add(remain);
			}
		}
		System.out.println(1);
	}
}
