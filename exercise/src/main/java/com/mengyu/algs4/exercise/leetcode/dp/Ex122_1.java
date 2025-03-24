package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex122_1 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int profitHasNoStock = 0;
        int profitHasStock = -1 * prices[0];
        for (int i = 1; i < n; i++) {
            profitHasNoStock = Math.max(profitHasStock + prices[i], profitHasNoStock);
            profitHasStock = Math.max(profitHasNoStock - prices[i], profitHasStock);
        }
        return profitHasNoStock;
    }
}
