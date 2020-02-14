package com.boo.datastructure.tree.trie;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

class StreamChecker {

	TrieNode root;
	Deque<Character> lastKQueries = new ArrayDeque<>();
	int inputStrMaxLen = 0;

	static class TrieNode {
		TrieNode[] children;
		boolean isEndOfWord;

		TrieNode() {
			children = new TrieNode[26];
			isEndOfWord = false;
		}

		void setEndOfWord() {
			isEndOfWord = true;
		}
	}

	public StreamChecker(String[] words) {
		root = new TrieNode();
		for (String w : words) {
			insert(w);
		}
	}

	/*
	 * Insert words on reverse
	 */
	public void insert(String w) {
		int len = w.length();
		inputStrMaxLen = Math.max(inputStrMaxLen, len);
		TrieNode curr = root;
		for (int i = 0; i < len; i++) {
			int idx = w.charAt(len - i - 1) - 'a';
			if (curr.children[idx] == null) {
				curr.children[idx] = new TrieNode();
			}
			curr = curr.children[idx];
		}
		curr.setEndOfWord();
	}

	public boolean query(char letter) {
		if (lastKQueries.size() > inputStrMaxLen) {
			lastKQueries.removeFirst();
		}
		lastKQueries.add(letter);
		TrieNode ptr = root;
		Iterator<Character> i = lastKQueries.descendingIterator();
		while (i.hasNext() && ptr != null) {
			Character c = i.next();
			ptr = ptr.children[c - 'a'];

			if (ptr != null && ptr.isEndOfWord) {
				return true;
			}
		}
		return false;
	}

	public static void main(String a[]) {
		String[] words = { "boo", "roo", "root" };
		StreamChecker stC = new StreamChecker(words);
		System.out.println(stC.query('b'));
		System.out.println(stC.query('r'));
		System.out.println(stC.query('o'));
		System.out.println(stC.query('o'));
		System.out.println(stC.query('t'));
		System.out.println(stC.query('o'));
	}
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words); boolean param_1 =
 * obj.query(letter);
 */