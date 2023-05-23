package com.mengyu.algs4.exercise.leetcode.dp;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2020/9/15 9:33 上午
 * TODO
 */
public class Ex467 {
    public static void main(String[] args) {
        Ex467 ex467 = new Ex467();
        System.out.println(ex467.findSubstringInWraproundString("abd"));
    }

    /**
     * 1. 对于一个字符串p：abd，则在循环字符串中，p的子字符串有：a,b,ab,d共四个(这取决于连续字母的个数)
     * 子字符串个数=以a结尾的最长"连续"子字符串长度(1)+以b结尾的最长"连续"子字符串长度(2)+以d结尾的最长"连续"子字符串长度(1)
     * 2. dp[i]：以字母i结尾的最长连续子字符串的长度，则最终的结果就是dp[0]+dp[1]+...+dp[25]
     *
     * @param p
     * @return
     */
    public int findSubstringInWraproundString(String p) {
        if (p == null || p.length() == 0) {
            return 0;
        }
        int[] dp = new int[26];
        int cnt = 1;
        char[] chars = (" " + p).toCharArray();
        for (int i = 1; i < chars.length; i++) {
            int idx = chars[i] - 'a';
            if (isSuccessive(chars[i - 1], chars[i])) {
                cnt++;
            } else {
                cnt = 1;
            }
            dp[idx] = Math.max(dp[idx], cnt);
        }
        return Arrays.stream(dp).sum();
    }

    /**
     * 判断两个字符是否连续
     *
     * @param a
     * @param b
     * @return
     */
    private boolean isSuccessive(char a, char b) {
        if (a == 'z' && b == 'a') {
            return true;
        }
        return b - a == 1;
    }
}
