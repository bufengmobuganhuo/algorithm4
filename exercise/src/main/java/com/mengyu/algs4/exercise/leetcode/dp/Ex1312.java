package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex1312 {

    public int minInsertions(String s) {
        int n = s.length();
        // dp[i][j]：s[i:j]成为回文串时需要的插入次数
        int[][] dp = new int[n][n];
        // 枚举每个回文串的长度
        for (int span = 2; span < n + 1; span++) {
            for (int i = 0; i <= n - span; i++) {
                int j = i + span - 1;
                // 在s[i]的左边插入一个字符，在s[j]右边插入一个字符
                dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                if (s.charAt(i) == s.charAt(j)) {
                    // 外部相等，看内部
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
