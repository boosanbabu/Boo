package com.boo.leetcode.problems;

import java.util.*;

//Q.1376
public class TimeToInformAllEmployees {
	class Employee {
		int id;
		List<Integer> subordinates;
		int timeToInform;

		Employee(int i, int t) {
			id = i;
			timeToInform = t;
			subordinates = new ArrayList<>();
		}
	}

	public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {

		Map<Integer, Employee> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			Employee e = new Employee(i, informTime[i]);
			map.put(i, e);
		}
		Employee head = map.get(headID);
		for (int i = 0; i < n; i++) {
			Employee e = map.get(manager[i]);
			if (e != null)
				e.subordinates.add(i);
		}

		int total = 0;
		total = dfs(head, informTime[headID], informTime, map);
		return total;

	}

	public int dfs(Employee emp, int t, int[] informTime, Map<Integer, Employee> map) {
		if (emp == null)
			return t;
		int max = 0;
		for (int e : emp.subordinates) {
			Employee sub = map.get(e);
			max = Math.max(dfs(sub, informTime[e], informTime, map) + t, max);
		}
		return max;
	}

	public static void main(String[] args) {
		/**
		 * 11 4 [] [0,213,0,253,686,170,975,0,261,309,337]
		 */
		TimeToInformAllEmployees t = new TimeToInformAllEmployees();
		int headID = 6;
		int[] manager = { 1, 2, 3, 4, 5, 6, -1 };
		int[] informTime = { 0, 6, 5, 4, 3, 2, 1 };
		// System.out.println(t.numOfMinutes(manager.length, headID, manager,
		// informTime));

		int[] inp = { 2, 2, 2, 2, 1, 1, 1, 1, 0, 0 };
		int[] table = new int[3];
		for (int i : inp) {
			table[i]++;
		}
		int k = 0;
		for (int i = 0; i < 3; i++) {
			while (table[i] > 0) {
				inp[k++] = i;
				table[i]--;
			}
		}
		for (int i : inp) {
			System.out.print(i + " ");
		}
	}
}
