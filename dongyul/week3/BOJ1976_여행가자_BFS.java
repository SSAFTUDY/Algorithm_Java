import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * 같은 그룹에 속해있으면, 여행이 가능!
 * Node 리스트를 만들어 연결해주고 BFS를 통해 탐색
 * 여행 경로중 하나의 정점에 대해 BFS를 돌렸을 때 나머지 정점을 방문하지 않았으면, 여행이 불가능 
 */
public class Main {

	static class Node{
		int value;
		List<Node> list;
		
		public Node(int value) {
			this.value = value;
			list = new ArrayList<Node>();
		}
		
	}

	static Node[] node;
	static boolean visited[];
	
	static void BFS(int start) {
		visited[start] = true;
		Deque<Integer> deque = new ArrayDeque<Integer>();
		deque.add(start);
		while(!deque.isEmpty()) {
			int curr = deque.removeFirst();
			for (Node node : node[curr].list) {
				int val = node.value;
				if(!visited[val]) {
					visited[val] = true;
					deque.addLast(val);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		node = new Node[N+1];
		for(int i = 0; i<N+1; i++) {
			node[i] = new Node(i);
		}
		visited = new boolean[N+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				if(Integer.parseInt(st.nextToken()) == 1) {
					node[i].list.add(node[j]);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine()); //
		BFS(Integer.parseInt(st.nextToken()));
		//한 점에 대해 BFS를 돌렸을 때 나머지 값을 방문 안했으면 NO
		for (int i = 0; i < M - 1; i++) {
			if (!visited[Integer.parseInt(st.nextToken())]) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}

}
