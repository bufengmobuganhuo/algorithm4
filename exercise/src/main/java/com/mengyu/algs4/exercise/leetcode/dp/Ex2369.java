package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex2369 {

    public static void main(String[] args) {
        System.out.println(new Ex2369().validPartition(new int[]{4, 4, 4, 5, 6}));
    }

    public boolean validPartition(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        dp[1] = false;
        for (int i = 2; i < n + 1; i++) {
            boolean flag = false;
            if (nums[i - 1] == nums[i - 2]) {
                flag = flag || dp[i - 2];
            }
            if (i >= 3 && nums[i - 1] == nums[i - 2] && nums[i - 1] == nums[i - 3]) {
                flag = flag || dp[i - 3];
            }
            if (i >= 3 && nums[i - 1] - nums[i - 2] == 1 && nums[i - 2] - nums[i - 3] == 1) {
                flag = flag || dp[i - 3];
            }
            dp[i] = flag;
        }
        return dp[n];
    }
}
