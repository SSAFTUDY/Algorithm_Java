package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_1976 {

	static int[] parent;
	static int[] size;

//	재귀를 이용해 가장 위에 있는 부모를 가르키도록
	private static int find(int value) {
		if (value == parent[value]) {
			return value;
		}
		return parent[value] = find(parent[value]);
	}

//	크기가 작은 집합이 크기가 큰 집합 아래에 들어오도록 구현
	private static void union(int value1, int value2) {
		int p1 = find(value1);
		int p2 = find(value2);
		
		if (size[p1] >= size[p2]) {
			parent[p2] = p1;
			size[p1]++;
		} else {
			parent[p1] = p2;
			size[p2]++;
		}
		
	}

//	맨 위의 부모가 같으면 동일한 union
	private static boolean isUnion(int value1, int value2) {
		if (find(value1) == find(value2)) {
			return true;
		}
		return false;
	}

	// 연결되어있지 않은 도시 찾기
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// get input
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

//		초기 집합은 자기 자신 혼자 있는 원소의 갯수가 1인 집합
		parent = new int[N + 1];
		size = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

//		배열의 값이 1이면 union인지 판단
//		두 원소가 union이 아니라면 union으로 만듦
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int in = Integer.parseInt(st.nextToken());
				if (in == 1 && !isUnion(i, j)) {
					union(i, j);
				}
			}
		}

		// process
//		경로보다는 어떤 노드가 경로에 있는지가 중요하므로 중복 노드를 Set으로 삭제
//		그 후 stack에 set에 있는 원소들을 넣어줌
		st = new StringTokenizer(br.readLine());
		Set<Integer> nodes = new HashSet<>();
		Stack<Integer> stk = new Stack<>();
		for (int i = 0; i < M; i++) {
			int in = Integer.parseInt(st.nextToken());
			if (nodes.add(in)) {
				stk.push(in);
			}
		}

//		스택을 하나씩 꺼내면서 스택에 있는 두 노드가 union인지 확인
//		하나라도 union이 아니라면 false
		boolean check = true;
		int city1 = -1;
		int city2 = -1;
		if (!stk.isEmpty()) {
			city1 = stk.pop();
		}
		while (!stk.isEmpty()) {
			city2 = stk.pop();
			if (!isUnion(city1, city2)) {
				check = false;
				break;
			}
		}

		if (check) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}

}
