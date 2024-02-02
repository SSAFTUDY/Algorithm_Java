import java.io.*;
import java.util.*;

public class Solution {
	
	private static int[] parent;
	private static int[] costs;
	
	//코스트가 작은 친구가 부모(리더)
	private static void union(int a, int b) {
		int x = find(a), y = find(b);
		
		if (costs[x] < costs[y]) {
			parent[y] = x;
		} else {
			parent[x] = y;
		}
	}
	
	private static int find(int x) {
		if (parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BitSet isFriend = new BitSet();
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()),
			K = Integer.parseInt(st.nextToken()), cost = 0;
		
		//init parent
		parent = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			parent[i] = i;
		}
		
		//input parsing
		st = new StringTokenizer(br.readLine());
		costs = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			costs[i] = Integer.parseInt(st.nextToken());
		}
		
		//union (grouping)
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
			
			union(a, b);
		}
		
		//각 그룹의 리더의 코스트만 합산
		for (int i = 1; i <= N; i++) {
			int leader = find(i);
			
			//리더 중복연산 방지
			if (!isFriend.get(leader)) {
				cost += costs[leader];
				isFriend.set(leader);
			}
			if (cost > K) {
				System.out.println("Oh no");
				return;
			}
		}
		System.out.println(cost);
	}
	
}
