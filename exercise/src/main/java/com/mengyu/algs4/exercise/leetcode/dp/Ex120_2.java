package com.mengyu.algs4.exercise.leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex120_2 {

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3, 4));
        triangle.add(Arrays.asList(6, 5, 7));
        triangle.add(Arrays.asList(4, 1, 8, 3));
        System.out.println(new Ex120_2().minimumTotal(triangle));
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 1) {
            return triangle.get(0).get(0);
        }
        int m = triangle.size(), n = triangle.size();
        int[] dp = new int[n];
        dp[0] = triangle.get(0).get(0);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < m; i++) {
            for (int j = triangle.get(i).size() - 1; j >= 1; j--) {
                if (j < triangle.get(i - 1).size()) {
                    dp[j] = Math.min(dp[j], dp[j - 1]) + triangle.get(i).get(j);
                } else {
                    dp[j] = dp[j - 1] + triangle.get(i).get(j);
                }
                if (i == m - 1) {
                    min = Math.min(dp[j], min);
                }
            }
            dp[0] += triangle.get(i).get(0);
            if (i == m - 1) {
                min = Math.min(dp[0], min);
            }
        }
        return min;
    }
}
