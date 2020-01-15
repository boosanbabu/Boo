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

	public static void main(String ar[]) {
		Trie tr = new Trie();
		tr.insert("boost");
		tr.insert("boosan");
		tr.insert("boo");
		tr.insert("ram");
		tr.insert("boat");
		tr.insert("root");
		tr.insert("rambabu");
		tr.insert("rambo");
		tr.insert("book");
		tr.insert("cook");
		tr.insert("cool");
		tr.insert("fool");
		tr.insert("fire");
		tr.displayContents();

	}
}
