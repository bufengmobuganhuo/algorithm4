package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex518_1 {
    public int change(int amount, int[] coins) {
        int m = coins.length;
        int[][] dp = new int[m][amount + 1];
        for (int i = 0; i <= amount; i++) {
            dp[0][i] = i % coins[0] == 0 ? 1 : 0;
        }
        for (int i = 1; i < m; i++) {
            int coin = coins[i];
            for (int j = 0; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j] + (j >= coin ? dp[i][j - coin] : 0);
            }
        }
        return dp[m - 1][amount];
    }
}
