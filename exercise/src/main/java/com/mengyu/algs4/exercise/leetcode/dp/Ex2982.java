package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex2982 {

    public static void main(String[] args) {
        System.out.println(new Ex2982().maximumLength("aaaa"));
    }

    /**
     * 1. 找到字符串中，所有的最长连续子字符串
     * 2. 如果一个子字符串的长度为n，则长度为n - 2的子字符串肯定能出现3次。所以对于每个字符，它所有的最长连续子字符串中第三长的就是备选答案
     * @param s
     * @return
     */
    public int maximumLength(String s) {
        int n = s.length();
        // 以s[i]为结尾的最长特殊子字符串的长度
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = 1;
            }
        }
        // 每个字符维护它前3大的最长连续子字符串的长度
        int[][] group = new int[26][3];
        int ans = -1;
        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            int len = dp[i];
            int j = 2;
            while (j >= 0 && len > group[idx][j]) {
                int tmp = group[idx][j];
                group[idx][j] = len;
                if (j + 1 < 3) {
                    group[idx][j + 1] = tmp;
                }
                j--;
            }
            ans = Math.max(ans, group[idx][2]);
        }
        return ans <= 0 ? -1 : ans;
    }
}
