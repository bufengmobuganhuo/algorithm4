package com.mengyu.algs4.exercise.leetcode.greedy;

/**
 * @author yuzhang
 * @date 2021/1/19 上午9:16
 * TODO
 */
public class Ex122 {
    /**
     * 不限交易次数，则把所有的上坡收集到
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                res += (prices[i] - prices[i - 1]);
            }
        }
        return res;
    }

    /**
     * 1. 动态规划，为后面Ex123铺垫
     * 2. 对于交易的一天，只有两种情况：手上没有股票，手上有股票，
     * 令dp[i][0]为第i天手上没有股票时最大收益，dp[i][1]为第i天手上有股票时的最大收益
     * 3. 对于dp[i][0]：
     * 要么是第i-1天有股票，第i天卖出（此时获利prices[i-1]），要么是第i-1天没有股票，第i天也没有股票（此时获利还是dp[i-1][0]）
     * 故：dp[i][0]=max(dp[i-1][1]+prices[i-1],dp[i-1][0])
     * 4. 对于dp[i][1]:
     * 要么是第i-1天没有股票，第i天买入（此时获利-prices[i]），要么是第i-1天就有股票（此时获利还是dp[i-1][1]）
     * 故：dp[i][1]=max(dp[i-1][0]-prices[i],dp[i-1][1])
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int[][] dp = new int[prices.length][2];
        // 初始条件
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
        }
        return Math.max(dp[prices.length - 1][0], dp[prices.length - 1][1]);
    }
}
