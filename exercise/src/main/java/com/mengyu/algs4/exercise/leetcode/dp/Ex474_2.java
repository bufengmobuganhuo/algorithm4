package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @date 2023/10/4 15:58
 * TODO
 */
public class Ex474_2 {

    public static void main(String[] args) {
        String[] strs = {"10","0001","111001","1","0"};
        System.out.println(new Ex474_2().findMaxForm(strs, 5, 3));
    }

    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String str : strs) {
            int cnt0 = 0, cnt1 = 0;
            for (char chr : str.toCharArray()) {
                if (chr == '0') {
                    cnt0++;
                } else {
                    cnt1++;
                }
            }
            for (int j = m; j - cnt0 > -1; j--) {
                for (int k = n; k - cnt1 > -1; k--) {
                    dp[j][k] = Math.max(dp[j - cnt0][k - cnt1] + 1, dp[j][k]);
                }
            }
        }
        return dp[m][n];
    }
}
