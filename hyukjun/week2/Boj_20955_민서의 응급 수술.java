import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Boj_20955 {

	// 트리란, 사이클이 존재하지 않는 연결 그래프

	static boolean[] check;
	static List<Integer>[] neighbor;
	static Set<Integer> union;
	static int count = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// get input
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		check = new boolean[N + 1];
		check[0] = true;
		neighbor = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			neighbor[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			neighbor[u].add(v);
			neighbor[v].add(u);
		}

		// process
		for (int i = 1; i <= N; i++) {
			if (check[i]) {
				continue;
			}
			union = new HashSet<>();
			check[i] = true;
			union.add(i);
			bfs(i);
			// 집합의 갯수를 더해줌
			count++;
		}

		// output
		// 집합을 연결하는 횟수 = 집합들의 갯수 - 1
		System.out.println(count - 1);
	}

	private static void bfs(int startIdx) {
		int vertexCount = 0;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(startIdx);
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			for (int i = 0; i < neighbor[cur].size(); i++) {
				int adj = neighbor[cur].get(i);
				vertexCount++;
				if(!check[adj]) {
					check[adj] = true;
					union.add(adj);
					queue.add(adj);
				}
			}
		}
		// 간선의 갯수가 노드의 갯수 - 1보다 많을 경우 그만큼을 삭제하는 연산 횟수
		count += (vertexCount / 2 - union.size() + 1);
	}

}
