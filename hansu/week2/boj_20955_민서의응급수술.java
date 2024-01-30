import java.io.*;
import java.util.*;

public class Solution {

	/** 연산 횟수 */
	private static int res = 0;
	private static int parent[];

	/** 노드 연결
	 * 이미 같은 그룹이라면 연결 안함 (끊기 연산) */
	private static void union(int a, int b) {
		int x = find(a), y = find(b);
		
		if (x == y) {
			res++;
			return;
		}
		parent[Math.max(x, y)] = Math.min(x, y);
	}
	
	/** 부모 탐색 */
	private static int find(int x) {
		if (parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
	
	/** 서로 연결되지 않은 그룹 연결 연산
	 * 연산 횟수: (그룹의 수) - 1
	 * 그룹의 수: (루트노드의 수) */
	private static void linkEachGroups() {
		int root = 0;
		
		for (int i = 1; i < parent.length; i++)
			if (parent[i] == i)
				root++;
		res += root - 1;
	}
	
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
	    
	    //init
	    parent = new int[N + 1];
	    for (int i = 0; i <= N; i++)
	    	parent[i] = i;
	    
	    //parsing
	    for (int i = 0; i < M; i++) {
	    	st = new StringTokenizer(br.readLine());
	    	int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
	    	
    		union(a, b);
	    }
	    
	    linkEachGroups();
	    System.out.println(res);
    }
    
}
