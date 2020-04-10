package com.boo.leetcode.contests.biweekly.feb22;

import java.util.HashMap;
import java.util.Map;

public class Cashier {
	int counter;
	int n;
	int discount;
	Map<Integer, Integer> products;

	public Cashier(int n, int discount, int[] products, int[] prices) {
		this.n = n;
		this.discount = discount;
		this.products = new HashMap<Integer, Integer>();
		for (int i = 0; i < products.length; i++)
			this.products.put(products[i], prices[i]);
		counter = 0;
	}

	public double getBill(int[] product, int[] amount) {
		counter++;
		double bill = 0d;
		for (int i = 0; i < product.length; i++) {
			bill += products.get(product[i]) * amount[i];
		}
		if (counter % n == 0) {
			bill = bill - (discount * bill) / 100;
		}
		return bill;
	}
}