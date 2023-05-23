package com.mengyu.algs4.exercise.leetcode.dp;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex322 {
    public static void main(String[] args) {
        Ex322 ex322 = new Ex322();
        int[] coins = {2, 5, 10, 1};
        System.out.println(ex322.coinChange2(coins, 27));
    }

    public int coinChange(int[] coins, int amount) {
        int coinsLen = coins.length;
        int[][] dp = new int[coinsLen][amount + 1];
        Arrays.fill(dp[0], -1);
        for (int i = 1; i * coins[0] <= amount; i++) {
            dp[0][i * coins[0]] = i;
        }
        dp[0][0] = 0;
        for (int i = 1; i < coinsLen; i++) {
            dp[i][0] = 0;
            for (int j = 1; j < amount + 1; j++) {
                dp[i][j] = -1;
                for (int k = 0; k * coins[i] <= j; k++) {
                    if (k * coins[i] == j) {
                        dp[i][j] = Math.min(dp[i][j] == -1 ? Integer.MAX_VALUE : dp[i][j], k);
                    } else if (dp[i - 1][j - k * coins[i]] != -1) {
                        dp[i][j] =
                            Math.min(dp[i][j] == -1 ? Integer.MAX_VALUE : dp[i][j], dp[i - 1][j - k * coins[i]] + k);
                    }
                }
            }
        }
        return dp[coinsLen - 1][amount];
    }

    /**
     * 参考完全背包问题
     */
    public int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int coin : coins) {
            for (int j = coin; j < amount + 1; j++) {
                if (dp[j - coin] != -1) {
                    dp[j] = Math.min(dp[j] == -1 ? Integer.MAX_VALUE : dp[j], dp[j - coin] + 1);
                }
            }
        }
        return dp[amount];
    }
}
