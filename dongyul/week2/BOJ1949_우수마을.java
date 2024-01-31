package ssaftudyweek2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;


public class BOJ1949 {
	
	static class Node{
		Node parent;
		List<Node> children;
		int index;

		public Node(int value) {
			children = new ArrayList<>();
			this.index = value;
		}
	}
	
	static int N;
	static Node[] tree;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		tree = new Node[N+1];
		for(int i = 1; i<N+1; i++) {
			tree[i] = new Node(i);
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] cost = new int[N+1];
		
		for(int i = 1; i<N+1; i++) {
			cost[i] = Integer.parseInt(st.nextToken()); 
		}
		
		for(int i = 0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(a>b) {	//마을 번호가 작은 마을을 부모로 두기 위한 과정 
				int temp = a;
				a = b;
				b = temp;
			}
			tree[a].children.add(tree[b]);
			tree[b].parent = tree[a];
		}
		
		
		int[][] dp = new int[N+1][2];	//0: 해당 마을이 우수 x / 1: 해당 마을이 우수 
		Deque<Integer> deque = findLeap();
		
		int[] visited = new int[N+1];

		while(!deque.isEmpty()) {
			int curr = deque.removeFirst();
			if(visited[curr] == 1) { // 중복 탐색 방지
				continue;
			}
			boolean flag = true;	//자식이 전부 탐색 되었는지 확인
			for(Node node : tree[curr].children) {	//자식이 탐색 완료 되었는지 확인, 안됐으면 탐색을 후 순위로 미룸 
				if(visited[node.index]!=1) {
					deque.addLast(curr);
					flag = false;
					break;
				}
			}
			if(!flag) {
				continue;	//자식이 탐색 안됐으면 
			}
			visited[curr] = 1;
			for(Node node : tree[curr].children) {
				dp[curr][0] += Math.max(dp[node.index][0], dp[node.index][1]);	//본인이 우수 마을이 아닐 때는 자식의 우수마을 여부는 상관없음
				dp[curr][1] += dp[node.index][0];	//본인이 우수마을일때는 자식이 모두 우수마을이 아니어야한다.
			}
			dp[curr][1] += cost[curr];
			if(tree[curr].parent != null) {
				deque.add(tree[curr].parent.index);	//부모노드 넣어줌
			}
		}
		
		System.out.println(Math.max(dp[1][0], dp[1][1]));
		
	}

	private static Deque<Integer> findLeap() {	//리프노드 찾기
		Deque<Integer> deque = new LinkedList<>();
		Deque<Integer> deque2 = new LinkedList<>();
		deque2.addLast(1);
		while(!deque2.isEmpty()) {
			int curr = deque2.removeFirst();
			if(tree[curr].children.isEmpty()) {
				deque.add(curr);
			}
			else {
				for (Node node : tree[curr].children) {
					deque2.addLast(node.index);
				}
			}
		}
		
		return deque;
	}
}
