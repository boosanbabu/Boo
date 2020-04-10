package com.boo.algorithms.dp;

public class NumberOfSubMatrixWithSum {

	/**
	 * https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/
	 * 
	 * @param args
	 */
	public int numSubmatrixSumTarget(int[][] matrix, int target) {
		int r = matrix.length;
		int c = matrix[0].length;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; ++j) {
				matrix[i][j] = getVal(matrix, i - 1, j) + getVal(matrix, i, j - 1);
			}
		}

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; ++j) {
				matrix[i][j] = getVal(matrix, i - 1, j) + getVal(matrix, i, j - 1);
			}
		}
		return target;
	}

	private int getVal(int[][] matrix, int i, int j) {
		if (i < 1 || j < 1)
			return 0;
		return matrix[i][j];
	}

	public static void main(String[] args) {
		int[][] mat = { { 1, 0, 1 }, { 0, -2, 3 } };
		NumberOfSubMatrixWithSum n = new NumberOfSubMatrixWithSum();

		n.numSubmatrixSumTarget(mat, 0);
	}

}
