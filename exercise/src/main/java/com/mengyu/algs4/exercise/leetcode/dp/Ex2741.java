package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex2741 {

    private static final int MOD = 1000000007;

    /**
     * 1. 将一个长度为 n−1 的特别排列新增一个整数变成长度为 n 的特别排列时，只需要关注最后两个整数是否满足题目要求即可
     * 2. 可以使用一个state的每一位表示当前nums[i]是否被选过
     * 3. dp[state][i]表示求解当前排列包含集合 state 表示的所有整数，并且最后一个整数为nums[i]时的排列数
     * state必须满足：（1）之前必须选过nums[i]，即state的第i位=1
     * 则 dp[state][i] = dp[state1][j]的和，其中state1必须不包含nums[i], state1 = state ^ (1<<i)，同时state1必须包含nums[j]，并且i!=j
     */
    public int specialPerm(int[] nums) {
        int n = nums.length;
        // 至少n位
        int[][] dp = new int[1 << n][n];
        for (int i = 0; i < n; i++) {
            // 只选nums[i]时，有1种排列
            dp[1 << i][i] = 1;
        }
        for (int state = 1; state < (1 << n); state++) {
            for (int i = 0; i < n; i++) {
                // state不包含nums[i]
                if (((state >> i) & 1) == 0) {
                    continue;
                }
                for (int j = 0; j < n; j++) {
                    // state不包含nums[j]
                    if (i == j || ((state >> j) & 1) == 0) {
                        continue;
                    }
                    // 不符合条件
                    if (nums[i] % nums[j] != 0 && nums[j] % nums[i] != 0) {
                        continue;
                    }
                    // 之前没选过nums[i]
                    dp[state][i] = (dp[state][i] + dp[state ^ (1 << i)][j]) % MOD;
                }
            }
        }
        // 要的是把所有nums[i]包含进去的排列数
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = (res + dp[(1 << n) - 1][i]) % MOD;
        }
        return res;
    }
}
