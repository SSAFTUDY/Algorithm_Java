import java.io.*;
import java.util.*;


class Main {
	static int T;
	static String str;
	
	public static boolean check(int start, int size) {
		for (int i = start; i < start + size; i++) {
			int a = (int) str.charAt(i)-48;
			int b = (int) str.charAt(start*2 + size*2 - i)-48;
			if (a + b != 1) {
				return false;
			}
		}
		if (size <= 1) return true;
		if (check(start, size/2) && check(start+size/2+1, size/2)) {
			return true;
		}
		else { 
			return false;
		}
	}
	
	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
//		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			str = br.readLine();
			if (str.length() == 1 || check(0, str.length()/2)) { 
				System.out.println("YES");
			} else { 
				System.out.println("NO");
			}
		}
	}
}