import java.io.*;
import java.util.*;

class Main {
	static int N, M;
	static int[] heap;
	static int 
	tail;
	
	static int delete() {
		if (heap[1] == 0) {
			return -1;
		}
//		System.out.println(heap[1] + " ? "+ heap[2] + " ? " + heap[3]);
		int num = heap[1];
//		System.out.println("num = " + num);
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
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		heap = new int[2_000_000];
		tail = 1;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int inserted_num = Integer.parseInt(st.nextToken());
			insert(inserted_num);
//			for (int j = 1; j < tail; j++) {
//				System.out.print(heap[j] + " ");
//			}System.out.println();
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int num = Integer.parseInt(st.nextToken());
			int box = delete();
//			for (int j = 1; j < tail; j++) {
//				System.out.print(heap[j] + " ");
//			}System.out.println();
//			System.out.println(box);
			if (box-num >= 0) {
				box -= num;
				insert(box);
			}
			else {
				System.out.println(0);
				System.exit(0);
			}
		}
		System.out.println(1);
	}
}