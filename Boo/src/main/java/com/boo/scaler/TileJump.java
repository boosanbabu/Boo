package com.boo.scaler;

import java.util.Arrays;

public class TileJump {
	public int solveTemp(int A, final int[][] B) {
		return maximize(A, B, 0);
	}

	public int maximize(int A, int B[][], int row) {
		if (row >= B.length)
			return 0;
		int x = maximize(A, B, row, 0);
		int y = maximize(A, B, row, 1);
		int z = maximize(A, B, row, 2);
		return Math.max(Math.max(x, y), z);
	}

	public int maximize(int A, int[][] B, int row, int col) {
		if (row >= B.length)
			return 0;
		int res = 0;
		if (col == 0) {
			res = B[row][col] + maximize(A, B, row + 1);
		} else if (col == 1) {
			res = B[row][col] + maximize(A, B, row + 2);
		} else {
			res = B[row][col] + maximize(A, B, row + 3);
		}
		return res;
	}

	public int solve(int A, final int[][] B) {
		if (B.length == 0)
			return 0;
		int[] memo = new int[B.length];
		Arrays.fill(memo, -1001);
		return maximizeRow(A, B, 0, 0, memo);
	}

	public int maximizeRow(int A, int B[][], int row, int sum, int[] memo) {
		if (row >= B.length)
			return sum;
		if (memo[row] != -1001)
			return memo[row];
		int x = maximizeCol(A, B, row, 0, memo, sum);
		int y = maximizeCol(A, B, row, 1, memo, sum);
		int z = maximizeCol(A, B, row, 2, memo, sum);
		memo[row] = Math.max(Math.max(x, y), z);
		return memo[row];
	}

	public int maximizeCol(int A, int[][] B, int row, int col, int[] memo, int sum) {
		if (row >= B.length)
			return 0;
		int res = 0;
		if (col == 0) {
			res = B[row][col] + maximizeRow(A, B, row + 1, sum, memo);
		} else if (col == 1) {
			res = B[row][col] + maximizeRow(A, B, row + 2, sum, memo);
		} else {
			res = B[row][col] + maximizeRow(A, B, row + 3, sum, memo);
		}
		return res;
	}

	public static void main(String[] args) {
		TileJump t = new TileJump();
		int[][] B = { { 11, 12, 133 }, { 1, 2, 3 }, { 5, 6, 7 }, { 7, 9, 11 }, { 1, 2, 3 }, { 9, 10, 11 },
				{ 11, 12, 133 }, { 1, 2, 3 }, { 5, 6, 7 }, { 7, 9, 11 }, { 1, 2, 3 }, { 9, 10, 11 }, { 11, 12, 133 },
				{ 1, 2, 3 }, { 5, 6, 7 }, { 7, 9, 11 }, { 1, 2, 3 }, { 5, 6, 7 }, { 7, 9, 19 }, { 7, 9, 11 } };
		System.out.println(B.length);
		long start = System.currentTimeMillis();
		System.out.println(t.solve(B.length, B));
		System.out.println(System.currentTimeMillis() - start);

		start = System.currentTimeMillis();
		System.out.println(t.solveTemp(B.length, B));
		System.out.println(System.currentTimeMillis() - start);

	}

}
