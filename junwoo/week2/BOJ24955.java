import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int n, q, num1, num2, num;
    static int[] home_num;
    static List<Integer>[] nearby;
    static boolean[] bo;

    public static long adddd(long num1, long num2) {
    	int cnt = 1;
    	while (num2 / cnt > 0) {
    		num1 *= (long) 10;
    		num1 %= (long) 1_000_000_007;
    		cnt *= 10;
    	}
    	return (num1 + num2) % 1_000_000_007;
    }
    
    static class train {
    	int num;
    	int[] arr;
    	train (int num, int[] arr) {
    		this.num = num;
    		this.arr = arr;
    	}
    }
    
    public static int[] bfs(int num1, int num2) {
    	Deque<train> deq = new ArrayDeque<train>();
    	deq.add(new train(num1, new int[] {num1}));
    	bo[num1] = true;
    	while (!deq.isEmpty()) {
    		train tr = deq.poll();
    		for (int i = 0; i < nearby[tr.num].size(); i++) {
    			int num = nearby[tr.num].get(i);
    			if (!bo[num]) {
    				int[] arr_ = new int[tr.arr.length + 1];
    				for (int j = 0; j < tr.arr.length; j++) {arr_[j] = tr.arr[j]; arr_[tr.arr.length] = num;}
    				if (num == num2) {return arr_;}
    				bo[num] = true;
    				deq.add(new train(num, arr_));
    			}
    		}
    	}
    	return null;
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        home_num = new int[n+1];
        nearby = new ArrayList[n+1];
        for(int i = 1; i < n+1; i++) {
            nearby[i] = new ArrayList<Integer>();
        }
        st = new StringTokenizer(bf.readLine());
        for(int i = 1; i < n+1; i++) {
            home_num[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i < n-1; i++) {
            st = new StringTokenizer(bf.readLine());
            num1 = Integer.parseInt(st.nextToken());
            num2 = Integer.parseInt(st.nextToken());
            nearby[num1].add(num2);
            nearby[num2].add(num1);
        }
        
        for(int i = 0; i < q; i++) {
            st = new StringTokenizer(bf.readLine());
            num1 = Integer.parseInt(st.nextToken());
            num2 = Integer.parseInt(st.nextToken());
            if (num1 == num2) {sb.append(home_num[num1]).append("\n"); continue;}
            bo = new boolean[n+1];
            int[] arr = bfs(num1, num2);
            long l = 0;
            for (int j = 0; j < arr.length; j++) {
				l = adddd((long) l, (long) home_num[arr[j]]);
			}
            sb.append(l).append("\n");            
        }
        System.out.println(sb);
    }
}