package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex2266 {

    public static void main(String[] args) {
        System.out.println(new Ex2266().countTexts("222222222222222222222222222222222222"));
    }

    private static final int mod = (int) 1.000000007E9;

    private static final int[] charMap = {0, 0, 3, 3, 3, 3, 3, 4, 3, 4};

    public int countTexts(String pressedKeys) {
        int len = 1;
        long res = 1;
        char chr = pressedKeys.charAt(0);
        for (int i = 1; i < pressedKeys.length(); i++) {
            char key = pressedKeys.charAt(i);
            if (chr != key) {
                res = (res * dp(len, chr - '0')) % mod;
                chr = key;
                len = 1;
            } else {
                len++;
            }
        }
        res = (res * dp(len, chr - '0')) % mod;
        return (int) res;
    }

    private int dp(int len, int num) {
        int[] dp = new int[len + 1];
        dp[0] = 1;
        int chrCnt = charMap[num];
        for (int i = 1; i < len + 1; i++) {
            for (int j = 1; j <= chrCnt && j <= i; j++) {
                dp[i] = (dp[i] + dp[i - j]) % mod;
            }
        }
        return dp[len];
    }
}
