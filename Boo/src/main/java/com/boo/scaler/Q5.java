package com.boo.scaler;

import java.util.*;

public class Q5 {

	public int solveA(String A) {
		int result = Integer.MIN_VALUE;
		int left_pointer = 0;
		int right_pointer = 0;
		Map<Character, Integer> chVsOccurences = new HashMap<>();
		chVsOccurences.put(A.charAt(0), 1);
		while (true) {
			if (isFreqMapValid(chVsOccurences)) {
				result = Math.max(result, (right_pointer - left_pointer) + 1);
			}
			if (isFreqMapValid(chVsOccurences)) {
				right_pointer++;
				if (right_pointer > A.length() - 1) {
					right_pointer--;
					break;
				}
				int count = chVsOccurences.getOrDefault(A.charAt(right_pointer), 0);
				chVsOccurences.put(A.charAt(right_pointer), count + 1);
			} else {
				int count = chVsOccurences.get(A.charAt(left_pointer));
				chVsOccurences.put(A.charAt(left_pointer), count - 1);
				left_pointer++;
			}
		}
		result = Math.max(result, (right_pointer - left_pointer) + 1);
		return result;
	}

	public boolean isFreqMapValidA(Map<Character, Integer> chVsOccurences) {
		for (Character ch : chVsOccurences.keySet()) {
			if (chVsOccurences.get(ch) > 2)
				return false;
		}
		return true;
	}

	public int solve(String A) {

		int result = Integer.MIN_VALUE;
		int left = 0;
		int right = 0;
		Map<Character, Integer> freqMap = new HashMap<>();
		freqMap.put(A.charAt(0), 1);

		while (true) {
			if (isFreqMapValid(freqMap)) {
				result = Math.max(result, (right - left) + 1);
			}
			if (isFreqMapValid(freqMap)) {
				right++;

				if (right > A.length() - 1) {
					right--;
					break;
				}
				int count = freqMap.getOrDefault(A.charAt(right), 0);

				freqMap.put(A.charAt(right), count + 1);
			} else {
				int count = freqMap.get(A.charAt(left));
				freqMap.put(A.charAt(left), count - 1);
				left++;
			}
		}
		result = Math.max(result, (right - left) + 1);
		return result;
	}

	public boolean isFreqMapValid(Map<Character, Integer> freqMap) {
		for (Character ch : freqMap.keySet()) {
			if (freqMap.get(ch) > 2)
				return false;
		}
		return true;
	}
}
