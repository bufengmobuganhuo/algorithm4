package com.mengyu.algs4.exercise.leetcode.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author yu zhang
 */
public class Ex3111 {
    public int minRectanglesToCoverPoints(int[][] points, int w) {
        Arrays.sort(points, Comparator.comparingInt(a -> a[0]));
        int cnt = 1, x1 = points[0][0] + w;
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] <= x1) {
                continue;
            }
            cnt++;
            x1 = points[i][0] + w;
        }
        return cnt;
    }
}
