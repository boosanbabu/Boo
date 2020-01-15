package com.boo.datastructure.tree.trie;

public class TrieNode {

	static final int CHAR_SIZE = 26;
	TrieNode[] children;
	boolean isEndOfWord;

	TrieNode() {
		isEndOfWord = false;
		children = new TrieNode[CHAR_SIZE];
	}
}
