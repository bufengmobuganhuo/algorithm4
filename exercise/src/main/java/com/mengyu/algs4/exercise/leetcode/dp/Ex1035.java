package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex1035 {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 2, 1, 2};
        int[] nums2 = {1};
        System.out.println(new Ex1035().maxUncrossedLines(nums1, nums2));
    }

    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[][] dp = new int[2][n];
        for (int i = 0; i < n; i++) {
            if (nums1[0] == nums2[i]) {
                dp[0][i] = 1;
            } else {
                dp[0][i] = i > 0 ? dp[0][i - 1] : 0;
            }
        }
        int lastIdx = 0;
        for (int i = 1; i < m; i++) {
            dp[1 - lastIdx][0] = nums1[i] == nums2[0] ? 1 : dp[lastIdx][0];
            for (int j = 1; j < n; j++) {
                if (nums1[i] == nums2[j]) {
                    dp[1 - lastIdx][j] = Math.max(dp[lastIdx][j - 1] + 1, Math.max(dp[lastIdx][j], dp[1 - lastIdx][j - 1]));
                } else {
                    dp[1 - lastIdx][j] = Math.max(dp[lastIdx][j], dp[1 - lastIdx][j - 1]);
                }
            }
            lastIdx = 1 - lastIdx;
        }
        return dp[lastIdx][n - 1];
    }
}
