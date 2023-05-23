package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yuzhang
 * @date 2020/10/13 9:42 上午
 * TODO
 */
public class Ex808_1 {
    public double soupServings(int N) {
        N = N / 25 + (N % 25 > 0 ? 1 : 0);
        if (N >= 500) {
            return 1.0;
        }
        // dp[i][j]：A剩余i，B剩余j的概率,dp[i][j]=0.25(dp[i-4][j]+dp[i-3][j-1]+dp[i-2][j-2]+dp[i-1][j-3])
        double[][] dp = new double[N + 1][N + 1];
        // 两种汤总共有2*N份
        for (int sum = 0; sum < 2 * N + 1; sum++) {
            // 遍历汤A
            for (int i = 0; i < N + 1; i++) {
                // 汤B的剩余量
                int j = sum - i;
                // 边界条件1：汤B剩余量符合基本要求
                if (j < 0 || j > N) {
                    continue;
                }
                double ans = 0;
                // 边界条件2：汤A先分完的概率是1
                if (i == 0) {
                    ans = 1.0;
                }
                // 边界条件3：A，B同时分完的概率是0.5
                if (i == 0 && j == 0) {
                    ans = 0.5;
                }
                if (i > 0 && j > 0) {
                    ans=0.25*(dp[max(i-4)][j]
                    +dp[max(i-3)][j-1]
                    +dp[max(i-2)][j-2]
                    +dp[max(i-1)][j-3]);
                }
                dp[i][j]=ans;
            }
        }
        return dp[N][N];
    }
    private int max(int x){
        return Math.max(0,x);
    }
}
