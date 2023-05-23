package com.mengyu.algs4.exercise.leetcode.dp;

import java.util.Arrays;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex120_1 {
    public static void main(String[] args) {
        System.out.println(minimumTotal(Arrays.asList(Arrays.asList(2), Arrays.asList(3, 4), Arrays.asList(6, 5 ,7),Arrays.asList(4, 1, 8, 3))));
    }
    public static int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j + 1]);
            }
        }
        return dp[0];
    }
}
