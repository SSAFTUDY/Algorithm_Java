import java.io.*;
import java.util.*;


class Main {
	static int N;
	static Process[] processes;
	static PriorityQueue<Process> pq;
	static class Process {
		int process_num;
		int request_time;
		int excution_time;
		int priority;
		
		Process(int process_num, int request_time, int priority, int excution_time) {
			this.process_num = process_num;
			this.request_time = request_time;
			this.excution_time = excution_time;
			this.priority = priority;
		}
	}
	
	public static void main(String args[]) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		processes = new Process[N+1];
		pq = new PriorityQueue<>(new Comparator<Process>() {
			@Override
			public int compare(Process o1, Process o2) {
				if ((o2.priority - o2.request_time) == (o1.priority - o1.request_time)) {
					if (o1.excution_time == o2.excution_time) {
						if (o1.process_num == o2.process_num) {
							return 0;
						}
						else {
							return o1.process_num - o2.process_num;
						}
					}
					else { 
						return o1.excution_time - o2.excution_time;
					}
				}
				else { 
					return (o2.priority - o2.request_time) - (o1.priority - o1.request_time);
				}
			}
		});
		for (int i = 1; i < N+1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			processes[i] = new Process(i, t, p, b);
		}
		int now_time = 0;
		int p_idx = 1;
		while (p_idx < N+1) {
			if (pq.isEmpty() && now_time < processes[p_idx].request_time) {
				now_time = processes[p_idx].request_time;
			}
			while (p_idx < N+1 && now_time >= processes[p_idx].request_time) {
				pq.add(processes[p_idx]);
				p_idx++;
			}
			Process working = pq.poll();
			sb.append(working.process_num).append(" ");
			now_time = working.excution_time + now_time;
		}
		while(!pq.isEmpty()) {
			Process working = pq.poll();
			sb.append(working.process_num).append(" ");
		}
		System.out.println(sb);
	}
}