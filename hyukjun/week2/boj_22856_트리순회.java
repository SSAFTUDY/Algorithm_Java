import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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
	static int lastIdx = -1;
	static List<Integer> inOrder;

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

			if (left != -1) {
				list[idx].left = list[left];
			}
			if (right != -1) {
				list[idx].right = list[right];
			}
		}

		// process
		inOrder = new ArrayList<>();
		findLast(list[1]);
		lastIdx = inOrder.get(inOrder.size() - 1);
		
		solve(list[1]);
	}

	private static void findLast(Node node) {
		// 왼쪽 노드 순회
		if (node.left != null) {
			findLast(node.left);
		}

		// 현재 노드 방문 처리
		inOrder.add(node.value);

		// 오른쪽 노드 순회
		if (node.right != null) {
			findLast(node.right);
		}
	}

	private static void solve(Node node) {
		if (node.left != null) {
			// 왼쪽 노드 순회
			count++;
			solve(node.left);
			count++;
		}
		if (node.right != null) {
			// 오른쪽 노드 순회
			count++;
			solve(node.right);
			count++;
		}
		if (node.value == lastIdx) {
			// 만약 모두 방문했으면 종료
			System.out.println(count);
		}
	}

}
