package com.boo.datastructure.tree.trie;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class MapSum {

	TrieNode root;

	class TrieNode {
		Map<Character, TrieNode> children;
		boolean isEndOfWord;
		int weight;

		TrieNode() {
			children = new HashMap<>();
			isEndOfWord = false;
			weight = 0;
		}
	}

	public MapSum() {
		root = new TrieNode();
	}

	public void insert(String key, int val) {
		TrieNode ptr = root;
		int len = key.length();
		for (int i = 0; i < len; i++) {
			if (ptr.children.get(key.charAt(i)) == null) {
				ptr.children.put(key.charAt(i), new TrieNode());
			}
			ptr = ptr.children.get(key.charAt(i));
		}
		ptr.isEndOfWord = true;
		ptr.weight = val;
	}

	public int sum(String prefix) {
		TrieNode ptr = root;
		int len = prefix.length();
		for (int i = 0; i < len; i++) {
			if (ptr.children.get(prefix.charAt(i)) == null)
				return 0;
			ptr = ptr.children.get(prefix.charAt(i));
		}
		return bfs(ptr);
	}

	private int bfs(TrieNode ptr) {
		int res = 0;
		Deque<TrieNode> q = new LinkedList<>();
		if (ptr != null)
			q.offer(ptr);

		while (!q.isEmpty()) {
			TrieNode k = q.poll();
			if (k.isEndOfWord)
				res += k.weight;

			for (Character c : k.children.keySet()) {
				q.offer(k.children.get(c));
			}
		}
		return res;
	}

	public static void main(String a[]) {
		MapSum map = new MapSum();
		map.insert("apple", 3);
		map.insert("apply", 1);
		map.insert("app", 2);
		System.out.println(map.sum("app"));

	}
}
