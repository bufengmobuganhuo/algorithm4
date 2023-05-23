package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex552 {
    private static final int MOD = 1000000007;



    /**
     * 1. dp[i][j][k]: 截止到第i天，有j个缺勤，k个连续迟到，能获得奖励的情况数量（可以知道 0<=j<2 && 0<=k<3才是有效的，其他都无法获得奖励）
     * 2. 状态转移：
     * （1）如果第i天是P（出勤），则"连续迟到"被清零（k = 0）；且回溯到i-1天，只有0 <= j < 3的情况能获得奖励
     * dp[i][j][0] = dp[i][j][0] + sum(dp[i-1][j][k])   0<=j<2 && 0<=k<3
     * （2）如果第i天是A（缺勤），则"连续迟到"被清零（k = 0）缺勤的个数+1；且回溯到第i-1天，只有j = 0的情况能获得奖励
     * dp[i][1][0] = dp[i][1][0] + sum(dp[i-1][0][k])  0<=k<3
     * （3）如果第i天是L（迟到），则"连续迟到"的数量+1；且回溯到第i-1天，只有 0<=j<2 && 0<=k-1<2的情况能获得奖励
     * dp[i][j][k] = dp[i][j][k] + sum(dp[i-1][j][k-1])  0<=j<2 && 1<=k<3
     * 3. 初始条件，第0天，没有缺勤，没有迟到，没有出勤，获得奖励为1 ---> dp[0][0][0] = 1
     */
    public int checkRecord(int n) {
        int[][][] dp = new int[n + 1][2][3];
        dp[0][0][0] = 1;
        for (int i = 1; i < n + 1; i++) {
            // 第i天是P
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][0] = (dp[i][j][0] + dp[i - 1][j][k]) % MOD;
                }
            }
            // 第i天是A
            for (int k = 0; k < 3; k++) {
                dp[i][1][0] = (dp[i][1][0] + dp[i - 1][0][k]) % MOD;
            }
            // 第i天是L
            for (int j = 0; j < 2; j++) {
                for (int k = 1; k < 3; k++) {
                    dp[i][j][k] = (dp[i][j][k] + dp[i - 1][j][k - 1]) % MOD;
                }
            }
        }
        // 最终的结果是第n天，所有符合条件的情况相加
        int sum = 0;
        for (int j = 0; j < 2; j++) {
            for (int k = 0; k < 3; k++) {
                sum = (sum + dp[n][j][k]) % MOD;
            }
        }
        return sum;
    }
}
