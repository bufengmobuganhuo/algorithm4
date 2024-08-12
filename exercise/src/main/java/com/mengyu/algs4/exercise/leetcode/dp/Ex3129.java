package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex3129 {

    public static void main(String[] args) {
        System.out.println(new Ex3129().numberOfStableArrays(3, 3, 2));
    }

    private static final int mod = 1000000007;

    private int cnt = 0;

    /**
     * https://leetcode.cn/problems/find-all-possible-stable-binary-arrays-i/solutions/2864829/zhao-chu-suo-you-wen-ding-de-er-jin-zhi-29q03
     */
    public int numberOfStableArrays(int zero, int one, int limit) {
        int[][][] dp = new int[zero + 1][one + 1][2];
        for (int i = 0; i < Math.min(zero, limit); i++) {
            dp[i][0][0] = 1;
        }
        for (int j = 0; j < Math.min(one, limit); j++) {
            dp[0][j][1] = 1;
        }
        for (int i = 1; i <= zero; i++) {
            for (int j = 1; j <= one; j++) {
                dp[i][j][0] = dp[i - 1][j][1] + dp[i - 1][j][0];
                if (i > limit) {
                    dp[i][j][0] -= dp[i - limit - 1][j][1];
                }
                dp[i][j][0] = (dp[i][j][0] % mod + mod) % mod;
                dp[i][j][1] = (dp[i-1][j][0] + dp[i - 1][j][1]) % mod;
                if (j > limit) {
                    dp[i][j][1] -= dp[i][j - limit - 1][0];
                }
                dp[i][j][1] = (dp[i][j][1] % mod + mod) % mod;
            }
        }

        return (dp[zero][one][0] + dp[zero][one][1]) % mod;
    }

    private void backtracking(int zeroLeft, int oneLeft, int zeroCon, int oneCon, int limit) {
        if (zeroLeft == 0 && oneLeft == 0) {
            cnt++;
            cnt %= mod;
            return;
        }
        if (zeroLeft > 0) {
            if (zeroCon < limit) {
                backtracking(zeroLeft - 1, oneLeft, zeroCon + 1, 0, limit);
            }
        }
        if (oneLeft > 0) {
            if (oneCon < limit) {
                backtracking(zeroLeft, oneLeft - 1, 0, oneCon + 1, limit);
            }
        }
    }
}
