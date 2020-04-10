package com.boo.leetcode.contests.biweekly.feb22;

import java.util.*;

public class Solution {
	public int[] sortByBits(int[] arr) {
		if (arr.length == 0)
			return arr;
		List<Integer> list = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();

		for (int a : arr) {
			list.add(a);
			map.put(a, countOne(a));
		}
		list.sort((a, b) -> map.get(a) == map.get(b) ? a - b : map.get(a) - map.get(b));
		int i = 0;
		for (int a : list) {
			arr[i++] = a;
		}
		return arr;
	}

	public static int countOne(int number) {
		int count = 0;
		for (int i = 0; i < 32; i++) {
			if ((number & 1) == 1) {
				count++;
			}
			number = number >>> 1;
		}
		return count;
	}

	public int countOrders(int n) {
		List<Integer> events = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			events.add(-i);
			events.add(i);
		}

		int res = backTrack(events, new ArrayList<Integer>(), 0, 0);
		return res;
	}

	private int backTrack(List<Integer> events, ArrayList<Integer> temp, int res, int start) {
		if (temp.size() == events.size())
			return 1;
		for (int i = start; i < events.size(); i++) {
			int ele = events.get(i);
			if (temp.contains(ele))
				continue;
			if (temp.isEmpty() || temp.contains(-1 * ele) || ele < 0) {
				temp.add(ele);
			}
			res += backTrack(events, temp, res, start + 1);

		}
		temp.remove(temp.size() - 1);
		return res;
	}

	public int numberOfSubstringsBrute(String s) {
		String[] test = { "a", "b", "c" };
		for (int i = 0; i < 50000; i++) {
			s += test[i % 3];
		}
		int res = 0, count = 0;
		for (int i = 0; i < s.length() - 2; i++) {
			for (int j = i + 2; j < s.length(); j++) {
				String subStr = s.substring(i, j + 1);
				res++;
				char[] arr = subStr.toCharArray();
				int[] abc = new int[3];
				for (char a : arr) {
					abc[a - 'a']++;
					if (abc[0] > 0 && abc[1] > 0 && abc[2] > 0) {
						count++;
						break;
					}
				}
			}
		}
		System.out.print("c " + count);
		return count;
	}

	public int numberOfSubstrings(String s) {
		int count = 0;
		String[] test = { "a", "b" };
		for (int i = 0; i < 50000; i++) {
			s += test[i % 2];
		}
		s += "c";
		
		for (int i = 0; i < s.length() - 2; i++) {
			boolean found = false;
			for (int j = i + 2; j < s.length(); j++) {
				String subStr = s.substring(i, j + 1);
				char[] arr = subStr.toCharArray();
				int[] abc = new int[3];
				for (char a : arr) {
					abc[a - 'a']++;
					if (abc[0] > 0 && abc[1] > 0 && abc[2] > 0) {
						found = true;
						break;
					}
				}
				if (found) {
					count += s.length() - j;
					break;
				}
			}

		}
		return count;
	}

	public static void main(String[] args) {
		int a[] = { 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1 };
		Solution s = new Solution();
		s.numberOfSubstrings("ab");
	}

}
