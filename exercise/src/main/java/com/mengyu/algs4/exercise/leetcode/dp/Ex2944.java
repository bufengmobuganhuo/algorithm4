package com.mengyu.algs4.exercise.leetcode.dp;

import java.util.PriorityQueue;

/**
 * @author yu zhang
 */
public class Ex2944 {

    public static void main(String[] args) {
        int[] prices = {3, 1, 2};
        System.out.println(new Ex2944().minimumCoins2(prices));
    }

    public int minimumCoins2(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2];
        PriorityQueue<int[]> que = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] != o2[1]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });
        for (int i = 1; i <= n; i++) {
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + prices[i - 1];
            dp[i][0] = Integer.MAX_VALUE;
            int min = (i + 1) / 2;
            while (!que.isEmpty() && que.peek()[0] < min) {
                que.poll();
            }
            if (!que.isEmpty()) {
                dp[i][0] = que.peek()[1];
            }
            que.offer(new int[]{i, dp[i][1]});
        }
        return Math.min(dp[n][0], dp[n][1]);
    }

    public int minimumCoins(int[] prices) {
        int n = prices.length;
        long[][] dp = new long[n + 1][2];
        for (int i = 1; i <= n; i++) {
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + prices[i - 1];
            dp[i][0] = Integer.MAX_VALUE;
            for (int j = (i + 1) / 2; j < i; j++) {
                dp[i][0] = Math.min(dp[i][0], dp[j][1]);
            }
        }
        return (int) Math.min(dp[n][0], dp[n][1]);
    }
}
