package test;

import java.io.*;
import java.util.*;


public class BOJ_5052 {

	static class TrieNode {
		Map<Character, TrieNode> childNode = new HashMap<>();
		boolean terminal;

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
				if(node.terminal == true && !node.childNode.isEmpty()) {
					return false;
				}
			
				trieNode = node;
			}
			return true;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		TrieNode trieNode = new TrieNode();

		a: for (int T = 0; T < t; T++) {
			PriorityQueue<String> pq = new PriorityQueue<>();
			int n = Integer.parseInt(br.readLine());
			
			for (int i = 0; i < n; i++) {
				String s = br.readLine();
				trieNode.insert(s);
				pq.add(s);
			}
			while (!pq.isEmpty()) {
				String out = pq.poll();
				if (trieNode.contains(out) == false) {
					System.out.println("NO");
					continue a;
				}
			}
			System.out.println("YES");
		}

	}
}
