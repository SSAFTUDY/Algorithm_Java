

import java.io.*;
import java.util.*;

public class BOJ_23088 {

	static class Process implements Comparable<Process> {

		int num; // 부여된 번호
		int rTime; // 요청 시간
		int priority; // 우선순위 값
		int implTime; // 실행 시간

		public Process(int num, int rTime, int priority, int implTime) {
			this.num = num;
			this.rTime = rTime;
			this.priority = priority;
			this.implTime = implTime;
		}

		@Override
		public int compareTo(Process o2) {
			if (this.priority == o2.priority) { // 우선 순위가 같은 경우
				if (this.implTime == o2.implTime) { // 실행 시간이 같은 경우
					return this.num - o2.num; // 부여된 번호가 작은 것 부터
				} else { // 실행 시간이 다른 경우
					return this.implTime - o2.implTime;
				}
			} else { // 우선 순위가 다른 경우
				return o2.priority - this.priority;
			}
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		ArrayList<Process> arr = new ArrayList<>();

		int N = Integer.parseInt(br.readLine());
		int time[][] = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			time[i][0] = i; // 0에는 인덱스
			time[i][1] = t; // 1에는 요청 시간
			Process process = new Process(i + 1, t, p, b);
			arr.add(process);
		}

		int nowTime = time[0][1]; // 현재 시간 계산
		int idx = 0; // first 초기화 여부

		PriorityQueue<Process> queue = new PriorityQueue<Process>();
		while (idx < N || !queue.isEmpty()) {

			while (idx < N && nowTime >= time[idx][1]) { // 현재 시간보다 요청시간이 작은 프로세스 모두 넣기
				queue.add(arr.get(time[idx][0]));
				idx++;
			}
			if (!queue.isEmpty()) { // 큐가 비어있지 않다면
				Process processOut = queue.poll();
				nowTime += processOut.implTime;
				sb.append(processOut.num).append(" ");

				for (Process pro : queue) { // 나머지 우선순위 갱신
					pro.priority += processOut.implTime;
				}

				for (int nextIdx = idx; nextIdx < N; nextIdx++) {
					if (arr.get(nextIdx).rTime < nowTime) {
						arr.get(nextIdx).priority += nowTime - arr.get(nextIdx).rTime;
					} else {
						break;
					}
				}
			} else {  
				if (idx < N) {
					nowTime = time[idx][1]; // 현재 시간 계산
				}
			}

		}
		System.out.println(sb);
	}
}
