package com.boo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Amazon1 {

	public static int clean_block(int[][] A, int x_in, int y_in, int cur_cnt) {
		A[x_in][y_in] = 0;
		if (coordinate_exists(x_in - 1, y_in, A.length, A[0].length) == 1 && A[x_in - 1][y_in] == 1) {
			clean_block(A, x_in - 1, y_in, cur_cnt);
			cur_cnt = 1;
		}
		if (coordinate_exists(x_in + 1, y_in, A.length, A[0].length) == 1 && A[x_in + 1][y_in] == 1) {
			clean_block(A, x_in + 1, y_in, cur_cnt);
			cur_cnt = 1;
		}
		if (coordinate_exists(x_in, y_in - 1, A.length, A[0].length) == 1 && A[x_in][y_in - 1] == 1) {
			clean_block(A, x_in, y_in - 1, cur_cnt);
			cur_cnt = 1;
		}
		if (coordinate_exists(x_in, y_in + 1, A.length, A[0].length) == 1 && A[x_in][y_in + 1] == 1) {
			clean_block(A, x_in, y_in + 1, cur_cnt);
			cur_cnt = 1;
		}

		return cur_cnt;
	}

	public static int coordinate_exists(int x_in, int y_in, int A_x_length, int A_y_length) {
		if (x_in >= 0 && x_in <= (A_x_length - 1) && y_in >= 0 && y_in <= (A_y_length - 1)) {
			return 1;
		} else {
			return 0;
		}
	}

	private static int minDays(int[][] A) {
		Queue<int[]> q = new LinkedList<>();
		int target = A.length * A[0].length;
		int cnt = 0, res = 0;
		List<List<Integer>> grid = new ArrayList<>();
		for (int i = 0; i < grid.size(); i++) {
			for (int j = 0; j < grid.get(0).size(); j++) {
				A[i][j] = grid.get(i).get(j);
			}
		}
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[0].length; j++) {
				if (A[i][j] == 1) {
					q.offer(new int[] { i, j });
					cnt++;
				}
			}
		}
		int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
		while (!q.isEmpty()) {
			int size = q.size();
			if (cnt == target)
				return res;
			for (int i = 0; i < size; i++) {
				int[] cur = q.poll();
				for (int[] dir : dirs) {
					int ni = cur[0] + dir[0];
					int nj = cur[1] + dir[1];
					if (ni >= 0 && ni < A.length && nj >= 0 && nj < A[0].length && A[ni][nj] == 0) {
						cnt++;
						q.offer(new int[] { ni, nj });
						A[ni][nj] = 1;
					}
				}
			}
			res++;
		}
		return -1;
	}

	public static ArrayList<String> topNCompetitors(int numCompetitors, int topNCompetitors, List<String> competitors,
			int numReviews, List<String> reviews) {
		ArrayList<String> res = new ArrayList<>();
		if (numCompetitors == 0 || numReviews == 0)
			return res;
		Map<String, Integer> map = new HashMap<>();
		for (String comp : competitors) {
			map.put(comp, 0);
		}

		for (String review : reviews) {
			String[] reviewWords = review.split(" ");
			Set<String> competitorsFoundInReview = new HashSet<>();
			for (String word : reviewWords) {
				if (word != null && word.length() > 0) {
					String wordInLowerCase = word.toLowerCase();
					if (map.containsKey(wordInLowerCase)) {
						competitorsFoundInReview.add(wordInLowerCase);
					}
				}
			}
			for (String c : competitorsFoundInReview) {
				map.put(c, map.get(c) + 1);
			}
		}

		res = topKFrequent(map, topNCompetitors);
		return res;
	}

	private static List<String> findN(int noOfComp, int TopN, List<String> competitorsList, int noofReviews,
			List<String> reviews) {
		ArrayList<String> result = new ArrayList<>(1);
		Set<String> competitors = new HashSet<>(competitorsList);
		Map<String, Long> competitorFreqMap = new HashMap<>();

		reviews.parallelStream().forEach(

				(review) -> {

					Set<String> competitorsinReview = new HashSet<>();
					String[] reviewTokens = review.split(" ");
					for (String reviewToken : reviewTokens) {
						if (competitors.contains(reviewToken) && !competitorsinReview.contains(reviewToken)) {
							competitorsinReview.add(reviewToken);
							Long freq = competitorFreqMap.get(reviewToken) != null ? competitorFreqMap.get(reviewToken)
									: 0l;
							freq = freq + 1;
							competitorFreqMap.put(reviewToken, freq);
						}
					}
				});

		List<Map.Entry<String, Long>> freqEntries = new ArrayList<Map.Entry<String, Long>>(
				competitorFreqMap.entrySet());

		final Comparator<Map.Entry<String, Long>> FREQ_COMPARATOR = new Comparator<Map.Entry<String, Long>>() {
			@Override
			public int compare(Map.Entry<String, Long> t1, Map.Entry<String, Long> t2) {
				if (t1.getValue() == t2.getValue()) {
					return t1.getKey().compareTo(t2.getKey());
				} else {
					return t2.getValue().compareTo(t1.getValue());
				}
			}
		};

		Collections.sort(freqEntries, FREQ_COMPARATOR);

		for (int i = 0; i < TopN; i++) {
			if (i < freqEntries.size())
				result.add(freqEntries.get(i).getKey());
		}

		return result;
	}

	public static ArrayList<String> topKFrequent(Map<String, Integer> freqCount, int k) {
		PriorityQueue<String> minHeap = new PriorityQueue<>((a, b) -> {
			if (freqCount.get(a).equals(freqCount.get(b))) {
				return b.compareTo(a);
			}
			return freqCount.get(a).compareTo(freqCount.get(b));
		});

		for (String w : freqCount.keySet()) {
			minHeap.offer(w);
			if (minHeap.size() > k) {
				minHeap.poll();
			}
		}

		ArrayList<String> res = new ArrayList<>();
		while (!minHeap.isEmpty()) {
			res.add(minHeap.poll());
		}
		Collections.reverse(res);
		return res;
	}

	public static void main(String a[]) {
		List<String> cmp = Arrays.asList(new String[] { "abc", "cde", "fgg", "fggg" });
		List<String> rvs = Arrays.asList(new String[] { "abc poda abc", "abc fgg cde", "fggg fggg", "abc abc fgg" });
		System.out.println(topNCompetitors(4, 2, cmp, 4, rvs));
		System.out.println(findN(4, 2, cmp, 4, rvs));
	}
}