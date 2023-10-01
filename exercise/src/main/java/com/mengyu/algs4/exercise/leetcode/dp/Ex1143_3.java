package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex1143_3 {

    public static void main(String[] args) {
        System.out.println(new Ex1143_3().longestCommonSubsequence2("abcde", "ace"));
    }

    public int longestCommonSubsequence2(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[2][n];
        int lastIdx = 0;
        for (int i = 0; i < n; i++) {
            if (text1.charAt(0) == text2.charAt(i)) {
                dp[0][i] = 1;
            } else {
                dp[0][i] = i > 0 ? dp[0][i - 1] : 0;
            }
        }
        for (int i = 1; i < m; i++) {
            dp[1 - lastIdx][0] = text1.charAt(i) == text2.charAt(0) ? 1 : dp[lastIdx][0];
            for (int j = 1; j < n; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[1- lastIdx][j] = Math.max(dp[lastIdx][j - 1] + 1, Math.max(dp[lastIdx][j],
                            dp[1 - lastIdx][j - 1]));
                } else {
                    dp[1 - lastIdx][j] = Math.max(dp[lastIdx][j], dp[1 - lastIdx][j - 1]);
                }
            }
            lastIdx = 1 - lastIdx;
        }
        return dp[lastIdx][n - 1];
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, Math.max(dp[i - 1][j], dp[i][j - 1]));
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}
