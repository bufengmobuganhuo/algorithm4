package com.mengyu.algs4.interview.huawei;

import java.util.Arrays;

/**
 * @author yuzhang
 * @date 2020/8/15 2:06 下午
 * TODO
 */
public class Ex9 {
    public static void main(String[] args) {
        System.out.println(solution(3, 7));
    }

    public static int solution(int m, int n) {
        if (m < 0 || n < 0) {
            return -1;
        }
        int[][] dp = new int[n][m];
        // 初始条件
        Arrays.fill(dp[0], 1);
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                dp[i][j] += dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[n - 1][m - 1];
    }
}
