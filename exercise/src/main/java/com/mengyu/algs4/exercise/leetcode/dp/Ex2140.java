package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @date 2023/10/5 10:56
 * TODO
 */
public class Ex2140 {
    /**
     * dp[i]: 从右向左遍历，第i道题目开始解决后得到的最大分数
     * 如果不解决第i道题目，则dp[i] = dp[i+1]
     * 如果解决第i道题目，则会有冷冻期，则dp[i] = dp[i + questions[i][1] + 1] + questions[i][0]
     * 同时为了覆盖i + questions[i][1] + 1 >= n的情况，另dp[questions.length] = 0，表示直到最后也没有过了冷冻期
     * 三者取最大值
     */
    public long mostPoints(int[][] questions) {
        int n = questions.length;
        long[] dp = new long[n + 1];
        for (int i = n - 1; i > -1; i--) {
            int point = questions[i][0];
            int brainpower = questions[i][1];
            dp[i] = Math.max(dp[i + 1], point + dp[Math.min(n, i + brainpower + 1)]);
        }
        return dp[0];
    }
}
