package com.boo.leetcode.contests.weekly;

import java.util.*;

class ProductOfNumbers {

	List<Integer> prod;

	public ProductOfNumbers() {
		reset();
	}

	public void reset() {
		prod = new ArrayList<>();
		prod.add(1);
	}

	public void add(int num) {
		if (num == 0) {
			reset();
			return;
		}

		int len = prod.size();
		prod.add(num * prod.get(len - 1));
	}

	public int getProduct(int k) {
		int len = prod.size();
		if (k >= len)
			return 0;
		return prod.get(len - 1) / prod.get(len - 1 - k);
	}

	public static void main(String[] args) {
		ProductOfNumbers p = new ProductOfNumbers();

		p.add(3);
		p.add(0);
		p.add(2);
		p.add(5);
		p.add(4);

		System.out.println(p.getProduct(2));
		System.out.println(p.getProduct(3));
		System.out.println(p.getProduct(4));
		p.add(8);
		System.out.println(p.getProduct(4));
		System.out.println(p.getProduct(2));

	}

}
