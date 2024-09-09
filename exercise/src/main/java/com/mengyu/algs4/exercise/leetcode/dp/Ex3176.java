package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex3176 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 1, 3};
        System.out.println(new Ex3176().maximumLength(nums, 2));
    }

    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[n][k + 1];
        dp[0][0] = 1;
        int ans = 1;
        for (int i = 1; i < n; i++) {
            dp[i][0] = 1;
            for (int j = 0; j < i; j++) {
                int add = nums[i] == nums[j] ? 0 : 1;
                for (int l = add; l <= k; l++) {
                    dp[i][l] = Math.max(dp[i][l], dp[j][l - add] + 1);
                    ans = Math.max(ans, dp[i][l]);
                }
            }
        }
        return ans;
    }
}
