import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Main {

	public static class Node {
		int count;
		Map<Character, Node> childs;
		public Node() {
			count = 0;
			childs = new TreeMap<>();
		}
	}
	
	public static char[] save = new char[31]; // 최대 글자 30
	public static double countAll = 0.0;
	public static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Arrays.fill(save, '\n');
		
		// get input
		String input = "";
		Node root = new Node();
		while ((input = br.readLine()) != null) {
			// process 1
			// 값을 trie에 집어넣기
			countAll += 1;
			Node current = root;
			for (int i = 0; i < input.length(); i++) {
				char c = input.charAt(i);
				if(!current.childs.containsKey(c)) {
					current.childs.put(c, new Node());
				}
				current = current.childs.get(c);
			}
			current.count++;
		}
		
		// process2
		// trie 순회하기
		solve(root, 0);
		
		// output
		System.out.println(sb);
	}
	
	public static void solve(Node parent, int depth) {
		if(parent.count != 0) {
			// 지금까지 저장해둔 save 배열을 읽기
			int idx = 0;
			while(save[idx] != '\n') {
				sb.append(save[idx]);
				idx++;
			}
			double ratio = parent.count / countAll * 100;
			sb.append(' ').append(String.format("%.4f", ratio)).append('\n');
		}
		
		Set<Character> set = parent.childs.keySet();
		for (Character c : set) {
			save[depth] = c;
			solve(parent.childs.get(c), depth + 1);
			save[depth] = '\n';
		}
	}

}
