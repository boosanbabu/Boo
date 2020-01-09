/**
 * 
 */
package com.boo.algorithms.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Question : Given a collection of numbers that might contain duplicates,
 * return all possible unique permutations.
 *
 * @see <a href="https://leetcode.com/problems/permutations-ii/">Leetcode</a>
 */
public class Permutations2 {
	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(nums);
		backTrack(nums, result, new boolean[nums.length], new ArrayList<Integer>());
		return result;

	}

	private void backTrack(int[] inp, List<List<Integer>> result, boolean[] used, ArrayList<Integer> temp) {
		if (temp.size() == inp.length) {
			result.add(new ArrayList<>(temp));
			return;
		}

		for (int i = 0; i < inp.length; i++) {
			if (used[i] || (i > 0 && inp[i - 1] == inp[i] && !used[i - 1]))
				continue;

			temp.add(inp[i]);
			used[i] = true;
			backTrack(inp, result, used, temp);
			used[i] = false;
			temp.remove(temp.size() - 1);
		}
	}

	public static void main(String[] args) {
		Permutations2 p2 = new Permutations2();
		int nums[] = { 1, 2, 1, 2, 3 };
		List<List<Integer>> res = p2.permuteUnique(nums);
		res.forEach(list -> {
			list.forEach(System.out::print);
			System.out.println();
		});
	}

}
