
import java.io.*;
import java.util.*;

public class BOJ_12764 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] result = new int[N];
		int[] decision = new int[N];
		int[][] info = new int[N][2];

		PriorityQueue<int[]> start = new PriorityQueue<>((o1, o2) -> {
			return o1[0] - o2[0];
		});

		PriorityQueue<int[]> now = new PriorityQueue<>((o1, o2) -> {
			return o1[0] - o2[0];
		});
		PriorityQueue<Integer> availableSeat = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int begin = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			start.add(new int[] { begin, i });
			info[i][0] = begin;
			info[i][1] = end;
			availableSeat.add(i);
		}

		for (int i = 0; i < N; i++) {
			int[] out = start.poll();
			int k = out[1]; // 시작 인덱스 추출
			int startTime = out[0];

			while (now.peek() != null) { // 시작 시간보다 작은 종료 값들 모두 추출
				int finish[] = now.peek();
				if (startTime > finish[0]) {
					now.poll();
					availableSeat.add(decision[finish[1]]); // 종료되면 가능 좌석에 추가
				}
				else {
					break;
				}
			}

			int seat = availableSeat.poll(); // 선택한 자리
			decision[k] = seat;

			result[seat]++;
			now.add(new int[] { info[k][1], k }); // 시작한 컴퓨터의 종료시간 추가
		}
		
		int cnt = 0;
		for (int i = 0; i < N; i++) {	
			if (result[i] != 0) {
				cnt++;
				sb.append(result[i]).append(" ");
			}
			else {
				break;
			}
		}
		System.out.println(cnt);
		System.out.println(sb);
	}
}
