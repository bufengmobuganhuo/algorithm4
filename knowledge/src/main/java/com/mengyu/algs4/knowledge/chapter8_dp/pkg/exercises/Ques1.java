package com.mengyu.algs4.knowledge.chapter8_dp.pkg.exercises;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2021/3/8 上午10:22
 * 01背包问题
 */
public class Ques1 {
    /**
     * 不是恰好装满的情况
     *
     * @param weights
     * @param values
     * @param cap
     * @return
     */
    private static int solution(int[] weights, int[] values, int cap) {
        int prdNum = weights.length;
        int[] dp = new int[cap + 1];
        for (int i = 1; i < prdNum + 1; i++) {
            for (int j = cap; j >= weights[i-1]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weights[i - 1]] + values[i - 1]);
            }
        }
        return dp[cap];
    }

    /**
     * 要完全装满，则dp[0][0]=0,dp[1][0]=-∞...
     *
     * @param weights
     * @param values
     * @param cap
     * @return
     */
    private static int solution2(int[] weights, int[] values, int cap) {
        int prdNum = weights.length;
        int[] dp = new int[cap + 1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        for (int i = 1; i < prdNum + 1; i++) {
            for (int j = cap; j >= weights[i-1]; j--) {
                dp[j] = Math.max(dp[j],dp[j-weights[i-1]]+values[i-1]);
            }
        }
        return dp[cap];
    }
}
