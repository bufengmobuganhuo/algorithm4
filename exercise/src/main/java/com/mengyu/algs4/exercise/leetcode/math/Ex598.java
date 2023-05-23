package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yuzhang
 * @date 2021/11/28 9:46 上午
 * TODO
 */
public class Ex598 {
    public int maxCount(int m, int n, int[][] ops) {
        if (ops == null || ops.length == 0) {
            return m * n;
        }
        int minI = m, minJ = n;
        for (int[] op : ops) {
            minI = Math.min(minI, op[0]);
            minJ = Math.min(minJ, op[1]);
        }
        return minI * minJ;
    }
}
