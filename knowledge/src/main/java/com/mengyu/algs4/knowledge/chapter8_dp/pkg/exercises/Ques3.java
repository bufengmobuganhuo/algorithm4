package com.mengyu.algs4.knowledge.chapter8_dp.pkg.exercises;

/**
 * @author yuzhang
 * @date 2021/3/8 上午10:39
 * 多重背包问题
 */
public class Ques3 {
    private static int solution(int[] weights, int[] values, int[] picks, int cap) {
        int prdNum = weights.length;
        int[] dp = new int[cap + 1];
        for (int i = 0; i < prdNum; i++) {
            int pickNum = Math.min(picks[i], cap / weights[i]);
            for (int k = 1; pickNum > 0; k <<= 1) {
                if (k > pickNum) {
                    k = pickNum;
                }
                pickNum -= k;
                for (int j = cap; j >= k * weights[i]; j--) {
                    dp[j] = Math.max(dp[j], dp[j - k * weights[i]] + k * values[i]);
                }
            }
        }
        return dp[cap];
    }
}
