/**
 * 
 */
package com.boo.algorithms.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.boo.CommonUtil;

public class CombinationSum {

	/**
	 * Question : Given a set of candidate numbers (candidates) (without duplicates)
	 * and a target number (target), find all unique combinations in candidates
	 * where the candidate numbers sums to target.
	 * 
	 * The same repeated number may be chosen from candidates unlimited number of
	 * times.
	 *
	 * @see <a href=
	 *      "https://leetcode.com/problems/combination-sum/">CombinationSum</a>
	 */
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> res = new ArrayList<>();
		backtrack(res, candidates, target, new ArrayList<Integer>(), 0);
		return res;
	}

	private void backtrack(List<List<Integer>> res, int[] candidates, int remainingTarget, ArrayList<Integer> temp,
			int start) {
		if (remainingTarget < 0)
			return;
		if (remainingTarget == 0) {
			res.add(new ArrayList<>(temp));
		} else {
			for (int i = start; i < candidates.length; i++) {
				temp.add(candidates[i]);
				backtrack(res, candidates, remainingTarget - candidates[i], temp, i);
				temp.remove(temp.size() - 1);
			}
		}
	}

	/**
	 * Given a collection of candidate numbers (candidates) and a target number
	 * (target), find all unique combinations in candidates where the candidate
	 * numbers sums to target.
	 * 
	 * Each number in candidates may only be used once in the combination.
	 * 
	 * Note:
	 * 
	 * All numbers (including target) will be positive integers. The solution set
	 * must not contain duplicate combinations.
	 * 
	 * @param candidates
	 * @param target
	 * @return
	 */
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(candidates);
		backtrackCs2(res, candidates, target, new ArrayList<Integer>(), 0, new boolean[candidates.length]);
		return res;
	}

	private void backtrackCs2(List<List<Integer>> res, int[] candidates, int remainingTarget, ArrayList<Integer> temp,
			int start, boolean picked[]) {
		if (remainingTarget < 0)
			return;
		if (remainingTarget == 0) {
			res.add(new ArrayList<>(temp));
		} else {
			for (int i = start; i < candidates.length; i++) {
				if (i > 0 && candidates[i - 1] == candidates[i])
					continue;
				temp.add(candidates[i]);
				backtrackCs2(res, candidates, remainingTarget - candidates[i], temp, i + 1, picked);
				temp.remove(temp.size() - 1);
			}
		}
	}

	public static void main(String[] args) {
		CombinationSum cs = new CombinationSum();

		int[] candidates = { 1, 2, 3, 4, 1, 5 };
		int target = 6;
		List<List<Integer>> res = cs.combinationSum2(candidates, target);
		CommonUtil.printListOfList(res);
	}

}
