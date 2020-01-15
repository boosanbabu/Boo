/**
 * 
 */
package com.boo.datastructure.tree;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Question : You are given a binary tree in which each node contains an integer
 * value.
 * 
 * Find the number of paths that sum to a given value.
 * 
 * The path does not need to start or end at the root or a leaf, but it must go
 * downwards (traveling only from parent nodes to child nodes).
 * 
 * The tree has no more than 1,000 nodes and the values are in the range
 * -1,000,000 to 1,000,000.
 * 
 * 
 *
 * @see <a href= "https://leetcode.com/problems/path-sum-iii/">Path Sum III</a>
 */
public class PathSumIII {

	public int pathSum(TreeNode root, int sum) {
		Map<Integer, Integer> preSum = new LinkedHashMap<>();
		preSum.put(0, 1);
		int r =  helper(root, 0, sum, preSum);
		return r;
	}

	public int helper(TreeNode root, int currSum, int target, Map<Integer, Integer> preSum) {
		if (root == null) {
			return 0;
		}

		currSum += root.val;
		int res = preSum.getOrDefault(currSum - target, 0);
		preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);

		res += helper(root.left, currSum, target, preSum) + helper(root.right, currSum, target, preSum);
		preSum.put(currSum, preSum.get(currSum) - 1);
		return res;
	}

	public static void main(String[] args) {
		PathSumIII ps = new PathSumIII();
		TreeNode root = TreeUtil.sampleTree();
		System.out.println(ps.pathSum(root, 10));
	}

}
