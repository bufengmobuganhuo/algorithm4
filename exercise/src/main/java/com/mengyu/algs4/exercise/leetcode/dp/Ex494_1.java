package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex494_1 {

    public static void main(String[] args) {
        System.out.println(new Ex494_1().findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
    }

    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int sum = 0;
        for (int k : nums) {
            sum += k;
        }
        if (sum < target || -sum > target) {
            return 0;
        }
        int[][] dp = new int[2][2001];
        dp[0][nums[0] + 1000]++;
        dp[0][1000 - nums[0]]++;
        int lastIdx = 0;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            for (int j = -sum; j < sum + 1; j++) {
                if (0 <= j + 1000 - num) {
                    dp[1 - lastIdx][j + 1000] += dp[lastIdx][j + 1000 - num];
                }
                if (j + 1000 + num < 2001) {
                    dp[1 - lastIdx][j + 1000] += dp[lastIdx][j + 1000 + num];
                }
            }
            lastIdx = 1 - lastIdx;
        }
        return dp[lastIdx][target + 1000];
    }
}
