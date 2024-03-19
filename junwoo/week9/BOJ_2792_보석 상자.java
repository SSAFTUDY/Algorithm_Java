import java.io.*;
import java.util.*;

import org.omg.PortableInterceptor.INACTIVE;


class Main {
	static int N, M;
	static int[] arr;
	static int howmanychildren(int num) {
		int ttl = 0;
		for (int i = 0; i < M; i++) {
			ttl	+= (arr[i] - 1) / num;
			ttl++;
		}
		return ttl;
	}
	static int bin_search(int start, int end) {
		int mid = -1;
//		int cnt = 0;
		while (start <= end) {
//			System.out.println(start + " " + end);
//			System.out.println(howmanychildren(start) + " " + howmanychildren(end) );
			
			mid = (start + end) / 2;
			if (howmanychildren(mid) > N) {
				start = mid+1;
			} else { 
				end = mid-1;
			}
//			cnt++;
//			System.out.println(mid);
//			if (cnt == 2) break;
//			if (end < 1000000000 ) break;
		}
		if (howmanychildren(mid) > N) return mid+1;
//		System.out.println(start + " " + end);
//		System.out.println(howmanychildren(start) + " "  +howmanychildren(mid));
		return mid;
	}
	
	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
//		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[M];
		for (int i = 0; i < M; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		int ans = bin_search(1, 1_000_000_000);
		System.out.println(ans);
	}
}