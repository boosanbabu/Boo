package com.boo.datastructure.tree.trie;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReplaceWords {

	class TrieNode {
		TrieNode[] children;
		boolean isEndOfWord;

		TrieNode() {
			isEndOfWord = false;
			children = new TrieNode[26];
		}

	}

	class Dictionary {
		TrieNode root;

		Dictionary() {
			root = new TrieNode();
		}

		public void insert(String word) {
			TrieNode ptr = root;
			for (int i = 0; i < word.length(); i++) {
				if (ptr.isEndOfWord)
					return;
				int idx = word.charAt(i) - 'a';
				if (ptr.children[idx] == null) {
					ptr.children[idx] = new TrieNode();
				}
				ptr = ptr.children[idx];
			}
			ptr.isEndOfWord = true;
		}

		/* Returns root word if exist or the word itself */
		public String getRootWord(String word) {
			TrieNode ptr = root;
			int i = 0;
			for (i = 0; i < word.length(); i++) {
				int idx = word.charAt(i) - 'a';
				if (ptr.children[idx] == null)
					return word;
				ptr = ptr.children[idx];
				if (ptr.isEndOfWord)
					return word.substring(0, i + 1);
			}

			return word;
		}
	}

	public String replaceWords(List<String> dict, String sentence) {
		Dictionary d = new Dictionary();
		for (int i = 0; i < dict.size(); i++) {
			d.insert(dict.get(i));
		}

		String[] words = sentence.split(" ");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < words.length; i++) {
			sb.append(d.getRootWord(words[i]));
			if (i < words.length - 1)
				sb.append(" ");
		}

		return sb.toString();
	}

	public String replaceWordsUsingStartsWith(List<String> dict, String sentence) {
		StringBuffer res = new StringBuffer();
		String wordsInSentence[] = sentence.split(" ");
		Collections.sort(dict, (a, b) -> a.length() - b.length());
		for (int i = 0; i < wordsInSentence.length; i++) {
			boolean hasRootWord = false;
			String word = wordsInSentence[i];
			for (String root : dict) {
				if (word.startsWith(root)) {
					hasRootWord = true;
					res.append(root);
					break;
				}
			}
			if (!hasRootWord) {
				res.append(word);
			}
			if (i != wordsInSentence.length - 1)
				res.append(" ");
		}
		return res.toString();
	}

	public static void main(String s[]) {
		ReplaceWords rp = new ReplaceWords();

		List<String> dict = Arrays.asList(new String[] { "cat", "bat", "rat" });
		String sentence = "the cattle was rattled by the battery";
		System.out.println(rp.replaceWords(dict, sentence));
	}
}