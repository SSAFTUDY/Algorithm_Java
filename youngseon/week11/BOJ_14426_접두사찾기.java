package test;

import java.io.*;
import java.util.*;

public class BOJ_14426 {
	static int result;

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

				if (node == null) {
					return false;
				}

				trieNode = node;
			}

			result += 1;
	
			return trieNode.terminal;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		TrieNode trieNode = new TrieNode();
	
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			trieNode.insert(br.readLine());
		}

		for (int i = 0; i < M; i++) {
			String input = br.readLine();
			trieNode.contains(input);	
		}
		System.out.println(result);
	}
}
