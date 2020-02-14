package com.boo.algorithms.greedy;

import java.util.*;
import java.util.Map.Entry;

/**
 * https://leetcode.com/problems/car-pooling/
 * 
 * @param trips
 * @param capacity
 * @return
 */
public class CarPool {
	static class Activity {
		int passenger;
		int location;

		Activity(int p, int l) {
			passenger = p;
			location = l;
		}
	}

	public static final int PASSENGER_COUNT_IDX = 0;
	public static final int BOARDING_LOC_IDX = 1;
	public static final int ALIGHTING_AT_IDX = 2;

	/**
	 * Consider boarding and alight as separate events, sort all events by location
	 * and passenger
	 * 
	 * @param trips
	 * @param capacity
	 * @return
	 */
	public boolean carPooling1(int[][] trips, int capacity) {
		List<Activity> activities = new ArrayList<>();
		for (int[] trip : trips) {
			activities.add(new Activity(trip[PASSENGER_COUNT_IDX], trip[BOARDING_LOC_IDX]));
			activities.add(new Activity(-trip[PASSENGER_COUNT_IDX], trip[ALIGHTING_AT_IDX]));
		}

		activities.sort((a, b) -> a.location == b.location ? a.passenger - b.passenger : a.location - b.location);

		int occupiedSeat = 0;
		for (Activity a : activities) {
			occupiedSeat += a.passenger;
			if (occupiedSeat > capacity)
				return false;
		}
		return true;
	}

	public boolean carPooling(int[][] trips, int capacity) {
		TreeMap<Integer, Integer> activities = new TreeMap<>();
		for (int[] trip : trips) {
			int boardingPoint = trip[1];
			int alightPoint = trip[2];
			activities.put(boardingPoint, activities.getOrDefault(boardingPoint, 0) + trip[0]);
			activities.put(alightPoint, activities.getOrDefault(alightPoint, 0) - trip[0]);
		}
		int occupiedSeat = 0;
		for (Entry<Integer, Integer> activity : activities.entrySet()) {
			int passenger = activity.getValue();
			occupiedSeat += passenger;
			if (occupiedSeat > capacity)
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		CarPool c = new CarPool();
		int[][] trips = { { 2, 1, 5 }, { 3, 3, 7 } };
		int capacity = 5;
		System.out.println(c.carPooling(trips, capacity));
	}
}
