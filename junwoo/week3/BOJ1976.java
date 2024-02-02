import java.io.*;
import java.util.*;

class Main {
	static int n, m, num;
	static int[] trip, parent;
	
	public static int find_parent(int num) {
		while (num != parent[num]) {
			num = parent[num];
		}
		return num;
	}
	
	public static boolean isConnected(int init, int dest) {
		parent[init] = find_parent(init);
		parent[dest] = find_parent(dest);
		if (parent[init] == parent[dest]) {
			return true;
		} else { return false; }
	}
	
	public static void main(String args[]) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(bf.readLine());
        m = Integer.parseInt(bf.readLine());
        parent = new int[n+1];
        trip = new int[m];
        for (int i = 1; i < n+1; i++) {
        	st = new StringTokenizer(bf.readLine());
        	for (int j = 1; j < n+1; j++) {
        		// 연결된 마을 중에 가장 작은 숫자를 부모로 삼는다.
        		// 부모가 없다면 자기 자신을 부모로 삼는다.
        		num = Integer.parseInt(st.nextToken());
        		if (i > j && num == 1) {
        			if (parent[i] == 0) { 
        				parent[i] = find_parent(j);
        			}
        			else {
        				num = (find_parent(j) > parent[i]) ? parent[i] : find_parent(j);
        				parent[find_parent(j)] = num;
        				parent[parent[i]] = num;
        				parent[i] = num;
        			}
        		}
        		if (parent[i] == 0 && i > j && num == 1) {parent[i] = j;}
			}
        	if (parent[i] == 0) { parent[i] = i; }
		}
        System.out.println(Arrays.toString(parent)); 
        st = new StringTokenizer(bf.readLine());
        int bf_num = Integer.parseInt(st.nextToken());
        for (int i = 1; i < m; i++) {
        	int num = Integer.parseInt(st.nextToken());
//        	System.out.println(num + " " + bf_num);
//        	System.out.println(Arrays.toString(parent)); 
        	if (!isConnected(num, bf_num)) {
//        		System.out.println(num + " " + bf_num);
        		System.out.println("NO");
        		
        		return;
        	}
        	bf_num = num;
		}
        System.out.println("YES");
	}
}