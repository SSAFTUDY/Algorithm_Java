package test;

import java.io.*;
import java.util.*;

public class BOJ4538 {

	static PriorityQueue<String> pq = new PriorityQueue<>();
	static int result;

	static class TrieNode {
		Map<Character, TrieNode> childNode = new HashMap<>();
		boolean terminal;
		int count;

		TrieNode() {

		}

		public void insert(String word) {
			TrieNode trieNode = this;
			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);

				trieNode.childNode.putIfAbsent(c, new TrieNode());
				trieNode = trieNode.childNode.get(c);

				if (i == word.length() - 1) {
					trieNode.terminal = true;
					return;
				}
			}
		}

		public boolean contains(String word) {
			TrieNode trieNode = this;
			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				TrieNode node = trieNode.childNode.get(c);

				if (node == null) {
					return false;
				}

				trieNode = node;
			}

			if (trieNode.terminal == true) {
				trieNode.count += 1;
				result = trieNode.count;
			}
			return trieNode.terminal;
		}

	}

	public static void main(String[] args) throws IOException {
		TrieNode trieNode = new TrieNode();
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int total = 1;
		int idx = 0;
		while (s != null) {
			if (!trieNode.contains(s)) {
				trieNode.insert(s);
				pq.add(s);
			}
			s = br.readLine();
			total++;
		}
	

		while (!pq.isEmpty()) {
			String out = pq.poll();
			trieNode.contains(out);
			double value = (result * 1.0) / ((total -1) * 1.0) * 100;
			sb.append(out).append(" ").append(String.format("%.4f", value)).append('\n');
		}
		System.out.println(sb);
	}
}
