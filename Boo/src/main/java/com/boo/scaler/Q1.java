package com.boo.scaler;

import java.util.*;

public class Q1 {
	public int solve(ArrayList<String> A, String B) {
		Map<Character, Integer> targetFreqMap = makeFreqMap(B);
		List<Integer> powers = new ArrayList<>();
		for (String s : A) {
			Map<Character, Integer> currentfreqMap = makeFreqMap(s);
			int currPow = getPow(currentfreqMap, targetFreqMap);
			powers.add(currPow);
		}
		Collections.sort(powers);
		int minDiff = Integer.MAX_VALUE;
		int prev = powers.get(0);
		for (int i = 1; i < powers.size(); i++) {
			int current = powers.get(1);
			int currDiff = Math.abs(current - prev);
			minDiff = Math.min(minDiff, currDiff);
			prev = current;
		}
		return minDiff;
	}

	public Map<Character, Integer> makeFreqMap(String s) {
		Map<Character, Integer> result = new HashMap<>();
		for (Character ch : s.toCharArray()) {
			Integer count = result.getOrDefault(ch, 0);
			count = count + 1;
			result.put(ch, count);
		}
		return result;
	}

	public int getPow(Map<Character, Integer> s, Map<Character, Integer> target) {
		int pow = 0;
		for (Character ch : target.keySet()) {
			int pCh = target.get(ch);
			if (s.containsKey(ch)) {
				pow = pow + s.get(ch) * pCh;
			}
		}
		return pow;
	}
}
