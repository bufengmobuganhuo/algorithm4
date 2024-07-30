package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex1186 {
    public int maximumSum(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][2];
        dp[0][0] = arr[0];
        int ans = arr[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], 0) + arr[i];
            dp[i][1] = Math.max(dp[i - 1][1] + arr[i], dp[i -1][0]);
            ans = Math.max(ans, Math.max(dp[i][0], dp[i][1]));
        }
        return ans;
    }
}
