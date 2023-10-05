package com.mengyu.algs4.exercise.leetcode.dp;

import java.util.Arrays;

/**
 * @date 2023/10/4 10:24
 * TODO
 */
public class Ex188 {

    public static void main(String[] args) {
        int[] prices = {2,6,8,7,8,7,9,4,1,2,4,5,8};
        System.out.println(new Ex188().maxProfit(3, prices));
    }

    /**
     * buy[i][j]，在第i天，进行恰好是第j笔买交易时，手上持有股票的最大收益=max{前一天已经持有了股票，在第i天买入} = max{buy[i-1][j], sell[i-1][j-1]-prices[i]}
     * (第j次买是建立在第j-1次卖基础上的，并且可能是前一天卖出的，也可能是当天卖出的，所以sell[i][j -1])
     * sell[i][j]，在第i天，进行恰好是第j笔卖交易时，手上不持有股票的最大收益=max{前一天已经卖出，当天卖出} = max{sell[i-1][j], buy[i][j]+prices[i]}
     * (第j次卖是建立在第j次买的基础上的，并且可能是当天买入的，也可能是前一天买入的，所以是buy[i][j])
     */
    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0 || k == 0) {
            return 0;
        }
        int n = prices.length;
        int[][] buy = new int[n][k];
        int[][] sell = new int[n][k];

        // 第0天先买入，再在当天卖出，所以买的收益是-prices[0]，卖的收益永远是0
        for (int i = 0; i < k; i++) {
            buy[0][i] = -prices[0];
        }
        int max = 0;
        for (int i = 1; i < n; i++) {
            // max{前一天已经买入了，当天买入}
            buy[i][0] = Math.max(buy[i - 1][0], -prices[i]);
            // max{前一天已经卖出；当天卖出，可能是前一天买入的，也可能是前一天买入的}
            sell[i][0] = Math.max(sell[i - 1][0], buy[i][0] + prices[i]);
            max = Math.max(sell[i][0], max);
            for (int j = 1; j < k; j++) {
                buy[i][j] = Math.max(buy[i - 1][j], sell[i - 1][j - 1] - prices[i]);
                sell[i][j] = Math.max(sell[i - 1][j], buy[i][j] + prices[i]);
                max = Math.max(sell[i][j], max);
            }
        }
        return max;
    }

    // 空间优化
    public int maxProfit2(int k, int[] prices) {
        if (prices.length == 0 || k == 0) {
            return 0;
        }
        int n = prices.length;
        int[] buy = new int[k];
        int[] sell = new int[k];

        Arrays.fill(buy, -prices[0]);
        int ans = 0;
        for (int i = 1; i < n; i++) {
            buy[0] = Math.max(buy[0], -prices[i]);
            sell[0] = Math.max(sell[0], buy[0] + prices[i]);
            ans = Math.max(sell[0], ans);
            for (int j = 1; j < k; j++) {
                buy[j] = Math.max(buy[j], sell[j - 1] - prices[i]);
                sell[j] = Math.max(sell[j], buy[j] + prices[i]);
                ans = Math.max(sell[j], ans);
            }
        }
        return ans;
    }
}
