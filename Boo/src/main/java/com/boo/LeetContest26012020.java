package com.boo;

import java.util.*;
import java.util.stream.Collectors;

public class LeetContest26012020 {
	class Hotel {
		int id, rating, price, distance;
		boolean veganFriendly;

		Hotel(int... args) {
			id = args[0];
			rating = args[1];
			int v = args[2];
			price = args[3];
			distance = args[4];
			veganFriendly = v == 1;
		}
	}

	public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
		List<Hotel> hotelList = new ArrayList<>();
		List<Hotel> res = new ArrayList<>();
		for (int r[] : restaurants) {
			hotelList.add(new Hotel(r));
		}
		res = hotelList.stream().filter(h -> h.distance <= maxDistance).filter(h -> h.price <= maxPrice)
				.filter(h -> veganFriendly != 1 || (veganFriendly == 1 && h.veganFriendly))
				.collect(Collectors.toList());
		res.sort((a, b) -> a.rating == b.rating ? b.id - a.id : b.rating - a.rating);
		return res.stream().map(r -> r.id).collect(Collectors.toList());
	}

	public static boolean isPalindrome(String s) {
		int i = 0, j = s.length() - 1;
		int len = s.length();
		while (i < len / 2) {
			if (s.charAt(i++) != s.charAt(j--))
				return false;
		}
		return true;
	}

	public static void main(String[] args)

	{
		LeetContest26012020 l = new LeetContest26012020();

		int[][] a = { { 0, 1, 2 }, { 0, 4, 8 }, { 1, 2, 3 }, { 1, 4, 2 }, { 2, 3, 1 }, { 3, 4, 1 } };
	}

}
