import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// get input
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		List<Integer>[] teleport = new List[N + 1];
		int[] minTime = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			teleport[i] = new ArrayList<>();
			minTime[i] = Integer.MAX_VALUE;
		}

		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken()), E = Integer.parseInt(st.nextToken());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			teleport[from].add(to);
			teleport[to].add(from);
		}

		// process
		Queue<Integer> que = new ArrayDeque<>();
		que.add(S);
		minTime[S] = 0;
		while (!que.isEmpty()) {
			int cur = que.poll();
			if (cur == E)
				break;

			// 뒤로
			if (cur > 0 && minTime[cur - 1] > minTime[cur] + 1) {
				minTime[cur - 1] = minTime[cur] + 1;
				que.add(cur - 1);
			}

			// 앞으로
			if (cur < N && minTime[cur + 1] > minTime[cur] + 1) {
				minTime[cur + 1] = minTime[cur] + 1;
				que.add(cur + 1);
			}

			// 텔레포트
			for (Integer tp : teleport[cur]) {
				if (minTime[tp] > minTime[cur] + 1) {
					minTime[tp] = minTime[cur] + 1;
					que.add(tp);
				}
			}

		}

		// output
		System.out.println(minTime[E]);
	}

}
