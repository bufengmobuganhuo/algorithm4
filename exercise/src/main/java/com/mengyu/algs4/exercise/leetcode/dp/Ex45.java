package com.mengyu.algs4.exercise.leetcode.dp;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex45 {

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(new Ex45().jump(nums));
    }

    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            int j = nums[i];
            for (int k = 1; k <= j && i + k < n; k++) {
                dp[i + k] = Math.min(dp[i + k], dp[i] + 1);
                if (i + k == n - 1) {
                    return dp[i + k];
                }
            }
        }
        return dp[n - 1];
    }
}
