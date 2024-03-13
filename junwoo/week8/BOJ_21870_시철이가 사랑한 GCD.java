import java.io.*;
import java.util.*;

import org.omg.PortableInterceptor.INACTIVE;


class Main {
	static int N, maxi;
	static int[] arr;
	
	static int gcd(int a, int b) {
		int lg = a;
		int sm = b;
		if (a == b) {
			return a;
		}
		else if (sm > lg) {
			sm ^= lg ^= sm;
			lg ^= sm;
		}
		while (lg % sm != 0) {
			lg %= sm;
			if (sm > lg) {
				int tmp = lg;
				lg = sm;
				sm = tmp;
			}
		}
		return sm;
	}
	
	static int cal2(int start, int end) {
		int num = arr[start];
		for (int i = start+1; i < end; i++) {
			num = gcd(num, arr[i]);
		}
		return num;
	}
	
	static void cal(int start, int end, int ttl) {
//		System.out.println(start + " " + end + " " + ttl);
		if (end - start == 1) {
			ttl+=arr[start];
			if (ttl > maxi) {
				maxi = ttl;
			}
			return;
		}
		cal((start + end)/2, end, ttl + cal2(start, (start + end)/2));
		cal(start, (start + end)/2, ttl + cal2((start + end)/2, end));
	}
	
	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
//		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N]; maxi = 0;
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		cal(0, N, 0);
		System.out.println(maxi);
		
		
	}
}