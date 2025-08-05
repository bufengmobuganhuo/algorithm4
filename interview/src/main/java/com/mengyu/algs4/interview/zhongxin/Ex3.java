package com.mengyu.algs4.interview.zhongxin;

import java.util.Scanner;

/**
 * @author yu zhang
 */
public class Ex3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int m = scanner.nextInt();
            for (int i = 0; i < m; i++) {
                int n = scanner.nextInt();
                int[][] abcd = new int[n + 1][4];
                for (int j = 1; j <= n; j++) {
                    for (int k = 0; k < 4; k++) {
                        abcd[j][k] = scanner.nextInt();
                    }
                }
                process(abcd);
            }
        }
    }

    private static void process(int[][] abcd) {
        int n = abcd.length;
        int[][] dp = new int[n][2];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - abcd[i - 1][3]) + abcd[i][0];
            dp[i][1] = Math.max(dp[i - 1][0] - abcd[i - 1][1], dp[i - 1][1]) + abcd[i][2];
        }
        System.out.println(Math.max(dp[n - 1][0], dp[n - 1][1]));
    }
}
