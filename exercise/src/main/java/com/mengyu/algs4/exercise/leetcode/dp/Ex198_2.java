package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex198_2 {

    public int rob2(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int n = nums.length;
        int rob1 = nums[0];
        int rob2 = Math.max(nums[0], nums[1]);
        int ans = rob2;
        for (int i = 2; i < n; i++) {
            ans = Math.max(rob1 + nums[i], rob2);
            rob1 = rob2;
            rob2 = ans;
        }
        return ans;
    }

    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[n - 1];
    }
}
