package com.boo.datastructure.array;

public class ArrayUtil {

	public static void reverse(int arr[], int start, int end) {
		int n = end - start + 1;
		int i = 0;
		while (i < n / 2) {
			swap(arr, start + i, end - i);
			i++;
		}
	}

	public static void reverse(int arr[]) {
		reverse(arr, 0, arr.length - 1);
	}

	public static void swap(int[] arr, int i, int j) {
		arr[i] = arr[i] ^ arr[j];
		arr[j] = arr[i] ^ arr[j];
		arr[i] = arr[i] ^ arr[j];
	}

	public static boolean equals(int a[], int b[]) {
		if (a.length != b.length)
			return false;
		for (int i = 0; i < a.length; i++)
			if (a[i] != b[i])
				return false;

		return true;
	}

	public static void checkEquals(int a[], int b[]) {
		System.out.println(equals(a, b) ? "Passed" : "Failed");
	}

}
