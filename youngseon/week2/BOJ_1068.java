import java.io.*;
import java.util.*;

public class BOJ_1068 {

	private static int N;
	private static int count = 0; // 리프노드 개수
	private static int remove = 0; // 삭제할 노드
	private static ArrayList<Integer>[] tree;
	private static boolean visited[];

	private static void dfs(int num) { // 삭제 로직
		visited[num] = true;
		int cNode = 0;
		for (int i : tree[num]) {
			if (visited[i] == false && i != remove) {
				cNode++;
				dfs(i);
			}
		}
		if (cNode == 0) {
			count++;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		tree = new ArrayList[N]; // ArrayList로 트리 구현
		visited = new boolean[N];

		int root = 0;
		for (int i = 0; i < N; i++) {
			tree[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < N; i++) {
			int parent_index = Integer.parseInt(st.nextToken());
			if (parent_index != -1) {
				tree[parent_index].add(i); // 값 저장
				tree[i].add(parent_index);
			} else {
				root = i;
			}
		}

		remove = Integer.parseInt(br.readLine()); // 삭제할 노드 찾고, 자식 노드 없애기
		if (remove == root) { // 삭제 노드 부모가 루트 노드이면
			System.out.println(0);
		} else {
			dfs(root);
			System.out.println(count);
		}
//		for(int i=0;i<N;i++) {
//			System.out.println(tree[i]);
//		}
	}
}
