package com.mengyu.algs4.knowledge.chapter8_dp.pkg;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2020/9/8 10:42 上午
 * TODO
 */
public class Question5 {
    public static void main(String[] args) {
        int[] values = {7000, 6000, 1500};
        int[] weights = {4, 3, 1};
        System.out.println(maxPickLimit(weights, values, 5, 4));
    }

    /**
     * 物品总个数限制
     * @param weights 每个物品的重量
     * @param values 每个物品的价值
     * @param cap 背包容量
     * @param limitPick 最多总共能拿${limitPick}个物品
     * @return
     */
    public static int maxPickLimit(int[] weights,int[] values,int cap,int limitPick){
        // 第二维度的费用,每次拿一个，则每个物品的费用=1
        int[] weights2= new int[weights.length];
        Arrays.fill(weights2,1);
        return solution(weights,weights2,values,cap,limitPick);
    }

    /**
     *
     * @param weights1 第一维度的费用
     * @param weights2 第二维度的费用
     * @param values 能获得的价值
     * @param cap1 第一维度的背包大小
     * @param cap2 第二维度的背包大小
     * @return
     */
    public static int solution(int[] weights1,int[] weights2,int[] values,int cap1,int cap2){
        int prdNum = weights1.length;
        int[][] dp = new int[cap1+1][cap2+1];
        for (int i = 0; i < prdNum; i++) {
            // 第一维度
            for (int j = cap1; j >= weights1[i]; j--) {
                // 第二维度
                for (int k = cap2; k >= weights2[i]; k--) {
                    dp[j][k] = Math.max(dp[j][k],dp[j-weights1[i]][k-weights2[i]]+values[i]);
                }
            }
        }
        return dp[cap1][cap2];
    }
}
