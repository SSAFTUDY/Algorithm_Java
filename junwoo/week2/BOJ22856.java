import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
public class Main {
	static int[] parent;
	static int[] right_child;
	
    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        StringTokenizer st;
        int a, b, c, cur, sum; // cur : current
        parent = new int[n + 1];
        right_child = new int[n + 1];
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(in.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			if (c > 0) {right_child[a] = c; parent[c] = a;}
//			System.out.println(Arrays.toString(left_cnt));
		}
        // 루트에서 시작해서 루트의 오른쪽으로만 이동했을때의 노드들의 집합을 set Right라고 하자
        cur = 1;
        sum = 2 * n - 2;
        while (right_child[cur] > 0) { //전체 노드 수 * 2 - 2 - set Right의 원소 수
        	sum -= 1;
        	cur = right_child[cur];
        }
//		System.out.println(Arrays.toString(left_cnt));
        System.out.println(sum);
    }
}