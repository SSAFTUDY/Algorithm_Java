
import java.io.*;
import java.util.*;

public class SWEA_1248 {

	static ArrayList<Integer>[] tree;
	static int[] depth;
	static int[] parent;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine());

		for (int t = 0; t < tc; t++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			tree = new ArrayList[V + 1];

			for (int i = 1; i <= V; i++) {
				tree[i] = new ArrayList<Integer>();

			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) {  // 부모 노드 배열 입력

				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				parent[s] = e;
			}
			depth = new int[V + 1];
			parent = new int[V + 1];
			visited = new boolean[V + 1];

			BFS(1);
			int LCA = calculateLCA(a, b);
			System.out.println("#" + (t+1) + " " + LCA);

		}

	}

	public static int calculateLCA(int a, int b) { // LCA 구하는 메소드
		if (depth[a] < depth[b]) { // b가 더 깊으면 a,b 바꾸기
			int temp = a;
			a = b;
			b = temp;
		}

		while (depth[a] != depth[b]) { // a를 b노드 깊이만큼 올리기
			a = parent[a];
		}

		while (a != b) { // 같은 조상이 나올때까지 위로 올리기
			a = parent[a];
			b = parent[b];
		}
		return a;
	}

	public static void BFS(int node) { // 각 노드의 depth를 저장
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(node);
		visited[node] = true;

		int level = 1;
		int now_size = 1;
		int count = 0;
		while (!queue.isEmpty()) {
			int now_node = queue.poll();
			for (int next : tree[now_node]) {
				if (!visited[next]) {
					visited[next] = true;
					queue.add(next);

					depth[next] = level; // 노드 depth 저장하기
				}
			}
			count++;
			if (count == now_size) {
				count = 0;
				now_size = queue.size();
				level++;
			}
		}
	}

}
