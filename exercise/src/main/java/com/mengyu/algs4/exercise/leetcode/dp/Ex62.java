package com.mengyu.algs4.exercise.leetcode.dp;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex62 {

    public int uniquePaths2(int m, int n) {
        int ans = 1;
        for (int x = m, y = 2; y < n; x++, y++) {
            ans = ans * x / y;
        }
        return ans;
    }

    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[n - 1];
    }
}
