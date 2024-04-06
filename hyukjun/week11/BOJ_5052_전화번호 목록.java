import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

	static class Node {
		boolean isWord;
		Map<Character, Node> childs;

		public Node() {
			isWord = false;
			childs = new HashMap<>();
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int TC = 0; TC < T; TC++) {
			// get input
			boolean flag = false;
			Node root = new Node();
			int N = Integer.parseInt(br.readLine());
			String[] list = new String[N];

			for (int i = 0; i < N; i++) {
				list[i] = br.readLine();

				if (!flag) {
					// Trie에 넣기
					Node head = root;
					for (int j = 0; j < list[i].length(); j++) {
						char c = list[i].charAt(j);
						if (!head.childs.containsKey(c)) {
							head.childs.put(c, new Node());
						}
						if (head.isWord) {
							sb.append("NO").append('\n');
							flag = true;
							break;
						}
						head = head.childs.get(c);
					}
					// 단어가 모두 완성되었을 때
					if (head.childs.size() != 0 && !flag) {
						sb.append("NO").append('\n');
						flag = true;
					}
					else
						head.isWord = true;
				}
			}
			if(!flag) {
				sb.append("YES").append('\n');
			}
		}
		
		System.out.println(sb);
	}

}
