package com.boo.algorithms.dp;

public class MaxLenEqualSubarray {
	public int findLength(int[] A, int[] B) {
		int[][] dp = new int[A.length + 1][B.length + 1];
		int n = A.length, m = B.length;
		int ans = Integer.MIN_VALUE;
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < m + 1; j++) {
				if (A[i - 1] == B[j - 1]) {
					dp[i][j] = 1 + dp[i - 1][j - 1];
					ans = Math.max(ans, dp[i][j]);
				}
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		int a[] = { 3, 2, 1, 4, 7, 2, 5 };
		int b[] = { 1, 2, 3, 2, 1, 4, 4, 7, 2, 5 };
		MaxLenEqualSubarray m = new MaxLenEqualSubarray();

		System.out.println(m.findLength(a, b));

	}

}
