package com.boo.datastructure.tree.trie;

public class Trie {
	TrieNode root;

	Trie() {
		root = new TrieNode();
	}

	public void insert(String str) {
		int length = str.length();
		TrieNode curr = root;
		for (int i = 0; i < length; i++) {
			int idx = str.charAt(i) - 'a';
			if (curr.children[idx] == null) {
				curr.children[idx] = new TrieNode();
			}
			curr = curr.children[idx];
		}
		curr.isEndOfWord = true;
	}

	public void displayContents() {
		displayContents(root, new char[100], 0);
	}

	public void displayContents(TrieNode curr, char[] word, int pos) {
		if (curr.isEndOfWord) {
			for (int i = 0; i < pos; i++)
				System.out.print(word[i]);
			System.out.println();
		}
		for (int i = 0; i < TrieNode.CHAR_SIZE; i++) {
			if (curr.children[i] != null) {
				word[pos] = (char) ('a' + i);
				displayContents(curr.children[i], word, pos + 1);
			}
		}
	}

	public boolean search(String word) {
		return match(word, true);
	}

	public boolean match(String word, boolean fullWord) {
		TrieNode ptr = root;
		for (char c : word.toCharArray()) {
			int idx = c - 'a';
			if (ptr.children[idx] == null)
				return false;
			ptr = ptr.children[idx];
		}
		return fullWord ? ptr.isEndOfWord : true;
	}

	/**
	 * Returns if there is any word in the trie that starts with the given prefix.
	 */
	public boolean startsWith(String prefix) {
		return match(prefix, false);
	}

	void insertGfg(String key) {
		int level;
		int length = key.length();
		int index;

		TrieNode pCrawl = root;

		for (level = 0; level < length; level++) {
			index = key.charAt(level) - 'a';
			if (pCrawl.children[index] == null)
				pCrawl.children[index] = new TrieNode();

			pCrawl = pCrawl.children[index];
		}

		// mark last node as leaf
		pCrawl.isEndOfWord = true;
	}

	public boolean match(String word) {
		TrieNode ptr = root;
		for (char c : word.toCharArray()) {
			int idx = c - 'a';
			if (ptr.children[idx] == null || !ptr.children[idx].isEndOfWord)
				return false;
			ptr = ptr.children[idx];
		}
		return ptr.isEndOfWord;
	}

	public static void main(String ar[]) {
		Trie tr = new Trie();
		String[] arr = { "world", "worl", "wor", "wo", "w" };
		for (String w : arr) {
			tr.insert(w);
		}
		tr.displayContents();
		System.out.println(tr.startsWith("booss"));
		System.out.println(tr.startsWith("ram"));
		System.out.println(tr.search("ram"));
		System.out.println(tr.match("worl"));

	}
}
