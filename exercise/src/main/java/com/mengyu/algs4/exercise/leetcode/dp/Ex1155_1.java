package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex1155_1 {

    private static final int mod = 1000000007;

    public int numRollsToTarget(int n, int k, int target) {
        int[][] dp = new int[n + 1][target + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < target + 1; j++) {
                for (int l = 1; l <= k; l++) {
                    if (j - l >= 0) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - l]) % mod;
                    }
                }
            }
        }
        return dp[n][target];
    }
}
