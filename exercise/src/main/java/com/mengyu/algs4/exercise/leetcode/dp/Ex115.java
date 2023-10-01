package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex115 {
    /**
     * dp[i][j]：s[i:]中，t[j:]出现的次数
     * 边界条件：当i = m时，s为空字符串，那dp[n][j]=0; 当j = n时，t为空字符串，空字符串是任何子序列的子串，那dp[i][n] = 1
     * 对于dp[i][j]：
     * 当s[i] == t[j]时，dp[i][j] = dp[i+1][j+1](使用当前字符匹配，则剩下的从后面开始) + dp[i+1][j](不使用当前字符匹配)
     * 当s[i] != t[j]时，dp[i][j] = dp[i+1][j](无法使用当前字符匹配，用后面的来匹配)
     */
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        // 长度不够，无法匹配
        if (n > m) {
            return 0;
        }
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) {
            dp[i][n] = 1;
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }
        return dp[0][0];
    }
}
