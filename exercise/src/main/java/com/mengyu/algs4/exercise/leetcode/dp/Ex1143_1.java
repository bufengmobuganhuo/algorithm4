package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yuzhang
 * @date 2021/3/9 上午9:25
 * TODO
 */
public class Ex1143_1 {
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length();
        int[][] dp = new int[len1][len2];
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = ((i > 0 && j > 0) ? dp[i - 1][j - 1] : 0) + 1;
                } else {
                    dp[i][j] = Math.max((i > 0 ? dp[i - 1][j] : 0), (j > 0 ? dp[i][j - 1] : 0));
                }
            }
        }
        return dp[len1-1][len2];
    }
}
