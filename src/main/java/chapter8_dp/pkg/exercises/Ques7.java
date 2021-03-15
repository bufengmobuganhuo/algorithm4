package chapter8_dp.pkg.exercises;

import javafx.util.Pair;

/**
 * @author yuzhang
 * @date 2021/3/8 上午11:21
 * 依赖背包问题
 */
public class Ques7 {
    private static int solution(Pair<Integer, Integer[]>[] weights, Pair<Integer, Integer[]>[] values, int cap) {
        int prdNum = weights.length;
        int[] dp = new int[cap + 1];
        for (int i = 0; i < prdNum; i++) {
            int[] max = dp(weights[i].getValue(), values[i].getValue(), cap);
            for (int j = cap; j >= 0; j--) {
                int weight = weights[i].getKey();
                int value = values[i].getKey();
                for (int k = weight; k <= j; k++) {
                    dp[j] = Math.max(dp[j], dp[j - k] + max[k - weight] + value);
                }
            }
        }
        return dp[cap];
    }

    private static int[] dp(Integer[] weights, Integer[] values, int cap) {
        int prdNum = weights.length;
        int[] dp = new int[cap + 1];
        for (int i = 0; i < prdNum; i++) {
            for (int j = cap; j >= weights[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
            }
        }
        return dp;
    }
}
