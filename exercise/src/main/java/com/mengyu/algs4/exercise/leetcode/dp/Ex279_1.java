package com.mengyu.algs4.exercise.leetcode.dp;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex279_1 {

    public static void main(String[] args) {
        System.out.println(new Ex279_1().numSquares(12));
    }

    public int numSquares(int n) {
        int[] dp = new int[1 + n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 1;
        for (int i = 1; i < n; i++) {
            int multi = i * i;
            if (multi > n) {
                break;
            }
            dp[multi] = 1;
        }
        for (int i = 2; i <= n; i++) {
            if (dp[i] == 1) {
                continue;
            }
            for (int j = 1; j < i; j++) {
                dp[i] = Math.min(dp[i - j] + dp[j], dp[i]);
            }
        }
        return dp[n];
    }
}
