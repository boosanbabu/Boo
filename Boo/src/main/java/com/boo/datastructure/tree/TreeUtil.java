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

}
