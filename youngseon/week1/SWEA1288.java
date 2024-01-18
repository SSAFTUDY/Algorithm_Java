import java.io.*;
import java.util.*;

public class Solution {
	
	private static boolean containsFalse(boolean[] array) {
	    for (boolean value : array) {
	        if (!value) {
	            return true;
	        }
	    }
	    return false;
	}
	
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());	
		
		int N;
		for (int i = 0; i < T; i++) {
			int m = 1;     // 곱하는 수   
		
			N = Integer.parseInt(br.readLine());
			boolean[] check = new boolean[10];

		
//			while (Arrays.asList(check).contains(false)) {	  // 이거 왜 안됨??
//			while (Arrays.asList(check).stream().filter(c -> c == true).count()<10) {   // 얘는 또 왜 안됨??
			
			while(containsFalse(check)) {
				boolean[] now_check = new boolean[10];
				char[] arr = String.valueOf(N*m).toCharArray(); 
				for (char ch : arr) {
					int idx = ch - '0';   
					now_check[idx] = true;
				}
				for(int j=0; j<10; j++) {
					check[j] = now_check[j] | check[j];     // 비트 연산(or)으로 체크
				}
				
				
				m++;
			}
			System.out.println("#" + (i+1) + " "+ (N*(m-1)));
		}
	}
}
