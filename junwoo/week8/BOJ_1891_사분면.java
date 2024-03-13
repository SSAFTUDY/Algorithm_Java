import java.io.*;
import java.util.*;
							

class Main {
	static int d;
	static long x, y, locx, locy;
	static StringBuilder str, new_str;
	static String[][] arr;
	
	
	static void dfs(long xs, long xe, long ys, long ye, int depth) {
		if (depth == d) {
			locx = xs;
			locy = ys;
			return;
		}
		if (str.charAt(depth) == '1') {
			dfs(xs, xe - (long)Math.pow(2, d-depth-1), ys + (long)Math.pow(2, d-depth-1), ye, depth+1);
		}
		else if (str.charAt(depth) == '2') {
			dfs(xs, xe - (long)Math.pow(2, d-depth-1), ys, ye - (long)Math.pow(2, d-depth-1), depth+1);
		}
		else if (str.charAt(depth) == '3') {
			dfs(xs + (long)Math.pow(2, d-depth-1), xe, ys, ye - (long)Math.pow(2, d-depth-1), depth+1);
		}
		else if (str.charAt(depth) == '4') {
			dfs(xs + (long)Math.pow(2, d-depth-1), xe, ys + (long)Math.pow(2, d-depth-1), ye, depth+1);
		}
	}
	
	static void sfd(long x, long y, int depth) {
		if(depth == d) {
			return;
		}
		if (x < (long)Math.pow(2, d-depth-1) && y >= (long)Math.pow(2, d-depth-1)) {
			new_str.append('1');
//			System.out.println(new_str);
			y -= (long)Math.pow(2, d-depth-1);
			sfd(x, y, depth+1);
		}
		else if (x < (long)Math.pow(2, d-depth-1) && y < (long)Math.pow(2, d-depth-1)) {
			new_str.append('2');
//			System.out.println("x = " + x + " " + (long)Math.pow(2, d-depth-1));
//			System.out.println("y = " + y + " " + (long)Math.pow(2, d-depth-1));
//			System.out.println(new_str);
			

			sfd(x, y, depth+1);
		}
		else if (x >= (long)Math.pow(2, d-depth-1) && y < (long)Math.pow(2, d-depth-1)) {
			new_str.append('3');
//			System.out.println(new_str);

			x -= (long)Math.pow(2, d-depth-1);
			sfd(x, y, depth+1);
		}
		else if (x >= (long)Math.pow(2, d-depth-1) && y >= (long)Math.pow(2, d-depth-1)) {
			new_str.append('4');
//			System.out.println(new_str);

			x -= (long)Math.pow(2, d-depth-1);
			y -= (long)Math.pow(2, d-depth-1);
			sfd(x, y, depth+1);
		}
	}
	
	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("res/input2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		d = Integer.parseInt(st.nextToken());
		str = new StringBuilder(st.nextToken());
		new_str = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		x = Long.parseLong(st.nextToken());
		y = Long.parseLong(st.nextToken());
		dfs(0, (long)Math.pow(2, d), 0, (long)Math.pow(2, d), 0);
//		System.out.println(locx + " " + locy);

		locx -= y;
		locy += x;
		
//		System.out.println(locx + " " + locy);
//		for (int i = 0; i < (long)Math.pow(2, d); i++) {
//			for (int j = 0; j < (long)Math.pow(2, d); j++) {
//				new_str = new StringBuilder();
//
//				sfd(i, j, 0);
//				System.out.print(new_str + " ");
//			}System.out.println();
//		}
		
		if (locx < 0 || locx >= (long) Math.pow(2, d) || locy < 0 || locy >= (long) Math.pow(2, d)) {
			System.out.println(-1);
		} else {
			sfd(locx, locy, 0);
			System.out.println(new_str);
		}
	}
}