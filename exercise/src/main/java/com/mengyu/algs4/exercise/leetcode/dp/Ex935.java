package com.mengyu.algs4.exercise.leetcode.dp;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex935 {

    private int mod = 10^9 + 7;

    // y的集合
    private int[][] preSet = {
            {4, 6},
            {6, 8},
            {7, 9},
            {4, 8},
            {0, 3, 9},
            {},
            {0, 1, 7},
            {2, 6},
            {1, 3},
            {2, 4},
    };

    /**
     * 1. dp[n][x]：表示经过n次跳跃到达数字x时，有几种方式
     * 第n步的方案数取决于dp[n-1][y](y是所有能从y经过合法跳跃后到达x的集合)
     * 因此n的问题可以转化成n-1，n-1可以转化成n-2...
     * 2. 初始条件：dp[1][x]=1
     */
    public int knightDialer(int n) {
        int[][] dp = new int[2][10];
        Arrays.fill(dp[1], 1);
        int idx = 0;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                for (int pre : preSet[j]) {
                    dp[idx][j] = (dp[idx][j] + dp[1- idx][pre]) % mod;
                }
            }
            idx = 1 - idx;
        }
        int res = 0;
        for (int x : dp[idx]) {
            res = (res + x) % mod;
        }
        return res;
    }
}
