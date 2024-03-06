import java.io.*;
import java.util.*;


class Solution {
	static int T;
	static int[] heap;
	static int tail;
	
	static int delete() {
		if (heap[1] == 0) {
			return -1;
		}
		int num = heap[1];
		heap[1] = heap[tail-1];
		heap[tail-1] = 0;
		int cur = 1;
		while(heap[cur*2] > 0 && (heap[cur*2] > heap[cur] || heap[cur*2+1] > heap[cur])) {
			if (heap[cur*2] > heap[cur*2+1]) {
				int tmp = heap[cur];
				heap[cur] = heap[cur*2];
				heap[cur*2] = tmp;
				cur *= 2;
			}
			else {
				int tmp = heap[cur];
				heap[cur] = heap[cur*2+1];
				heap[cur*2+1] = tmp;
				cur *= 2;
				cur += 1;
			}
		}
		tail--;
		return num;
	}
	
	static void insert(int num) {
		heap[tail] = num;
		int cur = tail;
		while (cur != 1 && heap[cur] > heap[cur/2]) {
			int tmp = heap[cur];
			heap[cur] = heap[cur/2];
			heap[cur/2] = tmp;
			cur /= 2;
		}
		tail++;
	}
	
	public static void main(String args[]) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc < T+1; tc++) {
			int N = Integer.parseInt(br.readLine());
			heap = new int[2*N];
			tail = 1;
			sb.append("#").append(tc).append(" ");
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int q = Integer.parseInt(st.nextToken());
				if (q == 1) {
					int inserted_num = Integer.parseInt(st.nextToken());
					insert(inserted_num);
				}
				else {
					int num = delete();
					sb.append(num).append(" ");
				}
			}
			if(tc == T) break;
			sb.append("\n");
		}
		System.out.println(sb);
	}
}