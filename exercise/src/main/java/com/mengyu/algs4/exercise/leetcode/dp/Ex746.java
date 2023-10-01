package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex746 {

    public int minCostClimbingStairs3(int[] cost) {
        int n = cost.length;
        int cost0 = 0, cost1 = 0;
        int ans = 0;
        for (int i = 2; i < n + 1; i++) {
            ans = Math.min(cost0 + cost[i - 2], cost1 + cost[i - 1]);
            cost0 = cost1;
            cost1 = ans;
        }
        return ans;
    }

    public int minCostClimbingStairs2(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        for (int i = 2; i < n + 1; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }

    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[][] dp = new int[n + 1][2];
        dp[1][0] = cost[0];
        for (int i = 2; i < n + 1; i++) {
            dp[i][0] = Math.min(dp[i - 1][0] + cost[i - 1], dp[i - 2][0] + cost[i - 2]);
            dp[i][1] = Math.min(dp[i - 1][1] + cost[i - 1], dp[i - 2][1] + cost[i - 2]);
        }
        return Math.min(dp[n][0], dp[n][1]);
    }
}
