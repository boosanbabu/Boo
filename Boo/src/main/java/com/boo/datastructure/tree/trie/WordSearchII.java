package com.boo.datastructure.tree.trie;

import java.util.ArrayList;
import java.util.List;

public class WordSearchII {
	class TrieNode {
		TrieNode[] children;
		String word;

		TrieNode() {
			children = new TrieNode[26];
		}
	}

	TrieNode root;

	public void insert(String word) {
		TrieNode ptr = root;
		for (int i = 0; i < word.length(); i++) {
			int idx = word.charAt(i) - 'a';
			if (ptr.children[idx] == null) {
				ptr.children[idx] = new TrieNode();
			}
			ptr = ptr.children[idx];
		}
		ptr.word = word;
	}

	public List<String> findWords(char[][] board, String[] words) {
		root = new TrieNode();
		for (String s : words)
			insert(s);

		List<String> res = new ArrayList<>();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				traverse(board, i, j, root, res);
			}
		}
		return res;
	}

	private void traverse(char[][] board, int i, int j, TrieNode node, List<String> res) {
		char c = board[i][j];
		board[i][j] = '#';
		if (c == '#' || node.children[c - 'a'] == null)
			return;
		node = node.children[c - 'a'];
		if (node.word != null) {
			res.add(node.word);
			node.word = null;
		}
		int dir[] = { -1, 0, 1, 0, -1 };
		for (int d = 0; d < 4; d++) {
			int newI = i + dir[d];
			int newJ = j + dir[d + 1];
			if (newI > -1 && newJ > -1 && newI < board.length && newJ < board[0].length)
				traverse(board, newI, newJ, node, res);
		}
		board[i][j] = c;
	}

	static int solve(String S) {
		// Write your code here
		// maintain balance of string
		System.out.print(S);
		int bal = 0;
		int ans = 0;

		for (int i = 0; i < S.length(); ++i) {
			bal += S.charAt(i) == '{' ? -2 : 1;
			// It is guaranteed bal >= -2
			if (bal == -3) {
				ans++;
				bal += 4;
			} else if (bal == 2) {
				ans++;
				bal -= 2;
			}
		}

		if (bal < 0)
			bal = Math.abs(bal / 2);
		return Math.abs(bal + ans);
	}

	public static void main(String[] args) {
		System.out.println(solve("{}}{}}{}}{}}"));
		System.out.println(solve("{}{}}}"));
		System.out.println(solve("{{{}"));
		System.out.println(solve("{}}}}}}"));
		System.out.println(solve("{{{{{}}}}{"));
		System.out.println(solve("{}{}{}{}{}{}}{"));
	}

}
