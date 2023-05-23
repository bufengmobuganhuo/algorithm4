package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex1770 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int[] multipliers = {3, 2, 1};
        System.out.println(new Ex1770().maximumScore(nums, multipliers));
    }

    /**
     * 1. dp[i][j]：与nums的前i个元素和后j个元素相乘得到的最大分数
     * 2. 对于dp[i][0]：只从前面取，则dp[i][0] = dp[i-1][0] + multipliers[i] * nums[i]
     * 3. 对于dp[0][j]：只从后面取，则dp[0][j] = dp[0][j-1] + multipliers[j] * nums[j]
     * 4. 对于dp[i][j]：依赖于dp[i-1][j]+nums[i]*multipliers[j + i - 1](已经取了i+j-1个), dp[i][j-1] + nums[j] * multipliers[i +
     * j - 1]
     */
    public int maximumScore(int[] nums, int[] multipliers) {
        int n = nums.length, m = multipliers.length;
        int[][] dp = new int[m + 1][m + 1];
        for (int i = 1, j = 1; i <= m && j <= m; i++, j++) {
            dp[i][0] = dp[i - 1][0] + nums[i - 1] * multipliers[i - 1];
            dp[0][j] = dp[0][j - 1] + nums[n - j] * multipliers[j - 1];
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; i + j - 1 < m; j++) {
                dp[i][j] = Math.max(dp[i - 1][j] + nums[i - 1] * multipliers[i + j - 1],
                        dp[i][j - 1] + nums[n - j] * multipliers[i + j - 1]);
            }
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i <= m; i++) {
            ans = Math.max(ans, dp[i][m - i]);
        }
        return ans;
    }
}
