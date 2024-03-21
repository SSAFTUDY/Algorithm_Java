import java.io.*;
import java.util.*;

import org.omg.PortableInterceptor.INACTIVE;


class Main {
	static int N, atk;
	static int[][] arr;
	
	static boolean simulate(long hp) {
		long tmp_atk = atk;
		long now_hp = hp;
		for (int i = 0; i < N; i++) {
			if (arr[i][0] == 1) {
				long quo = (arr[i][2]-1) / tmp_atk;
				now_hp -= (quo * (long) arr[i][1]);
			} else {
				tmp_atk += arr[i][1];
				now_hp += arr[i][2];
				if (now_hp > hp) {
					now_hp = hp;
				}
			}
			if (now_hp <= 0) {
				return false;
			}
		}
		return true;
	}
	
	static long bin_search(long start, long end) {
		long mid = -1;
		while (start <= end) {
			mid = (start + end) / 2;
			if (!simulate(mid)) {
				start = mid+1;
			} else { 
				end = mid-1;
			}
		}
		if (!simulate(mid)) return mid+1;
		return mid;
	}
	
	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
//		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		atk = Integer.parseInt(st.nextToken());
		arr = new int[N][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		long ans = bin_search(1L, 9_110_000_000_000_000_001L);
		System.out.println(ans);
	}
}