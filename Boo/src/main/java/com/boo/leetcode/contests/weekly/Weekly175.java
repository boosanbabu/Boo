package com.boo.leetcode.contests.weekly;

import java.util.*;
import java.util.Map.Entry;

public class Weekly175 {
	public boolean checkIfExist(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (i != j && arr[i] == arr[j] * 2)
					return true;
			}
		}
		return false;
	}

	public int minSteps(String s, String t) {
		Map<Character, Integer> sMap = new HashMap<>();
		Map<Character, Integer> tMap = new HashMap<>();

		for (int i = 0; i < s.length(); i++) {
			sMap.put(s.charAt(i), sMap.getOrDefault(s.charAt(i), 0) + 1);
		}

		for (int i = 0; i < t.length(); i++) {
			tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
		}
		int c = 0;
		for (Entry<Character, Integer> tEntry : tMap.entrySet()) {
			Character k = tEntry.getKey();
			Integer v = tEntry.getValue();
			if (!sMap.containsKey(k))
				c = c + v;
			else {
				int diff = v - sMap.get(k);
				if (diff > 0)
					c = c + diff;
			}
		}

		return c;
	}

	public static void main(String[] args) {
		Weekly175 w = new Weekly175();
		System.out.println(w.minSteps("ripara", "ripipr"));
		System.out.println(w.minSteps("family", "friend"));
		System.out.println(w.minSteps("abb", "aab"));
		System.out.println(w.minSteps("ram", "sna"));
	}

}
