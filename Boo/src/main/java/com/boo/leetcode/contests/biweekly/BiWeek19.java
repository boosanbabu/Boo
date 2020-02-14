package com.boo.leetcode.contests.biweekly;

public class BiWeek19 {

	public double angleClock(int hour, int minutes) {
		double hourAngleFromOrigin = hour * 30;
		double minAngleFromOrigin = minutes * 6;
		double diff = hourAngleFromOrigin - minAngleFromOrigin;
		double hourMoved = minutes * 0.5;
		double ans = Math.abs(diff + hourMoved);
		ans = Math.min(ans, 360 - ans);
		return ans;
	}

	public int numberOfSteps(int num) {
		int c = 0;
		while (num > 0) {
			c++;
			if (num % 2 == 0)
				num /= 2;
			else
				num -= 1;
		}
		return c;
	}

	public int numOfSubarrays(int[] arr, int k, int threshold) {
		int size = arr.length;

		long prefixSum[] = new long[size];
		prefixSum[0] = arr[0];
		for (int i = 1; i < size; i++)
			prefixSum[i] = prefixSum[i - 1] + arr[i];

		int c = 0;
		if (k == size && Math.floor((double) prefixSum[size - 1] / k) >= threshold)
			return 1;
		int p = 0;
		for (int i = 0; i < size - k + 1; i++) {
			double sum = prefixSum[i + k - 1] - p;
			if (Math.floor(sum / k) >= threshold)
				c++;
			p = (int) prefixSum[i];
		}
		return c;
	}

	public static void main(String[] args) {
		BiWeek19 b19 = new BiWeek19();

		System.out.println(b19.numOfSubarrays(new int[] { 11, 13, 17, 23, 29, 31, 7, 5, 2, 3 }, 3, 5));
		System.out.println(b19.numOfSubarrays(new int[] { 4, 4, 4, 4, 3 }, 4, 4));
	}

}
