package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @date 2023/10/4 16:11
 * TODO
 */
public class Ex377 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(new Ex377().combinationSum4(nums, 4));
    }

    /**
     * 和leetcode.518对比做
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i < target + 1; i++) {
            for (int num : nums) {
                if (i >= num) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }
}
