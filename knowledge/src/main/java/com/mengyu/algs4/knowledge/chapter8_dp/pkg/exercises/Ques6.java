package com.mengyu.algs4.knowledge.chapter8_dp.pkg.exercises;

/**
 * @author yuzhang
 * @date 2021/3/8 上午11:17
 * 分组背包问题
 */
public class Ques6 {
    private static int solution(int[][] weights, int[][] values, int cap) {
        int groupNum = weights.length;
        int[] dp = new int[cap + 1];
        for (int i = 0; i < groupNum; i++) {
            for (int j = cap; j >= 0; j--) {
                for (int k = 0; k < weights[i].length && j >= weights[i][k]; k++) {
                    dp[j] = Math.max(dp[j], dp[j - weights[i][k]] + values[i][k]);
                }
            }
        }
        return dp[cap];
    }
}
