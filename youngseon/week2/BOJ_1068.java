import java.io.*;
import java.util.*;

public class BOJ1068 {

	private static int N;
	private static int count; // 리프노드 개수
	private static ArrayList<Integer>[] tree;

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

	private static void check(int remove) { // 삭제 로직
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < tree[i].size(); j++) {
				if (tree[i].get(j) == remove) {
					tree[i].remove(j); // 해당 노드 삭제
					break;
				}
			}
		}
		while(true) {  // 모든 자손 노드 없애기
			ArrayList<Integer> list = tree[remove];
			tree[remove] = new ArrayList<>(); 
			
			if(list.size()==0) {
				break;
			}
			for(int k: list) {
				check(k);
			}
		}
	
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		tree = new ArrayList[N]; // ArrayList로 트리 구현
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
			check(remove);
			printCount();
		}
	}
}
