package leetcode.dp;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex516 {


    public int longestPalindromeSubseq2(String s) {
        int len = s.length();
        int[][] dp = new int[2][len];
        int last = 0;
        for (int i = len - 1; i >= 0; i--) {
            dp[1 - last][i] = 1;
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[1 - last][j] = dp[last][j - 1] + 2;
                }else {
                    dp[1 - last][j] = Math.max(dp[1 - last][j - 1], dp[last][j]);
                }
            }
            last = 1 - last;
        }
        return dp[last][len - 1];
    }

    /**
     * 1. dp[i][j]: s[i...j]之间的最长回文串的长度
     * 2. 状态转移方程
     * dp[i][j] = dp[i+1][j-1] + 2  s[i] == s[j]
     * dp[i][j] = max{dp[i][j-1], dp[i+1][j]}   s[i] != s[j]
     */
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                }else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i+1][j]);
                }
            }
        }
        return dp[0][len - 1];
    }
}
