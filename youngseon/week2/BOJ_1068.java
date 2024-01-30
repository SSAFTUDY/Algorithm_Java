import java.io.*;
import java.util.*;

public class BOJ_1068 {

	private static int N;
	private static int count; // 리프노드 개수
	private static ArrayList<Integer>[] tree;
	private static boolean visited[];

	private static void printCount() { // 단말 노드 출력 로직
		/*
		 * 
		 * [1] [3, 4] [] [] []
		 */
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < tree[i].size(); j++) {
				int num = tree[i].get(j);
				if (tree[num].size() == 0) {
					count++;
				}
			}
		}
		System.out.println(count);

	}

	private static int find_where(int n) {
		for (int i = 0; i < N; i++) {    // 해당 숫자가 있는 배열의 위치 반환 로직
			if (tree[i].contains(n)) {
				return i;
			}
		}
		return -1;
	}

	private static void dfs(int remove) { // 삭제 로직
		if (visited[remove]) {
			return;
		}
		visited[remove] = true;
		int idx = find_where(remove);
		if(idx !=-1) {
			tree[idx].remove(Integer.valueOf(remove));
		}
		ArrayList<Integer> copy = tree[remove];
		tree[remove] = new ArrayList<>();
		for (int i : copy) {
			if (visited[i] == false) {
				dfs(i);
			}
		}
		
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		tree = new ArrayList[N]; // ArrayList로 트리 구현
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			tree[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			int parent_index = Integer.parseInt(st.nextToken());
			if (parent_index != -1) {
				tree[parent_index].add(i); // 값 저장
			}
		}

		int remove = Integer.parseInt(br.readLine()); // 삭제할 노드 찾고, 자식 노드 없애기
		if (remove == 0) { // 삭제 노드가 0이면 루트 노드만 남으므로
			System.out.println(0);
		} else {
			dfs(remove);
			printCount();
		}
//		for(int i=0;i<N;i++) {
//			System.out.println(tree[i]);
//		}
	}
}
