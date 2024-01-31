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

	static class Node {
		List<Node> children;
		int index;	//정점의 번호
		int cnt;	//연결된 노드의 개수

		public Node(int value) {
			children = new ArrayList<>();
			this.index = value;
			cnt = 0;
		}
	}

	static int N;
	static Node[] tree;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		tree = new Node[N + 1];
		for (int i = 1; i < N + 1; i++) {
			tree[i] = new Node(i);
		}

		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] cost = new int[N + 1];	//비용 정보

		for (int i = 1; i < N + 1; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N - 1; i++) { // 양방향 연결
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			tree[a].children.add(tree[b]);
			tree[b].children.add(tree[a]);
			tree[a].cnt += 1;
			tree[b].cnt += 1;
		}

		int[][] dp = new int[N + 1][2]; // 0: 해당 마을이 우수 마을이 아닐 때 / 1: 해당 마을이 우수 마을일 때
		Deque<Integer> deque = findLeap();

		while (!deque.isEmpty()) {
			int curr = deque.removeFirst();
			if (tree[curr].cnt == 0) { // 마지막 하나 남았을 때
				dp[curr][1] += cost[curr];
				System.out.println(Math.max(dp[curr][0], dp[curr][1]));
			}
			if (tree[curr].cnt == 1) { //리프노드일때만 dp 진행
				dp[curr][1] += cost[curr];
				Node node = tree[curr].children.get(0);
				dp[node.index][0] += Math.max(dp[curr][0], dp[curr][1]); // 본인이 우수 마을이 아닐 때는 자식의 우수마을 여부는 상관없음
				dp[node.index][1] += dp[curr][0]; // 본인이 우수마을일때는 자식이 모두 우수마을이 아니어야한다.

				node.children.remove(tree[curr]); //리프노드는 탐색을 마치면 연결 끊어주기
				if (--node.cnt == 1) { //연결 끊었을 때 리프노드면 deque에 넣어줌
					deque.add(node.index);
				}
			}
		}

	}

	private static Deque<Integer> findLeap() { // 리프노드 찾기
		Deque<Integer> deque = new LinkedList<>(); // 리프노드가 담길 덱
		Deque<Integer> deque2 = new LinkedList<>(); // 탐색을 위한 덱
		int[] visited = new int[N + 1];
		visited[1] = 1;
		deque2.addLast(1);
		while (!deque2.isEmpty()) {
			int curr = deque2.removeFirst();
			visited[curr] = 1;
			if (tree[curr].cnt == 1) {
				deque.add(curr);
			}
			for (Node node : tree[curr].children) {
				if (visited[node.index] == 0) {
					deque2.addLast(node.index);
				}
			}
		}
		return deque;
	}
}
