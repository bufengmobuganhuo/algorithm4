package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex121 {
    public int maxProfit(int[] prices) {
        int profitBuy = Integer.MIN_VALUE, profitSell = 0;
        for (int i = 0; i < prices.length; i++) {
            if (i > 0) {
                profitSell = Math.max(profitSell, prices[i] + profitBuy);
            }
            profitBuy = Math.max(profitBuy, -1 * prices[i]);
        }
        return profitSell;
    }
}
