package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yuzhang
 * @date 2021/3/12 上午9:33
 * TODO
 */
public class Ex486_1 {

    public boolean PredictTheWinner2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return true;
        }
        int len = nums.length;
        int[] dp = new int[len];
        System.arraycopy(nums, 0, dp, 0, len);
        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j - 1]);
            }
        }
        return dp[len - 1] >= 0;
    }

    public boolean PredictTheWinner(int[] nums) {
        if (nums == null || nums.length == 0) {
            return true;
        }
        int len = nums.length;
        // dp[i][j]，剩余[i....j]时，当前手和另一个人的差值
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = nums[i];
        }
        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][len - 1] > 0;
    }
}
