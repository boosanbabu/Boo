package com.boo;

/**
 * Hello world!
 *
 */
public class App {
	public int minFallingPathSum(int[][] arr) {

		int r = arr.length;
		int c = arr[0].length;

		int dp[][] = new int[r][c];
		for (int i = 0; i < c; i++) {
			dp[0][i] = arr[0][i];
		}
		for (int i = 1; i < r; i++) {
			for (int j = 0; j < c; j++) {
				int min = Integer.MAX_VALUE;
				for (int k = 0; k < c; k++) {
					if (k == j)
						continue;
					min = Math.min(min, dp[i - 1][k]);
				}
				dp[i][j] = arr[i][j] + min;
			}
		}
		int result = dp[r - 1][0];
		for (int i = 1; i < c; ++i) {
			result = Math.min(result, dp[r - 1][i]);
		}
		return result;
	}

	public static void main(String aa[]) {
		int[][] arr = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		App a = new App();
		a.minFallingPathSum(arr);
	}
}
