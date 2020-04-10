package com.boo.leetcode.contests.weekly;

import java.util.*;
import java.util.Map.Entry;

import com.boo.datastructure.tree.TreeNode;
import com.boo.datastructure.tree.TreeUtil;

class Feb022020 {
	public int[] kWeakestRows(int[][] mat, int k) {
		if (k == 0 || mat.length == 0)
			return new int[0];
		Map<Integer, Integer> soldiers = new HashMap<>();
		for (int i = 0; i < mat.length; i++) {
			int s = 0;
			for (int j = 0; j < mat[0].length; j++) {
				if (mat[i][j] == 0)
					break;
				s++;
			}
			soldiers.put(i, s);
		}

		ArrayList<Entry<Integer, Integer>> soldiersList = new ArrayList<>(soldiers.entrySet());
		Collections.sort(soldiersList,
				(a, b) -> a.getValue() == b.getValue() ? a.getKey() - b.getKey() : a.getValue() - b.getValue());
		int res[] = new int[k];
		for (int i = 0; i < k; i++) {
			res[i] = soldiersList.get(i).getKey();
		}
		return res;
	}

	public int minSetSize(int[] arr) {
		if (arr.length == 0)
			return 0;
		int halfSize = arr.length / 2;
		Map<Integer, Integer> freqMap = new HashMap<>();
		for (int i : arr) {
			freqMap.putIfAbsent(i, 0);
			freqMap.put(i, freqMap.get(i) + 1);
		}
		ArrayList<Entry<Integer, Integer>> eList = new ArrayList<>(freqMap.entrySet());
		Collections.sort(eList, (a, b) -> b.getValue() - a.getValue());
		int ptr = 0, c = 1;
		for (Entry<Integer, Integer> e : eList) {
			if (ptr + e.getValue() >= halfSize) {
				break;
			}
			ptr += e.getValue();
			c++;
		}
		return c;
	}

	static class MaxProductWrapper {
		long res = 0l;

		int getRes() {
			return (int) (res % 1000000007);
		}
	}

	public int maxProduct(TreeNode root) {
		int totalSumOfTree = buildSumTree(root);
		MaxProductWrapper maxProd = new MaxProductWrapper();
		findMaxElementInTree(root, maxProd, totalSumOfTree);
		return maxProd.getRes();
	}

	private void findMaxElementInTree(TreeNode root, MaxProductWrapper maxProd, int totalSum) {
		if (root != null) {
			maxProd.res = Math.max((totalSum - root.val) * root.val, maxProd.res);
			findMaxElementInTree(root.left, maxProd, totalSum);
			findMaxElementInTree(root.right, maxProd, totalSum);
		}
	}

	private int buildSumTree(TreeNode root) {
		if (root == null) return 0;
		if (root.left == null && root.right == null) return root.val;
		root.val += buildSumTree(root.left) + buildSumTree(root.right);
		return root.val;
	}

	public static void main(String a[]) {
		int[][] mat = { { 1, 1, 0, 0, 0 }, { 1, 1, 1, 1, 0 }, { 1, 0, 0, 0, 0 }, { 1, 1, 0, 0, 0 }, { 1, 1, 1, 1, 1 } };
		Feb022020 s = new Feb022020();

		TreeNode root = TreeUtil.constructTreeFromLevelOrder(2, 3, 9, 10, 7, 8, 6, 5, 4, 11, 1);
		System.out.println(s.maxProduct(root));

		root = new TreeNode(1);
		TreeNode two = new TreeNode(2);
		TreeNode t3 = new TreeNode(3);
		TreeNode t4 = new TreeNode(4);
		TreeNode t5 = new TreeNode(5);
		TreeNode t6 = new TreeNode(6);
		root.right = two;
		two.left = t3;
		two.right = t4;
		t4.left = t5;
		t4.right = t6;

		System.out.println(s.maxProduct(root));
	}
}