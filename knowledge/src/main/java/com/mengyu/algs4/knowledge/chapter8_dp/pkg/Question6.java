package com.mengyu.algs4.knowledge.chapter8_dp.pkg;

/**
 * @author yuzhang
 * @date 2020/9/8 11:09 上午
 * TODO
 */
public class Question6 {
    public static void main(String[] args) {
        int[][] weights={
                {4},
                {3},
                {1}
        };
        int[][] values={
                {3000},
                {2000},
                {1500}
        };
        System.out.println(solution(weights,values,4));
    }

    /**
     * @param weights weights[i][j]，第i组内的第j个物品的消耗
     * @param values
     * @param cap
     * @return
     */
    public static int solution(int[][] weights, int[][] values, int cap) {
        int[] dp = new int[cap + 1];
        int groupNum = weights.length;
        for (int i = 0; i < groupNum; i++) {
            for (int j = cap; j >= 0; j--) {
                // 在每个组内找到最大价值的物品
                for (int k = 0; k < weights[i].length && j - weights[i][k] >= 0; k++) {
                    dp[j] = Math.max(dp[j], dp[j - weights[i][k]] + values[i][k]);
                }
            }
        }
        return dp[cap];
    }
}
