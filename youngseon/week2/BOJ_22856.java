import java.io.*;
import java.util.*;

public class BOJ_22856 {

	private static int N;
	private static int[][] tree;
	private static int[] visited;
	private static int count = 0;
	private static ArrayList<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;

		tree = new int[100000][2]; // 0-> 왼쪽 노드 배열, 1-> 오른쪽 노드 배열
		visited = new int[100000];
		int node, left, right;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			node = Integer.parseInt(st.nextToken());
			left = Integer.parseInt(st.nextToken());
			right = Integer.parseInt(st.nextToken());

			tree[node][0] = left; // 값 저장
			tree[node][1] = right;

		}
		inOrder(1); // 루트 노드는 항상 1번 노드이므로
//		System.out.println(list);
		System.out.println(list.size() - 1);

	}

	public static void inOrder(int now) { // 중위 순회

		if (now == -1) {
			return;
		}

		list.add(now);

		if (tree[now][0] != -1) {

			if (visited[tree[now][0]] == 0) {
				visited[tree[now][0]] = 1;
				count++;

				inOrder(tree[now][0]);
//				if (count == N - 1) {
//					return;
//				}
				list.add(now);

			}

		}

		if (tree[now][1] != -1) {

			if (visited[tree[now][1]] == 0) {
				visited[tree[now][1]] = 1;
				count++;

				inOrder(tree[now][1]);
				if (count == N - 1) {
					return;
				}
				list.add(now);
			}
		}
	}

}
