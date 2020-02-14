package com.boo.datastructure.tree;

import java.util.Deque;
import java.util.LinkedList;

public class TreeUtil {
	/**
	 * 1, 2, 3, 4, 5, 6, 7, 8, 9
	 * 
	 * @return Root of the above tree
	 */
	public static TreeNode sampleTree() {
		return constructTreeFromLevelOrder(new Integer[] { 6, 4, -3, 1, 1, 2, 5, 6, -1, -7, 3, 5, 3, -4, 5, -2 });
	}

	/**
	 * 6, 3, 9, 2, 5, 8, 10, 1
	 * 
	 * @return
	 */
	public static TreeNode sampleBST() {
		return constructTreeFromLevelOrder(new Integer[] { 6, 3, 9, 2, 5, 8, 10, 1 });
	}

	public static TreeNode constructTreeFromLevelOrder(Integer... integers) {
		if (integers.length == 0)
			return null;

		TreeNode root = new TreeNode(integers[0]);
		Deque<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		int i = 0;
		while (!q.isEmpty()) {
			TreeNode curr = q.peek();
			if (i + 1 < integers.length) {
				TreeNode left = new TreeNode(integers[++i]);
				curr.left = left;
				q.offer(left);
			}
			if (i + 1 < integers.length) {
				TreeNode right = new TreeNode(integers[++i]);
				curr.right = right;
				q.offer(right);
			}
			q.remove();
		}
		return root;
	}

	public static void printLevelOrder(TreeNode root) {
		int h = height(root);
		int i;
		for (i = 1; i <= h; i++) {
			System.out.println();
			printGivenLevel(root, i);
		}
	}

	/*
	 * Compute the "height" of a tree -- the number of nodes along the longest path
	 * from the root node down to the farthest leaf node.
	 */
	static int height(TreeNode root) {
		if (root == null)
			return 0;
		else {
			/* compute height of each subtree */
			int lheight = height(root.left);
			int rheight = height(root.right);

			/* use the larger one */
			if (lheight > rheight)
				return (lheight + 1);
			else
				return (rheight + 1);
		}
	}

	/* Print nodes at the given level */
	static void printGivenLevel(TreeNode root, int level) {
		if (root == null)
			return;
		if (level == 1)
			System.out.print(root.val + " ");
		else if (level > 1) {
			printGivenLevel(root.left, level - 1);
			printGivenLevel(root.right, level - 1);
		}
	}

}
