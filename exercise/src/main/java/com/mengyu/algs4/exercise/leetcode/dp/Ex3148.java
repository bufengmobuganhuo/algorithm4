package com.mengyu.algs4.exercise.leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex3148 {

    public static void main(String[] args) {
        List<Integer> row1 = Arrays.asList(4, 3, 2);
        List<Integer> row2 = Arrays.asList(3, 2, 1);
        List<List<Integer>> grid = new ArrayList<>();
        grid.add(row1);
        grid.add(row2);
        System.out.println(new Ex3148().maxScore(grid));
    }

    public int maxScore(List<List<Integer>> grid) {
        int m = grid.size(), n = grid.get(0).size();
        int[] dp = new int[n];
        dp[n - 1] = grid.get(m - 1).get(n - 1);
        int ans = Integer.MIN_VALUE;
        for (int i = n - 2; i >= 0; i--) {
            ans = Math.max(ans, dp[i + 1] - grid.get(m - 1).get(i));
            dp[i] = Math.max(grid.get(m - 1).get(i), dp[i + 1]);
        }
        for (int i = m - 2; i >= 0; i--) {
            ans = Math.max(ans, dp[n - 1] - grid.get(i).get(n - 1));
            dp[n - 1] = Math.max(dp[n - 1], grid.get(i).get(n - 1));
            for (int j = n - 2; j >= 0; j--) {
                ans = Math.max(ans, dp[j] - grid.get(i).get(j));
                ans = Math.max(ans, dp[j + 1] - grid.get(i).get(j));
                dp[j] = Math.max(dp[j + 1], Math.max(dp[j], grid.get(i).get(j)));
            }
        }
        return ans;
    }
}
