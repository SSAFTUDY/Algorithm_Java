import java.io.*;
import java.util.*;

class Main {
	static int n, init, end, com_size;
	static PriorityQueue<int[]> pq, pq_end;
	static PriorityQueue<Integer> pq_empty;
	static int[] cnt = new int[100_000];
	
	public static void check_end(int time) { 
		int[] element;
		while (pq_end.peek() != null && pq_end.peek()[0] <= time) {
//			System.out.println(pq_end.peek()[0]);
			element = pq_end.poll();
			pq_empty.add(element[1]);
		}
	}
	
	public static void main(String args[]) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        pq = new PriorityQueue<>(new Comparator<int[]>() {
        	@Override
        	public int compare(int[] o1, int[] o2) {
        		// 만일 2차원 배열의 첫 번째 원소가 같다면, 2번째 원소를 기준으로 오름차순 정렬한다.
        		if(o1[0] == o2[0]) {
        			return Integer.compare(o1[1], o2[1]);
        		}
        		// 2차원 배열의 첫 번째 원소를 기준으로 오름차순 정렬한다.
        		return Integer.compare(o1[0], o2[0]);
        	}
        });
        pq_end = new PriorityQueue<>(new Comparator<int[]>() {
        	@Override
        	public int compare(int[] o1, int[] o2) {
        		// 만일 2차원 배열의 첫 번째 원소가 같다면, 2번째 원소를 기준으로 오름차순 정렬한다.
        		if(o1[0] == o2[0]) {
        			return Integer.compare(o1[1], o2[1]);
        		}
        		// 2차원 배열의 첫 번째 원소를 기준으로 오름차순 정렬한다.
        		return Integer.compare(o1[0], o2[0]);
        	}
        });
        pq_empty = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(bf.readLine());
        	init = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            pq.add(new int[] {init, end});
        }
        int[] element;
        while (!pq.isEmpty()) {
        	element = pq.poll();
//        	System.out.println(element[0]);
        	check_end(element[0]);
        	if (!pq_empty.isEmpty()) {
        		int com_num = pq_empty.poll();
        		cnt[com_num] += 1;
        		pq_end.add(new int[] {element[1], com_num});
        	}
        	else {
        		com_size += 1;
        		cnt[com_size] += 1;
        		pq_end.add(new int[] {element[1], com_size});
        	}
        }
        sb.append(com_size).append("\n");
        for (int i = 1; i < com_size+1; i++) {
			sb.append(cnt[i]).append(" ");
		}
        System.out.println(sb);
	}
}