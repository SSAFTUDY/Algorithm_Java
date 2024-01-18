package study_algorithm_b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_1406 {
	public static class Node {
		char word;
		Node left;
		Node right;
	}

	public static class List {
		static final int MAX = 600000;
		Node cursor;
		Node start;
		int length;

		public List(String string) {
			init(string);
		}

		public void left() {
			if (cursor.left == null)
				return;
			cursor = cursor.left;
		}

		public void right() {
			if (cursor.right == null)
				return;
			cursor = cursor.right;
		}

		public void delete() {
			// length == 0일 때
			if (length == 0 || cursor.left == null)
				return;

			if (length == 1) {
				length = 0;
				return;
			}

			// 일반적인 경우
			cursor.left.right = cursor.right;
			cursor.right.left = cursor.left;
			cursor = cursor.left;
			length--;
		}

		public void add(String string) {
			// length == 0일 때
			if (length == 0) {
				init(string);
			}

			// 일반적인 경우
			Node right = cursor.right;

			for (int i = 0; i < string.length(); i++) {
				Node tmp = new Node();
				tmp.word = string.charAt(i);

				cursor.right = tmp;
				tmp.left = cursor;
				cursor = tmp;
			}
			cursor.right = right;
			if (cursor.right != null) {
				cursor.right.left = cursor;
			}

		}

		private void init(String string) {
			Node tmp = new Node();
			cursor = tmp;
			start = tmp;
			cursor.word = string.charAt(0);

			for (int i = 1; i < string.length(); i++) {
				tmp = new Node();
				tmp.word = string.charAt(i);
				tmp.left = cursor;
				cursor.right = tmp;
				cursor = tmp;
			}
			length = string.length();
		}

		public Node startNode() {
			return start;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List list = new List(br.readLine());
		int n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			String[] input = br.readLine().split(" ");
			if (input[0].equals("L")) {
				list.left();
			} else if (input[0].equals("D")) {
				list.right();
			} else if (input[0].equals("B")) {
				list.delete();
			} else if (input[0].equals("P")) {
				list.add(input[1]);
			}
		}

		StringBuilder sb = new StringBuilder();
		Node node = list.startNode();
		while (node != null) {
			sb.append(node.word);
			node = node.right;
		}
		System.out.println(sb);
	}
}
