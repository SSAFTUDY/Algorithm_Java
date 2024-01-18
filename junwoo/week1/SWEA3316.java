import java.util.*;
import java.lang.*;
import java.io.*;


public class Main {
    static int n;
    static int size;
    static int[] arr;
    
    public static void simulate(char ch) {
    	int[] new_arr = new int[16];
    	byte now = 0;
    
    	switch (ch){ 
    	case 'A':	now = 1; break;
    	case 'B':	now = 2; break;
    	case 'C':	now = 4; break;
    	case 'D':	now = 8; break;
    	}
    	
    	for (int i = 1; i < 16; i++) {
    		if ((i & now) > 0) {
    			for (int bf = 1; bf < 16; bf++) {
    				if (((i & now) > 0) && ((bf & i) > 0)) {
//    					System.out.println(arr[bf]);
    					new_arr[i] += arr[bf];
    					new_arr[i] = new_arr[i] % 1000000007;
            		}
        		}
    		}
    	}
    	arr = new_arr;
    }
    public static void main(String[] args) throws IOException{
    	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(bf.readLine());
    	for (int i = 0; i < n; i++) {
    		arr = new int[16];
    		arr[1] = 1;
    		StringBuilder st = new StringBuilder();
    		st.append(bf.readLine());
    		size = st.length();
    		for (int j = 0; j < size; j++) {
        		simulate(st.charAt(j));
//        		System.out.println(Arrays.toString(arr));
			}
    		int sum = 0;
    		for (int j = 1; j < 16; j++) {
    			sum += arr[j];
    			sum %= 1000000007;
    		}
    		System.out.printf("#%d %d\n", i+1, sum);
		}
    }
}




