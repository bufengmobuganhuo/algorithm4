package com.mengyu.algs4.exercise.interview.dp;

import java.util.Scanner;

/**
 * @date 2024/5/25 21:17
 * TODO
 */
public class Ex {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String input = in.nextLine();
            String[] params = input.split(" ");
            String a = params[0], b = params[1];
            int m = b.length(), n = a.length();
            int[][] dp = new int[m + 1][n + 1];
            for (int i = 0; i <= m; i++) {
                dp[i][0] = i;
            }
            for (int i = 0; i <= n; i++) {
                dp[0][i] = i;
            }
            for (int i = 1; i <= m; i++) {
                for(int j = 1; j <= n; j++) {
                    int leftDist = dp[i][j - 1];
                    int rightDist = dp[i - 1][j];
                    int dist = b.charAt(i - 1) == a.charAt(j - 1) ? dp[i - 1][j - 1] :
                            Integer.MAX_VALUE;
                    dp[i][j] = Math.min(leftDist, Math.min(rightDist, dist)) + 1;
                }
            }
            System.out.println(dp[m][n]);
        }
    }
}
