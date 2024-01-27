package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_22856 {

	static class Node {
		int value;
		Node left;
		Node right;

		Node(int value) {
			this.value = value;
		}
	}

	static int N;
	static Node[] list;
	
	static int check = 0;
	static int count = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// get input
		N = Integer.parseInt(br.readLine());
		list = new Node[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new Node(i);
		}
		
		StringTokenizer st;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			
			if(left != -1) {
				list[idx].left = list[left];
			}
			if(right != -1) {
				list[idx].right = list[right];
			}
		}

		// process
		solve(list[1]);
	}

	private static void solve(Node node) {
		// 왼쪽 노드 순회
		if(node.left != null && (check & (1 << node.left.value)) == 0) {
			count++;
			solve(node.left);
			count++;
		}
		
		// 현재 노드 방문 처리
		// 만약 모두 방문했으면 종료
		check |= (1 << node.value);
		if(check == (((1 << (N + 1)) - 2))) {
			System.out.println(count);
		}
		
		// 오른쪽 노드 순회
		if(node.right != null && (check & (1 << node.right.value)) == 0) {
			count++;
			solve(node.right);
			count++;
		}
	}

}
