package com.boo.datastructure.array;

public class RotateArray {

	public void rotateLeft(int[] arr, int k) {
		if (arr.length == 0)
			return;
		k = k % arr.length;
		ArrayUtil.reverse(arr, 0, k - 1);
		ArrayUtil.reverse(arr, k, arr.length - 1);
		ArrayUtil.reverse(arr);
	}

	public void rotateRight(int[] arr, int k) {
		k = k % arr.length;
		rotateLeft(arr, arr.length - k);
	}

	public static void main(String[] args) {
		int arr[] = { 1, 23, 4, 5, 67, 3, 2, 32 };
		RotateArray r = new RotateArray();
		r.rotateLeft(arr, 3);
		ArrayUtil.checkEquals(arr, new int[] { 5, 67, 3, 2, 32, 1, 23, 4 });
		r.rotateRight(arr, 3);
		ArrayUtil.checkEquals(arr, new int[] { 1, 23, 4, 5, 67, 3, 2, 32 });
	}

}
