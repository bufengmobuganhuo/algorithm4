package com.mengyu.algs4.exercise.leetcode.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuzhang
 * @date 2020/9/1 8:53 上午
 * TODO
 */
public class Ex120 {
    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> line0 = new ArrayList<>();
        line0.add(2);
        triangle.add(line0);
        List<Integer> line1 = new ArrayList<>();
        line1.add(3);
        line1.add(4);
        triangle.add(line1);
        List<Integer> line2 = new ArrayList<>();
        line2.add(6);
        line2.add(5);
        line2.add(7);
        triangle.add(line2);
        List<Integer> line3 = new ArrayList<>();
        line3.add(4);
        line3.add(1);
        line3.add(8);
        line3.add(3);
        triangle.add(line3);
        Ex120 ex120 = new Ex120();
        System.out.println(ex120.minimumTotal3(triangle));
    }

    public int minimumTotal3(List<List<Integer>> triangle) {
        int n = triangle.size();
        int dp[] = new int[n+1];
        // 从下往上走
        for(int i= n-1;i>=0;i--){
            for(int j=0;j<=i;j++){
                dp[j] = triangle.get(i).get(j)+Math.min(dp[j],dp[j+1]);
            }
        }
        return dp[0];
    }

    public int minimumTotal2(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.size()];
        int minSum = Integer.MAX_VALUE;
        // 初始条件
        dp[0] = triangle.get(0).get(0);
        int tmp = dp[0];
        for (int i = 1; i < triangle.size(); i++) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                if (j == 0) {
                    tmp = dp[j];
                    dp[j] += triangle.get(i).get(j);
                } else if (j < triangle.get(i - 1).size()) {
                    int x = triangle.get(i).get(j) + Math.min(tmp, dp[j]);
                    tmp = dp[j];
                    dp[j] = x;
                } else {
                    dp[j] = triangle.get(i).get(j) + tmp;
                }
                if (i == triangle.size() - 1 && dp[j] < minSum) {
                    minSum = dp[j];
                }
            }
        }
        return minSum == Integer.MAX_VALUE ? dp[0] : minSum;
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int[][] dp = new int[triangle.size()][triangle.size()];
        int minSum = Integer.MAX_VALUE;
        // 初始条件
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); i++) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                if (j < triangle.get(i - 1).size()) {
                    dp[i][j] = triangle.get(i).get(j) + Math.min(dp[i - 1][j], j > 0 ? dp[i - 1][j - 1] : Integer.MAX_VALUE);
                } else {
                    dp[i][j] = triangle.get(i).get(j) + dp[i - 1][j - 1];
                }

                if (i == triangle.size() - 1 && dp[i][j] < minSum) {
                    minSum = dp[i][j];
                }
            }
        }
        return minSum;
    }
}
