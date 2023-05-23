package com.mengyu.algs4.knowledge.chapter8_dp.pkg;

/**
 * @author yuzhang
 * @date 2020/9/4 9:01 上午
 * 第二讲：完全背包问题
 */
public class Question2 {
    public static void main(String[] args) {
        int[] values = {3000, 2000, 1500};
        int[] weights = {4, 3, 1};
        System.out.println(solution1(weights, values, 4));
        System.out.println(solution2(weights, values, 4));
    }

    /**
     * O(VN)的解法
     * @param weights
     * @param values
     * @param cap
     * @return
     */
    public static int solution2(int[] weights, int[] values, int cap) {
        if (weights == null || weights.length == 0) {
            return 0;
        }
        int[] dp = new int[cap + 1];
        for (int i = 1; i < weights.length + 1; i++) {
            // j正序增加，在选第i件物品时，是依赖于一个可能已经选过第i件物品的子结果
            for (int j = weights[i - 1]; j < cap + 1; j++) {
                dp[j] = Math.max(dp[j], dp[j - weights[i - 1]] + values[i - 1]);
            }
        }
        return dp[cap];
    }

    /**
     * 基本解法
     *
     * @param weights
     * @param values
     * @param cap
     * @return
     */
    public static int solution1(int[] weights, int[] values, int cap) {
        if (weights == null || weights.length == 0) {
            return 0;
        }
        int[][] dp = new int[weights.length + 1][cap + 1];
        for (int i = 1; i < weights.length + 1; i++) {
            for (int j = 1; j < cap + 1; j++) {
                int k = 0;
                // 由于商品有无限件，所以以取0件，1件开始尝试，取最大值
                while (j - k * weights[i - 1] >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * weights[i - 1]] + k * values[i - 1]);
                    k++;
                }
            }
        }
        return dp[weights.length][cap];
    }
}
