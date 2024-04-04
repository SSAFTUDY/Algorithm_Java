
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_14426 {

	public static class Node {
		Map<Character, Node> childs;
		public Node() {
			childs = new HashMap<>();
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		// get input
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// make trie
		Node root = new Node();
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			Node point = root;
			for (int j = 0; j < input.length(); j++) {
				char c = input.charAt(j);
				if(!point.childs.containsKey(c)) {
					point.childs.put(c, new Node());
				}
				point = point.childs.get(c);
			}
		}
		
		// find
		int count = 0;
		for (int i = 0; i < M; i++) {
			String find = br.readLine();
			if(find(find, root)) {
				count++;
			}
		}
		
		// output
		System.out.println(count);
	}

	private static boolean find(String find, Node root) {
		Node point = root;
		for (int i = 0; i < find.length(); i++) {
			char c = find.charAt(i);
			if(point.childs.containsKey(c)) {
				point = point.childs.get(c);
			}
			else {
				return false;
			}
		}
		
		return true;
	}
	
}
