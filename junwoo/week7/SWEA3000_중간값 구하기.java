import java.io.*;
import java.util.*;


class Solution {
	static int T;
	
	static PriorityQueue<Integer> maxpq;
	static PriorityQueue<Integer> minpq;
	
	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc < T+1; tc++) {
			int ttl = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int mid = Integer.parseInt(st.nextToken());
			maxpq = new PriorityQueue<>(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o2-o1;
				}
			});
			minpq = new PriorityQueue<>(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o1-o2;
				}
			});
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (a > mid) {
					minpq.add(a);
					a = minpq.poll();
				}
				else {
					maxpq.add(a);
					a = maxpq.poll();
				}
				if (b > mid) {
					minpq.add(b);
					b = minpq.poll();
				}
				else {
					maxpq.add(b);
					b = maxpq.poll();
				}
				if (mid > b) {
					int tmp = b;
					b = mid;
					mid = tmp;
				}
				if (a > mid) {
					int tmp = a;
					a = mid;
					mid = tmp;
				}
				maxpq.add(a);
				if (mid > b) {
					int tmp = b;
					b = mid;
					mid = tmp;
				}
				minpq.add(b);
				System.out.println(mid);
				ttl += mid;
				ttl %= 20171109;
			}
			sb.append("#").append(tc).append(" ").append(ttl).append("\n");
		}
		System.out.println(sb);
	}
}