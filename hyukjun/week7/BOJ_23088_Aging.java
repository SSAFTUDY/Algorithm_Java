package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_23088 {

	static class Process implements Comparable<Process> {
		int index;
		int startTime;
		int priority;
		int runTime;

		public Process(int index, int startTime, int priority, int runTime) {
			super();
			this.index = index;
			this.startTime = startTime;
			this.priority = priority;
			this.runTime = runTime;
		}

		@Override
		public int compareTo(Process o) {
			if(this.priority == o.priority) {
				if(this.runTime == o.runTime) {
					return this.index - o.index;
				}
				return this.runTime - o.runTime;
			}
			return o.priority - this.priority;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		// get input
		int N = Integer.parseInt(br.readLine());
		Process[] processes = new Process[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int startTime = Integer.parseInt(st.nextToken());
			int priority = Integer.parseInt(st.nextToken());
			int runTime = Integer.parseInt(st.nextToken());
			processes[i] = new Process(i + 1, startTime, priority, runTime);
		}
		
		// process
//		현재 실행되고 있는 프로세스가 없다면, 지금까지 실행 요청된 프로세스 중 우선순위가 가장 높은 프로세스가 실행된다.
//		우선순위가 같은 프로세스가 여러 개라면 실행 시간이 짧은 프로세스가 먼저 실행된다.
//		우선순위가 같고 실행 시간이 같은 프로세스가 여러 개라면 부여된 번호가 작은 프로세스가 먼저 실행된다.
//		실행 요청되었지만 실행되지 않은 프로세스는 단위 시간당 우선순위가 1씩 증가한다.
//		한 프로세스가 실행 중인 동안에는 다른 프로세스가 요청되어도 실행을 중단하지 않는다.
		
		PriorityQueue<Process> processQueue = new PriorityQueue<>();
		int currentTime = 0;
		int currentIdx = 0;
		for (int time = 0; time <= 300_000; time++) {
			if(currentTime == 0) {
				if(!processQueue.isEmpty()) {
					Process current = processQueue.poll();
					currentTime = current.runTime;
					sb.append(current.index).append(' ');
				}
			}
			
			while(currentIdx < processes.length && processes[currentIdx].startTime == time) {
				processQueue.add(processes[currentIdx]);
				currentIdx++;
			}
			
			
			if(currentTime != 0) {
				currentTime--;
			}
			
			if(!processQueue.isEmpty()) {
				Process[] tmp = new Process[processQueue.size()];
				for (int i = 0; i < tmp.length; i++) {
					Process cur = processQueue.poll();
					cur.priority++;
					tmp[i] = cur;
				}
				
				processQueue.addAll(Arrays.asList(tmp));
			}
		}
		
		// output
		System.out.println(sb);
	}

}
