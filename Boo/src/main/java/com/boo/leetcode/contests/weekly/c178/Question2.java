package com.boo.leetcode.contests.weekly.c178;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Question2 {

	public String rankTeams(String[] votes) {
		int totalRanks = votes[0].length();
		int[][] ranking = new int[totalRanks][26];

		for (String v : votes) {
			for (int i = 0; i < v.length(); i++) {
				ranking[i][v.charAt(i) - 'A']++;
			}
		}

		List<Character> candidates = new ArrayList<>();
		for (char ch : votes[0].toCharArray()) {
			candidates.add(ch);
		}

		candidates.sort(new Comparator<Character>() {
			@Override
			public int compare(Character c1, Character c2) {
				for (int i = 0; i < totalRanks; i++) {
					if (ranking[i][c1 - 'A'] != ranking[i][c2 - 'A']) {
						return ranking[i][c2 - 'A'] - ranking[i][c1 - 'A'];
					}
				}
				return c1 - c2;
			}
		});
		return listToString(candidates);
	}

	private String listToString(List<Character> candidates) {
		StringBuilder sb = new StringBuilder();
		for (Character ch : candidates) {
			sb.append(ch);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		Question2 q = new Question2();

		String[] votes = { "BCA", "CAB", "CBA", "ABC", "ACB", "BAC" };
		System.out.println(q.rankTeams(votes));
		System.out.println();
		votes = new String[] { "WXYZ", "XYZW" };
		System.out.println(q.rankTeams(votes));

		System.out.println();
		votes = new String[] { "ABC", "ACB", "ABC", "ACB", "ACB" };
		System.out.println(q.rankTeams(votes));

	}

}
