import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Boj_1068 {

	static final int MAX = 50;
	
	static Node[] list;
	static boolean[] check;
	static int count = 0;

	private static class Node {
		int[] child;
		int childSize;

		public Node() {
			child = new int[MAX];
			childSize = 0;
		}

		// 자식 배열에 자식 인덱스를 추가하고 배열 사이즈를 1 증가
		public void childAdd(int value) {
			child[childSize] = value;
			childSize++;
		}

		// 자식 배열에 자식 인덱스가 있으면 삭제하고 배열 사이즈를 1 감소
		public void childDelete(int value) {
			int idx = -1;
			for (int i = 0; i < childSize; i++) {
				if (value == child[i]) {
					idx = i;
					break;
				}
			}
			if (idx == -1)
				return;

			for (int i = idx; i < childSize - 1; i++) {
				child[i] = child[i + 1];
			}
			childSize--;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// get input
		int N = Integer.parseInt(br.readLine());
		list = new Node[N];
		check = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			list[i] = new Node();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int root = -1;
		int parent = -1;
		for (int i = 0; i < N; i++) {
			parent = Integer.parseInt(st.nextToken());
			if(parent == -1) {
				root = i;
				continue;
			}
			list[parent].childAdd(i);
		}
		
		int delete = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			list[i].childDelete(delete);
		}
		
		// process
		// delete할 노드는 방문할 수 없게끔 check 배열을 true로 만듬
		check[delete] = true;
		dfs(root);
		
		// output
		System.out.println(count);
	}

	private static void dfs(int idx) {
		// 이미 방문한 노드라면 return
		if(check[idx]) {
			return;
		}
		check[idx] = true;
		
		if(list[idx].childSize == 0) {
			count++;
		} else {
			for (int i = 0; i < list[idx].childSize; i++) {
				if(!check[list[idx].child[i]]) {
					dfs(list[idx].child[i]);
				}
			}
		}
	}

}
