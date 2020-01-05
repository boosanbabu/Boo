package com.boo.datastructure.heap;

import java.util.PriorityQueue;

/**
 * 
 * Question : Find the kth largest element in an unsorted array. Note that it is
 * the kth largest element in the sorted order, not the kth distinct element.
 *
 * @see <a href=
 *      "https://leetcode.com/problems/kth-largest-element-in-an-array/">K th
 *      Largest - Leetcode</a>
 */
public class KthLargestInArray {

	public int findKthLargest(int[] nums, int k) {
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		for (int n : nums) {
			heap.add(n);
			if (heap.size() > k) {
				heap.poll();
			}
		}
		return heap.poll();
	}

	public static void main(String[] args) {
		KthLargestInArray sol = new KthLargestInArray();
		int nums[] = { 3, 2, 3, 1, 2, 4, 5, 5, 6 };
		assertEquals(sol.findKthLargest(nums, 4), 4);
		assertEquals(sol.findKthLargest(nums, 3), 5);
		assertEquals(sol.findKthLargest(nums, 2), 5);
		assertEquals(sol.findKthLargest(nums, 1), 6);
	}

	private static void assertEquals(int x, int y) {
		System.out.println(x == y ? "passed" : "failed");
	}

}
