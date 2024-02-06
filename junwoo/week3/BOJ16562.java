import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int n, m, k, num1, num2, ans;
    static int[] cost, parent;
    static boolean[] root_boolean;

    
    public static int find_parent(int num) {
    	int cur = num;
    	while (parent[cur] != 0) {
    		cur = parent[cur];
    	} return cur;
    }
    public static void make_relation(int num1, int num2) {
    	// 더 작은 비용을 가진 노드를 부모로 정한다.
    	num1 = find_parent(num1);
    	num2 = find_parent(num2);
    	if (num1 == num2) {}
    	else if (cost[num1] > cost[num2]) {
    		parent[num1] = num2;
    	}
    	else {
    		parent[num2] = num1;
    	}
    }
    
    public static void calc_ans(int num) {
    	int pa = find_parent(num);
    	if (!root_boolean[pa]) {
    		root_boolean[pa] = true;
    		ans += cost[pa];
    	}	
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        cost = new int[n+1]; parent = new int[n+1];
        root_boolean = new boolean[n+1];
        st = new StringTokenizer(bf.readLine());
        for(int i = 1; i < n+1; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 1; i < m+1; i++) {
        	st = new StringTokenizer(bf.readLine());
        	num1 = Integer.parseInt(st.nextToken());
            num2 = Integer.parseInt(st.nextToken());
            if (cost[num1] < cost[num2]) { make_relation(num1, num2); }
            else { make_relation(num2, num1); }
        }
        for(int i = 1; i < n+1; i++) {
        	calc_ans(i);
        }
        if (ans <= k) {System.out.println(ans);}
        else {System.out.println("Oh no");}
        System.out.println(Arrays.toString(parent) + " " +ans );
    }
}