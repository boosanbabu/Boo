package com.boo.algorithms.dp;

import java.util.*;

import com.boo.datastructure.tree.TreeNode;

public class HouseRobber {
	public int rob(TreeNode root) {
		return robMemoize(root, new HashMap<>());
	}

	public int robMemoize(TreeNode root, Map<TreeNode, Integer> map) {
		if (root == null) return 0;
		if (map.containsKey(root)) {
			return map.get(root);
		}
		int sum = root.val;
		if (root.left != null) {
			sum += robMemoize(root.left.left, map) + robMemoize(root.left.right, map);
		}
		if (root.right != null) {
			sum += robMemoize(root.right.right, map) + robMemoize(root.right.left, map);
		}
		int res = Math.max(sum, robMemoize(root.left, map) + robMemoize(root.right, map));
		map.put(root, res);
		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
