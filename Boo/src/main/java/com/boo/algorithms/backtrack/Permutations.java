/**
 * 
 */
package com.boo.algorithms.backtrack;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.CommonDataSource;

import com.boo.CommonUtil;

/**
 * Question : Given a collection of distinct integers, return all possible
 * permutations.
 *
 * 
 * 
 * @see <a href="https://leetcode.com/problems/permutations/">Permutations -
 *      Leetcode</a>
 */
public class Permutations {
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		backtrack(nums, result, new ArrayList<Integer>());
		return result;
	}

	private void backtrack(int[] nums, List<List<Integer>> result, List<Integer> temp) {
		if (temp.size() == nums.length) {
			result.add(new ArrayList<>(temp));
			return;
		}
		for (int i = 0; i < nums.length; i++) {
			if (temp.contains(nums[i])) {
				continue;
			}
			temp.add(nums[i]);
			backtrack(nums, result, temp);
			temp.remove(temp.size() - 1);
		}
	}

	public static void main(String[] args) {
		Permutations pm = new Permutations();
		int nums[] = { 1, 2, 3 };
		List<List<Integer>> res = pm.permute(nums);
		CommonUtil.printListOfList(res);
	}

}
