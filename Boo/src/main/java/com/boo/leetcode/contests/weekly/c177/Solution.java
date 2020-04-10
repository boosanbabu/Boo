package com.boo.leetcode.contests.weekly.c177;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Solution {

	public static void main(String[] args) {
		Solution s = new Solution();
		int[] leftChild = { -1, 0, -1, 1, 3 };
		int[] rightChild = { 2, -1, -1, -1, 2 };
		System.out.println(s.validateBinaryTreeNodes(5, leftChild, rightChild));
	}

	public int daysBetweenDates(String date1, String date2) {
		Date d1 = null, d2 = null;
		try {
			d1 = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
			d2 = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		long diffInMillies = Math.abs(d1.getTime() - d2.getTime());
		long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		return (int) Math.abs(diff);
	}

	static class NodeDetails {
		int leftIndegree, rightIndegree, leftOutDegree, rightOutDegree;

		public NodeDetails() {
			leftIndegree = 0;
			rightIndegree = 0;
			rightOutDegree = 0;
			leftOutDegree = 0;
		}

		public int totalIndegree() {
			return leftIndegree + rightIndegree;
		}
	}

	public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
		NodeDetails[] nodes = new NodeDetails[n];
		for (int i = 0; i < n; i++) {
			nodes[i] = new NodeDetails();
		}
		for (int i = 0; i < n; i++) {
			if (leftChild[i] != -1) {
				nodes[i].leftOutDegree++;
				nodes[leftChild[i]].leftIndegree++;
			}
			if (rightChild[i] != -1) {
				nodes[i].rightOutDegree++;
				nodes[rightChild[i]].rightIndegree++;
			}
		}

		int rootCount = 0;
		for (NodeDetails node : nodes) {
			if (node.totalIndegree() == 0) {
				rootCount++;
			}
			if (node.totalIndegree() > 1 || node.leftOutDegree > 1 || node.rightOutDegree > 1)
				return false;
		}
		return rootCount == 1;
	}

	public int[] closestDivisors(int num) {
		int sqrt = (int) Math.sqrt(num + 2);
		int[] res = new int[2];
		int diff1 = Integer.MAX_VALUE, diff = Integer.MAX_VALUE;
		for (int i = sqrt; i >= 1; i--) {
			int N = num + 1;
			if (N % i == 0) {
				diff1 = Math.abs(i - N / i);
			}

			if (diff > diff1) {
				res[0] = i;
				res[1] = N / i;
				diff = diff1;
			}

			int N2 = num + 2;
			if (N2 % i == 0) {
				diff1 = Math.abs(i - N2 / i);
			}

			if (diff > diff1) {
				res[0] = i;
				res[1] = N2 / i;
				diff = diff1;
			}
		}

		System.out.println(res[0] + " " + res[1]);
		return res;
	}

	public String largestMultipleOfThree(int[] digits) {
		Arrays.sort(digits);
		int sum = 0;
		Queue<Integer> q0 = new LinkedList<>();
		Queue<Integer> q1 = new LinkedList<>();
		Queue<Integer> q2 = new LinkedList<>();

		for (int d : digits) {
			if (d % 3 == 0) {
				q0.add(d);
			}
			if (d % 3 == 1) {
				q1.add(d);
			}
			if (d % 3 == 2) {
				q2.add(d);
			}
			sum += d;
		}

		if (sum % 3 == 1) {
			if (!q1.isEmpty()) {
				q1.remove();
			} else {
				if (!q2.isEmpty()) {
					q2.remove();
				} else
					return "";
				if (!q2.isEmpty()) {
					q2.remove();
				} else {
					return "";
				}
			}
		}

		if (sum % 3 == 2) {
			if (!q2.isEmpty()) {
				q2.remove();
			} else {
				if (!q1.isEmpty()) {
					q1.remove();
				} else {
					return "";
				}
				if (!q1.isEmpty()) {
					q1.remove();
				} else {
					return "";
				}
			}
		}

		List<Integer> list = new ArrayList<>();
		fillList(q0, q1, q2, list);
		String res = "";
		if (list.get(0) == 0)
			return "0";
		for (int i = list.size() - 1; i >= 0; i--) {
			res += list.get(i);
		}
		return res;
	}

	private void fillList(Queue<Integer> q0, Queue<Integer> q1, Queue<Integer> q2, List<Integer> list) {
		while (!q0.isEmpty())
			list.add(q0.remove());
		while (!q1.isEmpty())
			list.add(q1.remove());
		while (!q2.isEmpty())
			list.add(q2.remove());
		list.sort((a, b) -> a - b);
	}

}
