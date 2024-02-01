import java.io.*;
import java.util.*;

public class BOJ_20955 {

	static int parent[];
	static int count = 0;
	static int divide = 0;
	static int N;
	static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		parent = new int[N + 1];
		for (int i = 1; i < N + 1; i++) { // 부모 노드 초기화
			parent[i] = i;
		}

		int u, v;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());

			if (find(u) == find(v)) {
				divide++;
			} else {
				union(u, v);
			}

		}
		count();
		System.out.println(divide + count - 1);

	}

	public static int find(int a) { // find 연산
		if (a == parent[a]) {
			return a;
		}
		return parent[a] = find(parent[a]);
	}

	public static void union(int a, int b) { // union 연산
		a = find(a);
		b = find(b);
		if (a != b) {
			parent[b] = a;
		}
	}

	public static void count() { // 그룹의 개수 세기
		for (int i = 1; i < N + 1; i++) {
			if (parent[i] == i) {
				count++;
			}
		}
	}
}
