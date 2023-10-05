package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @date 2023/10/4 17:05
 * TODO
 */
public class Ex2466 {

    public static void main(String[] args) {
        System.out.println(new Ex2466().countGoodStrings(3, 3, 1, 1));
    }

    private static long mod = (long) (Math.pow(10, 9) + 7);

    public int countGoodStrings(int low, int high, int zero, int one) {
        long[] dp = new long[high + 1];
        dp[0] = 1;
        for (int i = 1; i < high + 1; i++) {
            if (i - zero >= 0) {
                dp[i] = dp[i - zero] % mod;
            }
            if (i - one >= 0) {
                dp[i] = (dp[i] + dp[i - one]) % mod;
            }
        }
        long ans = 0;
        for (int i = low; i < high + 1; i++) {
            ans = (ans + dp[i]) % mod;
        }
        return (int) ans;
    }
}
