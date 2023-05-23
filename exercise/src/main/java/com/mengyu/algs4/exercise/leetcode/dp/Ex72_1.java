package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex72_1 {
    public static void main(String[] args) {
        Ex72_1 ex72_1 = new Ex72_1();
        System.out.println(ex72_1.minDistance2("horse", "ros"));
    }
    // 空间优化
    public int minDistance2(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        int[][] dp = new int[2][len2 + 1];
        for (int i = 0; i < len2 + 1; i++) {
            dp[0][i] = i;
        }
        int last = 0;
        int now = 1;
        for (int i = 1; i < len1 + 1; i++) {
            dp[now][0] = i;
            for (int j = 1; j < len2 + 1; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[now][j] = dp[last][j - 1];
                } else {
                    dp[now][j] = Math.min(dp[last][j], Math.min(dp[now][j - 1], dp[last][j - 1])) + 1;
                }
            }
            last = 1 - last;
            now = 1 - now;
        }
        return dp[last][len2];
    }

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
                    // min{删除word1[i]，向word1插入一个字符，替换word1的一个字符}
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                }
            }
        }
        return dp[len1][len2];
    }
}
