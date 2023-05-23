package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex72 {
    /**
     * 1. dp[i][j]：word[0...i] -> word2[0...j]需要的操作次数
     * 2. dp[i][j]:
     * (1) word1[i]=word2[j]: dp[i-1][j-1]
     * (2) word1[i]!=word2[j]: min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1])+1
     * dp[i-1][j-1]: word1[0...i-1] -> word2[0...j-1]，则word1[i]需要替换为word2[j]
     * dp[i-1][j]: word1[0...i-1] -> word2[0...j], 则需要删除word1[i]
     * dp[i][j-1]: word1[0...i] -> word2[0...j-1]，则需要在word1[0...i-1]末尾插入一个字符
     * 3. 初始条件： horse -> ros
     * '' r o s
     * '' 0 1 2 3
     * h  1
     * o  2
     * r  3
     * s  4
     * e  5
     */
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        // 第一行，word1是空字符，一直向word1插入
        for (int i = 0; i < len2 + 1; i++) {
            dp[0][i] = i;
        }
        // 第一列，word2是空字符，一直删除word1
        for (int i = 0; i < len1 + 1; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i < len1 + 1; i++) {
            for (int j = 1; j < len2 + 1; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        return dp[len1][len2];
    }
}
