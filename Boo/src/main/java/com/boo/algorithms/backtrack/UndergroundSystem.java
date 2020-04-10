package com.boo.algorithms.backtrack;

import java.util.*;

class UndergroundSystem {
	Map<Integer, String> idStation = new HashMap();
	Map<Integer, Integer> idTime = new HashMap();

	public UndergroundSystem() {

	}

	public void checkIn(int id, String stationName, int t) {
		idStation.put(id, stationName);
		idTime.put(id, t);
	}

	public void checkOut(int id, String stationName, int t) {
		idStation.put(id, idStation.get(id) + stationName);
		idTime.put(id, t - idTime.get(id));
	}

	public double getAverageTime(String startStation, String endStation) {
		String combinedStation = startStation + endStation;
		int count = 0;
		List<Integer> idList = new ArrayList();
		for (Map.Entry<Integer, String> map : idStation.entrySet()) {
			int key = map.getKey();
			String val = map.getValue();
			if (val.equals(combinedStation)) {
				idList.add(key);
				count++;
			}
		}

		double totalTime = 0;
		for (Map.Entry<Integer, Integer> map : idTime.entrySet()) {
			int key = map.getKey();
			int val = map.getValue();
			if (idList.contains(key)) {
				totalTime += val;
			}
		}

		if (count == 0) {
			return 0.0;
		}
		System.out.println((double) (totalTime) / count);
		return (double) (totalTime) / count;
	}

	public static void main(String[] args) {
		UndergroundSystem undergroundSystem = new UndergroundSystem();
		undergroundSystem.checkIn(45, "Leyton", 3);
		undergroundSystem.checkIn(32, "Paradise", 8);
		undergroundSystem.checkIn(27, "Leyton", 10);
		undergroundSystem.checkOut(45, "Waterloo", 15);
		undergroundSystem.checkOut(27, "Waterloo", 20);
		undergroundSystem.checkOut(32, "Cambridge", 22);
		undergroundSystem.getAverageTime("Paradise", "Cambridge"); // return 14.0. There was only one travel from
																	// "Paradise" (at time 8) to "Cambridge" (at time
																	// 22)
		undergroundSystem.getAverageTime("Leyton", "Waterloo"); // return 11.0. There were two travels from "Leyton" to
																// "Waterloo", a customer with id=45 from time=3 to
																// time=15 and a customer with id=27 from time=10 to
																// time=20. So the average time is ( (15-3) + (20-10) )
																// / 2 = 11.0
		undergroundSystem.checkIn(10, "Leyton", 24);
		undergroundSystem.getAverageTime("Leyton", "Waterloo"); // return 11.0
		undergroundSystem.checkOut(10, "Waterloo", 38);
		undergroundSystem.getAverageTime("Leyton", "Waterloo"); // return 12.0

	}

}
