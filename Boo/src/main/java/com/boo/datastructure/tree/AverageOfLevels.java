package com.boo.datastructure.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * Question : Given a non-empty binary tree, return the average value of the
 * nodes on each level in the form of an array.
 *
 * @see <a href=
 *      "https://leetcode.com/problems/average-of-levels-in-binary-tree/">AverageOfLevels
 *      - Leetcode</a>
 */
public class AverageOfLevels {

	public List<Integer> averageOfLevels(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		Deque<TreeNode> q = new LinkedList<>();
		q.add(root);
		while (!q.isEmpty()) {
			int size = q.size();
			int sum = 0;
			for (int i = 0; i < size; ++i) {
				TreeNode curr = q.remove();
				if (curr.left != null) {
					q.offer(curr.left);
				}
				if (curr.right != null) {
					q.offer(curr.right);
				}
				sum += curr.val;
			}
			result.add(sum / size);
		}
		return result;
	}

	public static void main(String[] args) {
		TreeNode tree = TreeUtil.constructTreeFromLevelOrder(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 14, 15);
		AverageOfLevels av = new AverageOfLevels();
		List<Integer> result = av.averageOfLevels(tree);
		result.containsAll(Arrays.asList(1, 3, 5, 9));

		tree = TreeUtil.constructTreeFromLevelOrder(9, 10, 11, 14, 15, 1, 2, 3, 4, 5, 6, 7, 8);
		result = av.averageOfLevels(tree);
		result.containsAll(Arrays.asList(9, 11, 16, 6));

		tree = TreeUtil.constructTreeFromLevelOrder(9);
		result = av.averageOfLevels(tree);
		result.containsAll(Arrays.asList(9));

		tree = TreeUtil.constructTreeFromLevelOrder(9, 10);
		result = av.averageOfLevels(tree);
		result.containsAll(Arrays.asList(9, 10));

		tree.left.left = new TreeNode(11);
		tree.left.left.left = new TreeNode(12);
		result = av.averageOfLevels(tree);
		result.containsAll(Arrays.asList(9, 10, 11, 12));

	}

}
