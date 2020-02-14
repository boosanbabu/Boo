package com.boo.algorithms.backtrack;

import java.util.*;

public class Subsets {
	public ArrayList<ArrayList<Integer>> subsets(List<Integer> set) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		int k = 1 << set.size();
		for (int i = 0; i < k; i++) {
			ArrayList<Integer> subset = new ArrayList<>();
			subset = convertlntToSet(k, set);
			result.add(subset);
		}
		return result;
	}

	ArrayList<Integer> convertlntToSet(int x, List<Integer> set) {
		ArrayList<Integer> subset = new ArrayList<>();
		int index = -1;
		for (int i = x; i > 0; i = i >> 1) {
			if ((i & 1) == 1) {
				subset.add(set.get(index));
			}
			index++;
		}
		return subset;
	}

	public static void main(String[] args) {
		Subsets s = new Subsets();
		List<Integer> set = Arrays.asList(new Integer[] { 1, 2, 3 });
		s.subsets(set);

	}
}
