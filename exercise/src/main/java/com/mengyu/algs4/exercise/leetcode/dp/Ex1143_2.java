package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex1143_2 {
    public static void main(String[] args) {
        Ex1143_2 ex1143_2 = new Ex1143_2();
        System.out.println(ex1143_2.longestCommonSubsequence("abcde", "ace"));
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length();
        int[][] dp = new int[2][len2];
        dp[0][0] = text1.charAt(0) == text2.charAt(0) ? 1 : 0;
        // 初始条件
        for (int i = 1; i < len2; i++) {
            if (text1.charAt(0) == text2.charAt(i)) {
                dp[0][i] = 1;
            } else {
                dp[0][i] = dp[0][i - 1];
            }
        }
        int lastRow = 0;
        for (int i = 1; i < len1; i++) {
            dp[1-lastRow][0] = text1.charAt(i) == text2.charAt(0) ? 1 : dp[lastRow][0];
            for (int j = 1; j < len2; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[1 - lastRow][j] = dp[lastRow][j - 1] + 1;
                } else {
                    dp[1 - lastRow][j] = Math.max(dp[1 - lastRow][j - 1], dp[lastRow][j]);
                }
            }
            lastRow = 1 - lastRow;
        }
        return dp[lastRow][len2 - 1];
    }
}
