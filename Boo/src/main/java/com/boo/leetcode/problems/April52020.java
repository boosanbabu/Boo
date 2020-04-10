package com.boo.leetcode.problems;

import java.util.*;

public class April52020 {
	public static void main(String[] args) {
		April52020 s = new April52020();

	}

	public List<Integer> minSubsequence(int[] nums) {
		int sum = 0;
		List<Integer> list = new ArrayList<>();
		for (int i : nums) {
			list.add(i);
			sum += i;
		}
		Collections.sort(list, Comparator.reverseOrder());
		int halfSum = 0, i = 0;
		for (i = 0; i < list.size(); i++) {
			if (halfSum > sum / 2)
				break;
			halfSum += list.get(i);
		}

		return list.subList(0, i);
	}

	public int numSteps(String s) {
		int[] arr = new int[s.length() + 1];
		arr[0] = 0;
		for (int i = 1; i <= s.length(); i++) {
			arr[i] = Integer.valueOf("" + s.charAt(i - 1));
		}
		int start = 0, end = arr.length - 1, result = 0;
		while (start < end) {
			// If number is "1" break
			if (end == 1 && arr[end] == 1 && arr[start] == 0)
				break;
			result++;
			if (arr[end] == 1) {
				arr[end] = 0;
				moveCarryBit(start, end, arr);
			} else {
				end--;
			}
		}
		return result;
	}

	private void moveCarryBit(int start, int end, int[] arr) {
		for (int i = end - 1; i >= start; i--) {
			if (arr[i] == 0) {
				arr[i] = 1;
				break;
			} else
				arr[i] = 0;
		}
	}

	static boolean isPowerOfTwo(int x) {
		return x != 0 && ((x & (x - 1)) == 0);
	}

	public String longestDiverseString(int a, int b, int c) {
		int arr[] = { a, b, c };
		StringBuffer sb = new StringBuffer();
		while (arr[0] > 0 || arr[1] > 0 || arr[2] > 0) {
			int g = getGreater(arr);
			int l = getLess(arr);
			sb.append(getStringToAppend(arr, g));
			sb.append(getStringToAppend(arr, l));
			System.out.println(g + " " + l);
			System.out.println(sb.toString());
		}
		return null;
	}

	private String getStringToAppend(int[] arr, int g) {
		arr[g]--;
		switch (g) {
		case 0:
			if (arr[g] > 1) {
				arr[g]--;
				return "aa";
			} else
				return "a";
		case 1:
			if (arr[g] > 1) {
				arr[g]--;
				return "bb";
			} else
				return "b";
		case 2:
			if (arr[g] > 1) {
				arr[g]--;
				return "cc";
			} else
				return "c";
		}
		return "";
	}

	private String getSmaller(int[] arr) {
		if (arr[0] > 0 && arr[0] <= arr[1] && arr[0] <= arr[2]) {
			if (arr[0] > 1) {
				arr[0] = arr[0] - 2;
				return "aa";
			}

			else {
				arr[0]--;
				return "aa";
			}
		}
		if (arr[1] > 0 && arr[1] <= arr[0] && arr[1] <= arr[2]) {
			if (arr[1] > 1) {
				arr[1] -= 2;
				return "bb";

			} else {
				arr[1]--;
				return "b";
			}
		}

		if (arr[2] > 0 && arr[2] <= arr[0] && arr[2] <= arr[1]) {
			if (arr[2] > 1) {
				arr[2] -= 2;
				return "cc";
			} else {
				arr[2]--;
				return "c";

			}
		}
		return "";
	}

	private int getGreater(int arr[]) {
		if (arr[0] >= arr[1] && arr[1] > 0 && arr[0] >= arr[2] && arr[2] > 0) {
			return 0;
		}

		if (arr[1] >= arr[0] && arr[0] > 0 && arr[1] >= arr[2] && arr[2] > 0) {
			return 1;
		}
		if (arr[2] >= arr[0] && arr[0] > 0 && arr[2] >= arr[1] && arr[1] > 0) {
			return 2;
		}
		return -1;
	}

	private int getLess(int arr[]) {
		if (arr[0] <= arr[1] && arr[0] <= arr[2]) {
			return 0;
		}

		if (arr[1] <= arr[0] && arr[0] > 0 && arr[1] <= arr[2] && arr[2] > 0) {
			return 1;
		}
		if (arr[2] <= arr[0] && arr[0] > 0 && arr[2] <= arr[1] && arr[1] > 0) {
			return 2;
		}
		return -1;
	}

	public int maxSatisfaction(int[] satisfaction) {
		Arrays.sort(satisfaction);
		int postSum = 0, cur = 0, res = 0;
		for (int i = satisfaction.length - 1; i >= 0; i--) {
			postSum += satisfaction[i];
			cur += postSum;
			res = Math.max(res, cur);
		}
		return res;
	}

	public int countElements(int[] arr) {
		int[] freq = new int[1001];
		for (int i : arr) {
			freq[i]++;
		}
		int res = 0;
		for (int i : arr) {
			if (freq[i + 1] > 0)
				res++;
		}
		return res;
	}

}
