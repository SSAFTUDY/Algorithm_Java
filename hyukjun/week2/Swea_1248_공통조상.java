import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Swea_1248 {

	private static class Node {
		int value = -1;
		Node left;
		Node right;
		Node parent;
		int depth = 0;

		public Node(int value) {
			this.value = value;
		}
	}

	static Node[] list;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		sb = new StringBuilder();
		for (int TC = 1; TC <= T; TC++) {
			sb.append("#").append(TC).append(" ");

			// get input
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			int tmp1 = Integer.parseInt(st.nextToken());
			int tmp2 = Integer.parseInt(st.nextToken());

			list = new Node[V + 1];
			for (int i = 1; i < list.length; i++) {
				list[i] = new Node(i);
			}

			st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens()) {
				int parent = Integer.parseInt(st.nextToken());
				int child = Integer.parseInt(st.nextToken());

				if (list[parent].left != null) {
					list[parent].right = list[child];
				} else {
					list[parent].left = list[child];
				}
				list[child].parent = list[parent];
			}

			// process
			// depth 계산
			BFS(1);

			// 공통 조상 계산
			Node node1 = list[tmp1];
			Node node2 = list[tmp2];
			LCA(node1, node2);
		}
		System.out.println(sb);
	}

	private static void LCA(Node node1, Node node2) {
		boolean isNode1Deeper = node1.depth >= node2.depth;

		while (node1.depth != node2.depth) {
			if (isNode1Deeper) {
				node1 = node1.parent;
			} else {
				node2 = node2.parent;
			}
		}

//		System.out.println("node1 : " + node1.value + ", depth : " + node1.depth);
//		System.out.println("node2 : " + node2.value + ", depth : " + node2.depth);
		
		while(node1.value != node2.value) {
			node1 = node1.parent;
			node2 = node2.parent;
		}
		
		sb.append(node1.value).append(" ").append(BFS(node1.value)).append("\n");
	}

	private static int BFS(int startIdx) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(list[startIdx]);
		int count = 1;
		while (!queue.isEmpty()) {
			Node parent = queue.poll();
			if (parent.left != null) {
				parent.left.depth = parent.depth + 1;
				queue.add(parent.left);
				count++;
			}
			if (parent.right != null) {
				parent.right.depth = parent.depth + 1;
				queue.add(parent.right);
				count++;
			}
		}
		return count;
	}

}
