package com.boo.algorithms.dp;

public class RobotTravel {

	public static void main(String ar[]) {
		int A[][] = { { 1, 1, 1, 0 }, { 1, 1, 1, 0 }, { 1, 1, 1, 0 }, { 0, 1, 1, 1 } };
		System.out.println(robotTravel(A, 0, 0, 0));
	}

	public static int robotTravel(int[][] A, int i, int j, int rest) {
		if (i == A.length - 1 && j == A[0].length - 1)
			return 1;

		int res = 0;
		if (couldStepToBlock(A, i + 1, j)) {
			res += robotTravel(A, i + 1, j, res);
		}
		if (couldStepToBlock(A, i, j + 1)) {
			res += robotTravel(A, i, j + 1, res);
		}
		return res;
	}

	private static boolean couldStepToBlock(int[][] a, int ni, int nj) {
		if (ni < a.length && nj < a[0].length && a[ni][nj] == 1)
			return true;
		return false;
	}
}
