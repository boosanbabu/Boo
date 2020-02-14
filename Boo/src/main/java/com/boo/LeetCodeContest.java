package com.boo;

import java.util.*;

import com.boo.datastructure.tree.TreeNode;
import com.boo.datastructure.tree.TreeUtil;

public class LeetCodeContest {
	public int maximum69Number(int num) {
		Deque<Integer> digits = new LinkedList<>();
		boolean changed = false;
		int n = num;
		while (n > 0) {
			digits.addFirst(n % 10);
			n = n / 10;
		}

		n = 0;
		for (int d : digits) {
			if (!changed && d == 6) {
				changed = true;
				d = 9;
			}
			n = n * 10 + d;
		}
		return n;
	}

	public List<String> printVertically(String s) {
		String[] words = s.split(" ");
		int maxLen = 0;
		for (String w : words) {
			maxLen = Math.max(maxLen, w.length());
		}
		List<String> res = new ArrayList<>();

		for (int i = 0; i < maxLen; i++) {
			StringBuffer sb = new StringBuffer();
			for (String w : words) {
				sb.append(getChar(w, i));
			}
			res.add(sb.toString().replaceFirst("\\s++$", ""));
		}
		return res;
	}

	private char getChar(String s, int i) {
		if (i > s.length() - 1)
			return ' ';
		return s.charAt(i);
	}

	public TreeNode removeLeafNodes(TreeNode root, int target) {
		if (root == null)
			return null;

		root.left = removeLeafNodes(root.left, target);
		root.right = removeLeafNodes(root.right, target);
		if (root.val == target && root.right == null && root.left == null)
			return null;
		return root;
	}

	public int minTaps(int n, int[] ranges) {
		List<Integer[]> rangesCovered = new ArrayList<Integer[]>();
		for (int i = 0; i < n + 1; i++) {
			rangesCovered.add(new Integer[] { i - ranges[i], i + ranges[i] });
		}
		rangesCovered.forEach(i -> System.out.println(i[0] + " " + i[1]));
		return n;
	}

	public static void main(String[] args) {
		LeetCodeContest l = new LeetCodeContest();
		int n = 5;
		int[] ranges = { 3, 4, 1, 1, 0, 0 };
		l.minTaps(n, ranges);
	}

}
