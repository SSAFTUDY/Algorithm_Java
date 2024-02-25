package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1105 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		// get input
		st = new StringTokenizer(br.readLine());
		String L = st.nextToken();
		String R = st.nextToken();
		
		// process
		if(L.length() != R.length()) {
			System.out.println(0);
		} else {
			int count = 0;
			for (int i = 0; i < L.length(); i++) {
				if(L.charAt(i) == R.charAt(i) && L.charAt(i) == '8') {
					count++;
				} else if (L.charAt(i) == R.charAt(i)) {
					continue;
				} else {
					break;
				}
			}
			System.out.println(count);
		}
	}
}
