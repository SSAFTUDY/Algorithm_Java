import java.util.*;
import java.io.*;

public class Main {
	static int L, R;
	static int[] Larr, Rarr;
	
	static int[] changeToArr(int num, int max_digits) {
		int[] arr = new int[max_digits];
		int digits = 1;
		while (num > 0) {
			arr[digits-1] = num % 10;
			num /= 10;
			digits++;
		}
		return arr;
	}
	
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("res/input.txt"));
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	L = Integer.parseInt(st.nextToken());
    	R = Integer.parseInt(st.nextToken());
    	int digits = 1;
    	int ten_mul_digits_times = 10;
    	while (R / ten_mul_digits_times > 0) {
    		digits++;
    		ten_mul_digits_times *= 10;
    	}
    	Larr = changeToArr(L, digits);
    	Rarr = changeToArr(R, digits);
    	int ans = 0;
    	for (int i = digits-1; i > -1; i--) {
			if (Larr[i] != Rarr[i]) {
				break;
			} else if (Larr[i] == 8) {
				ans++;
			}
		}
    	for (int i = 0; i < digits; i++) {
			System.out.print(Larr[i] + " ");
		}System.out.println();
		
		for (int i = 0; i < digits; i++) {
			System.out.print(Rarr[i] + " ");
		}System.out.println();
		
    	System.out.println();
    	System.out.println(ans);
    }
}