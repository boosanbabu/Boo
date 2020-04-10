package com.boo.algorithms.greedy;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TaskScheduler {

	public static void main(String[] args) {
		TaskScheduler a = new TaskScheduler();
		int[][] arr = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 4, 5 }, { 2, 4 }, { 5, 7 }, { 7, 3 }, { 7, 6 } };
		a.canFinish(8, arr);
	}

	/*
	 * https://leetcode.com/problems/task-scheduler/solution/
	 */
	public int leastInterval(char[] tasks, int n) {
		int[] map = new int[26];
		for (char c : tasks)
			map[c - 'A']++;
		Arrays.sort(map);
		int max_val = map[25] - 1, idle_slots = max_val * n;
		for (int i = 24; i >= 0 && map[i] > 0; i--) {
			idle_slots -= Math.min(map[i], max_val);
		}
		return idle_slots > 0 ? idle_slots + tasks.length : tasks.length;
	}

	public boolean canFinish(int numCourses, int[][] prerequisites) {
		int[] completed = new int[numCourses];
		Arrays.fill(completed, 1);
		Map<Integer, List<Integer>> fromTo = new HashMap<>();
		for (int i = 0; i < numCourses; i++) {
			fromTo.put(i, new ArrayList<>());
		}
		for (int[] preReq : prerequisites) {
			if (completed[preReq[1]] == 1)
				completed[preReq[1]] = 0;
			fromTo.get(preReq[1]).add(preReq[0]);
		}
		// dfs(fromTo, 0);
		topoUsingBFS(numCourses, prerequisites);
		findOrder(numCourses, prerequisites);
		return true;
	}

	public void dfs(Map<Integer, List<Integer>> g, int start) {
		int[] visited = new int[g.size()];
		Deque<Integer> stack = new LinkedList<>();
		stack.add(start);
		visited[start] = 1;
		while (!stack.isEmpty()) {
			int curr = stack.pop();
			System.out.println(curr);
			List<Integer> children = g.get(curr);
			for (int c : children) {
				if (visited[c] == 1)
					continue;
				stack.push(c);
				visited[c] = 1;

			}
		}
	}

	public void topoSort(Map<Integer, List<Integer>> g, int n) {
		int[] visited = new int[n];
		Deque<Integer> stack = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			if (visited[i] != 1) {
				topoSortCore(g, visited, i, stack);
			}
		}
		for (int i : stack) {
			System.out.println(i);
		}
	}

	private void topoSortCore(Map<Integer, List<Integer>> g, int[] visited, int curr, Deque<Integer> stack) {
		visited[curr] = 1;
		List<Integer> children = g.get(curr);
		for (int c : children) {
			if (visited[c] != 1)
				topoSortCore(g, visited, c, stack);
		}
		stack.push(curr);
	}

	public boolean topoUsingBFS(int numCourses, int[][] prerequisites) {
		int[] inDegree = new int[numCourses];
		Map<Integer, List<Integer>> graph = new HashMap<>();
		for (int i = 0; i < numCourses; i++) {
			graph.put(i, new ArrayList<>());
		}

		for (int[] toFrom : prerequisites) {
			int to = toFrom[0];
			int from = toFrom[1];
			inDegree[to]++;
			graph.get(from).add(to);
		}

		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 0; i < inDegree.length; i++) {
			if (inDegree[i] == 0)
				q.add(i);
		}

		while (!q.isEmpty()) {
			int curr = q.poll();
			for (int child : graph.get(curr)) {
				inDegree[child]--;
				if (inDegree[child] == 0)
					q.add(child);
			}
		}

		for (int i = 0; i < numCourses; i++) {
			if (inDegree[i] > 0)
				return false;
		}
		return true;
	}

	public int[] findOrder(int numCourses, int[][] prerequisites) {
		int[] res = new int[numCourses];
		int[] indegree = new int[numCourses];
		List<Integer> adjList[] = new ArrayList[numCourses];
		for (int i = 0; i < numCourses; i++)
			adjList[i] = new ArrayList<>();

		for (int row[] : prerequisites) {
			indegree[row[0]]++;
			adjList[row[1]].add(row[0]);
		}

		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 0; i < indegree.length; i++) {
			if (indegree[i] == 0)
				q.offer(i);
		}

		int i = 0;
		while (!q.isEmpty()) {
			int curr = q.poll();
			res[i++] = curr;
			for (int child : adjList[curr]) {
				indegree[child]--;
				if (indegree[child] == 0)
					q.add(child);
			}
		}
		return res;
	}
}
