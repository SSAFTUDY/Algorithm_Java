import java.util.*;
import java.lang.*;
import java.io.*;


public class Main {
    static int ori_value;
    static int n;
    static boolean[] bo;
    static int bo_ttl = 0;
    
    public static void main(String[] args) throws IOException{
    	bo = new boolean[10];
    	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(bf.readLine());
    	for (int i = 0; i < n; i++) {
    		bo_ttl = 0;
    		bo = new boolean[10];
    		int cnt = 1;
    		ori_value = Integer.parseInt(bf.readLine());
    		int value = ori_value * cnt;
    		while (true) {
    			int tmp_value = value;
        		int val;
        		while (tmp_value > 0) {
        			val = tmp_value % 10;
        			if (!bo[val]) {
        				bo[val] = true;
        				bo_ttl += 1;
        			}
        			tmp_value = tmp_value / 10;
        		}
    			cnt += 1;
    			if (bo_ttl > 9) {
    				break;
    			}
    			value = ori_value * cnt;
    		}
    		System.out.printf("#%d %d\n", i+1, value);
    	}
    }
}



