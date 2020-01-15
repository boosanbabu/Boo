package com.boo.algorithms.dp;

import java.util.Arrays;

public class CoinChange {
	/**
	 * https://leetcode.com/problems/coin-change<br>
	 * <br>
	 * 
	 * You are given coins of different denominations and a total amount of money
	 * amount. Write a function to compute the fewest number of coins that you need
	 * to make up that amount. If that amount of money cannot be made up by any
	 * combination of the coins, return -1.
	 * 
	 * @param coins
	 * @param amount
	 * @return
	 */
	public int coinChangeMinimum(int[] coins, int amount) {
		if (amount == 0)
			return 0;

		int T[][] = new int[coins.length][amount + 1];
		Arrays.sort(coins);
		for (int i = 0; i < coins.length; i++) {
			Arrays.fill(T[i], Integer.MAX_VALUE);
			T[i][0] = 0;
		}

		for (int i = 1; i <= amount; i++) {
			if (i % coins[0] == 0)
				T[0][i] = i / coins[0];
		}

		for (int i = 1; i < coins.length; i++) {
			int currCoin = coins[i];
			for (int j = 1; j < amount + 1; j++) {
				boolean canSelectCurrCoin = j - currCoin > -1 && T[i][j - currCoin] != Integer.MAX_VALUE;

				if (canSelectCurrCoin) {
					T[i][j] = Math.min(1 + T[i][j - currCoin], T[i - 1][j]);
				} else {
					T[i][j] = T[i - 1][j];
				}
			}
		}
		return T[coins.length - 1][amount] == Integer.MAX_VALUE ? -1 : T[coins.length - 1][amount];
	}

	/**
	 * https://leetcode.com/problems/coin-change-2/
	 * 
	 * <br>
	 * <br>
	 * You are given coins of different denominations and a total amount of money.
	 * Write a function to compute the number of combinations that make up that
	 * amount. You may assume that you have infinite number of each kind of coin.
	 * 
	 * 
	 * @param coins
	 * @param amount
	 * @return
	 */
	public int coinChange(int[] coins, int amount) {
		if (coins.length == 0)
			return 0;
		int T[][] = new int[coins.length][amount + 1];
		for (int i = 0; i < coins.length; i++) {
			T[i][0] = 1;
		}
		Arrays.sort(coins);
		for (int i = 0; i < coins.length; i++) {
			for (int j = 1; j < amount + 1; j++) {
				int val = 0;
				if (j - coins[i] >= 0)
					val = T[i][j - coins[i]];

				T[i][j] = i != 0 ? T[i - 1][j] + val : val;
			}
		}
		return T[coins.length - 1][amount];
	}

	public static void main(String[] args) {
		int[] coins = { 1 };
		int targ = 0;
		CoinChange c = new CoinChange();
		System.out.println(c.coinChange(coins, targ));
	}

}
