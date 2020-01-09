/**
 * 
 */
package com.boo.algorithms.backtrack;

import java.util.ArrayList;
import java.util.List;

import com.boo.CommonUtil;

/**
 * Question : Given two integers n and k, return all possible combinations of k
 * numbers out of 1 ... n.
 *
 * 
 * 
 * @see <a href="https://leetcode.com/problems/combinations/">Combinations</a>
 */
public class Combinations {
	public static List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> res = new ArrayList<>();
		combinations(res, k, new ArrayList<Integer>(), 1, n - k + 1);
		return res;
	}

	private static void combinations(List<List<Integer>> res, int spaceLeft, ArrayList<Integer> temp, int from,
			int to) {
		if (spaceLeft == 0)
			res.add(new ArrayList<>(temp));
		else
			for (int i = from; i <= to; i++) {
				temp.add(i);
				combinations(res, spaceLeft - 1, temp, i + 1, to + 1);
				temp.remove(temp.size() - 1);
			}
	}

	public static void main(String[] args) {
		List<List<Integer>> res = Combinations.combine(9, 4);
		CommonUtil.printListOfList(res);
	}

}
