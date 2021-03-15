package chapter8_dp.pkg.exercises;

/**
 * @author yuzhang
 * @date 2021/3/8 上午10:58
 * 混合背包问题
 */
public class Ques4 {

    private static int solution(int[] weights, int[] values, int[] picks, int cap) {
        int prdNum = weights.length;
        int[] dp = new int[cap + 1];
        for (int i = 0; i < prdNum; i++) {
            if (picks[i] == 0) {
                for (int j = weights[i]; j <= cap; j++) {
                    dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
                }
            } else {
                int pickNum = picks[i];
                for (int k = 1; pickNum > 0; k <<= 1) {
                    if (pickNum < k) {
                        k = pickNum;
                    }
                    pickNum -= k;
                    for (int j = cap; j >= k * weights[i]; j--) {
                        dp[j] = Math.max(dp[j],dp[j-k*weights[i]]+k*values[i]);
                    }
                }
            }
        }
        return dp[cap];
    }
}
