package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex2786 {
    /**
     * 从左到右遍历，当遍历到nums[i]时，他只可能从两种情况中得到结果：
     * 1. nums[i]左边与nums[i]相同奇偶性的值
     * 2. nums[i]左边与nums[i]不同奇偶性的值
     *
     * 我们只要维护这两个值即可得到答案
     * @param nums
     * @param x
     * @return
     */
    public long maxScore(int[] nums, int x) {
        long ans = nums[0];
        long[] dp = {Integer.MIN_VALUE, Integer.MIN_VALUE};
        dp[nums[0] % 2] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int idx = nums[i] % 2;
            long cur = Math.max(dp[idx] + nums[i], dp[1 - idx] + nums[i] - x);
            ans = Math.max(cur, ans);
            dp[idx] = Math.max(dp[idx], cur);
        }
        return ans;
    }
}
