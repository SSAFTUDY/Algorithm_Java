import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static class Node {
		Node left;
		Node right;
		String value;

		public Node() {
		}

		public Node(String value) {
			this.value = value;
		}
	}

	static class LinkedList {
		Node head;
		Node tail;
		int size;

		public LinkedList() {
			head = new Node();
			tail = new Node();
			head.right = tail;
			tail.left = head;
			size = 0;
		}
		
		public String getValue(int idx) {
			Node curr = head;
			for (int i = 0; i < idx; i++) {
				curr = curr.right;
			}
			return curr.value;
		}

		public void addLast(String str) {
			Node node = new Node(str);
			node.right = tail;
			node.left = tail.left;
			tail.left.right = node;
			tail.left = node;
		}

		public void add(int idx, String str) {
			Node curr = head;
			for (int i = 0; i < idx; i++) {
				curr = curr.right;
			}
			Node node = new Node(str);
			node.left = curr;
			node.right = curr.right;
			curr.right.left = node;
			curr.right = node;
		}

		public void remove(int idx) {
			Node curr = head;
			for (int i = 0; i < idx; i++) {
				curr = curr.right;
			}
			curr.right.right.left = curr;
			curr.right = curr.right.right;
		}
		
		public String getTen() {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 10; i++) {
				sb.append(getValue(i+1)).append(" ");
			}
			return sb.toString();
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int t=0; t<10; t++) {
			LinkedList list = new LinkedList();
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				list.addLast(st.nextToken());
			}
			int num = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens()) {
				switch (st.nextToken()) {
				case "I": {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					for (int i = 0; i < y; i++) {
						String s = st.nextToken();
						list.add(x + i, s);
					}
					break;
				}
				case "D": {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					for (int i = 0; i < y; i++) {
						list.remove(x);
					}
					break;
				}
				case "A": {
					int y = Integer.parseInt(st.nextToken());
					for (int i = 0; i < y; i++) {
						list.addLast(st.nextToken());
					}
				}
				break;
				}
			}
			System.out.println("#" + (t+1) + " " + list.getTen());
		}
		
	}
}
