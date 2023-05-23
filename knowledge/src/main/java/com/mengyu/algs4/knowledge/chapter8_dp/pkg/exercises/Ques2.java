package com.mengyu.algs4.knowledge.chapter8_dp.pkg.exercises;

/**
 * @author yuzhang
 * @date 2021/3/8 上午10:33
 * 完全背包问题
 */
public class Ques2 {

    private static int solution(int[] weights, int[] values, int cap) {
        int prdNum = weights.length;
        int[] dp = new int[cap + 1];
        for (int i = 1; i < prdNum + 1; i++) {
            for (int j = weights[i - 1]; j < cap + 1; j++) {
                dp[j] = Math.max(dp[j], dp[j - weights[i - 1]] + values[i - 1]);
            }
        }
        return dp[cap];
    }
}
