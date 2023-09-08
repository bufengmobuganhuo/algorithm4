package com.mengyu.algs4.exercise.chapter6_dp.bag;

/**
 * @author yu zhang
 */
public class No1 {

    public static void main(String[] args) {
        int[] weights = {2, 6, 3, 4};
        System.out.println(new No1().solution2(weights, weights, 7));
    }
    public int solution(int[] weights, int[] vals, int capacity) {
        int[] dp = new int[capacity + 1];
        for (int i = 1; i <= weights.length; i++) {
            int weight = weights[i - 1];
            int val = vals[i - 1];
            for (int j = capacity; j >= 0; j--) {
                if (j - weight >= 0) {
                    dp[j] = Math.max(dp[j], dp[j - weight] + val);
                }
            }
        }
        return dp[capacity];
    }

    // 恰好装满时，获利最大
    public int solution2(int[] weights, int[] vals, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];
        // 放入0个物品，获利为0
        dp[0][0] = 0;
        // 除了上述情况，(容量为0,放入几个物品 || 容量不为0，但是不放入物品时) 都是非法情况
        for (int i = 1; i < n + 1; i++) {
            dp[i][0] = Integer.MIN_VALUE;
        }
        for (int i = 1; i < capacity + 1; i++) {
            dp[0][i] = Integer.MIN_VALUE;
        }
        int ans = 0;
        for (int i = 1; i < n + 1; i++) {
            int weight = weights[i - 1];
            int val = vals[i - 1];
            for (int j = 1; j < capacity + 1; j++) {
                if (j - weight >= 0) {
                    int lastMax = Integer.MIN_VALUE;
                    for (int k = 0; k < i; k++) {
                        lastMax = Math.max(lastMax, dp[k][j - weight]);
                    }
                    dp[i][j] = lastMax == Integer.MIN_VALUE ? Integer.MIN_VALUE : lastMax + val;
                } else {
                    dp[i][j] = Integer.MIN_VALUE;
                }
            }
            ans = Math.max(ans, dp[i][capacity]);
        }

        return ans;
    }
}
