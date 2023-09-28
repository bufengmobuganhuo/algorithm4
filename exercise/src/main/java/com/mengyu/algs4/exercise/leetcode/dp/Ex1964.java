package com.mengyu.algs4.exercise.leetcode.dp;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex1964 {

    public static void main(String[] args) {
        int[] obstacles = {5, 1, 5, 5, 1, 3, 4, 5, 1, 4};
        System.out.println(Arrays.toString(new Ex1964().longestObstacleCourseAtEachPosition2(obstacles)));
    }

    public int[] longestObstacleCourseAtEachPosition2(int[] obstacles) {
        int n = obstacles.length;
        int[] dp = new int[n];
        int[] ans = new int[n];
        int maxLen = 0, idx = 0;
        for (int obstacle : obstacles) {
            int ceilIdx = ceil(dp, 0, maxLen, obstacle);
            dp[ceilIdx] = obstacle;
            ans[idx] = ceilIdx + 1;
            if (ceilIdx == maxLen) {
                maxLen++;
            }
            idx++;
        }
        return ans;
    }

    private int ceil(int[] dp, int l, int r, int t) {
        int ceilIdx = r;
        while (l <= r) {
            int m = (l + r) / 2;
            if (dp[m] == t) {
                while (dp[m] == t) {
                    m++;
                }
                return m;
            } else if (dp[m] > t) {
                ceilIdx = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ceilIdx;
    }

    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int n = obstacles.length;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int obstacle = obstacles[i];
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (obstacle >= obstacles[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp;
    }
}
