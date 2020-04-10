package com.boo.algorithms.dp;

/**
 * Assume an array and we start from element at index 0. We want to go from 0
 * index to last index of the array by taking steps of at max length K.
 * 
 * For example, suppose an array is [10,2,-10,5,20] and K is 2, which means
 * maximum step length is 2 (We can assume K is always possible and less than
 * length of array). Now as we start from index 0, our score currently is 10 and
 * then we can either go to 2 or can go to -10. Suppose we go to 2 from here so
 * total score becomes 10+2=12. Now from 2 we can go to -10 or 5 so you go to 5
 * making score 12+5=17. From here you directly go to last index as you have no
 * way other than that, hence total score is 17+20=37.
 * 
 * For given array of length N and an integer K we need to find maximum score we
 * can get. I thought of a solution, to divide it into sub problems by deciding
 * weather to go at index i or not and recursively call the remaining array. But
 * I sense some dynamic programming out of this problem.
 * 
 * How can this be solved for given array of size N and integer K.
 * 
 * Constraint : 1<=N<=100000 and 1<=K<=N
 *
 * @see <a href=
 *      "link">https://stackoverflow.com/questions/51619016/max-score-to-reach-end-of-array-with-step-length</a>
 */
public class MaxScoreToReachEnd {
	public int maxScore(int k, int[] arr, int i, int score, Integer operationCount) {

		if (i > arr.length - 1) {
			return score;
		}
		score += arr[i];
		int maxScore = score;
		for (int x = 1; x <= k; x++) {
			maxScore = Math.max(maxScore, maxScore(k, arr, i + x, score, operationCount++));
		}
		return maxScore;
	}

	public int maxScoreMemo(int k, int[] arr, int start, int len, int score, Integer[] memo) {
		if (memo[start] != null)
			return memo[start];
		if (start == len - 1)
			memo[start] = arr[start];
		else {
			int maxScore = score;
			for (int x = 1; x <= k && start + x < len; x++) {
				int scoreIfIncluded = maxScoreMemo(k, arr, start + x, len, score, memo) + arr[start];
				maxScore = Math.max(maxScore, scoreIfIncluded);
			}
			memo[start] = maxScore;
		}
		return memo[start];
	}

	public static void main(String[] args) {
		MaxScoreToReachEnd m = new MaxScoreToReachEnd();
		Integer operationCount = 0;
		int k = 3;
		int[] arr = { 10, 2, -10, 5, 20 };
		System.out.println("Input");
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println("\nOutput");
		System.out.println("Brute " + m.maxScore(k, arr, 0, 0, operationCount));
		System.out.println("Memo  " + m.maxScoreMemo(k, arr, 0, arr.length, 0, new Integer[arr.length]));
		arr = new int[] { 1, -2, -3, 4, -5, -6, 2, -10, 5, 20 };
		System.out.println("\nInput");
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println("\nOutput");
		System.out.println("Brute " + m.maxScore(k, arr, 0, 0, operationCount));
		System.out.println("Memo  " + m.maxScoreMemo(k, arr, 0, arr.length, 0, new Integer[arr.length]));
	}

}
