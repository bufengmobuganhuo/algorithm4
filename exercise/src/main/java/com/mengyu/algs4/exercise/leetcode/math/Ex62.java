package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yuzhang
 * @date 2020/12/10 上午9:32
 * TODO
 */
public class Ex62 {
    /**
     * 数学思路：
     * 1. 对于m行，n列，从左上角到右下角，需要向右移动n-1次，向下移动m-1次，共移动m+n-2次
     * 2. 从这m+n-2次中选出m-1次向下移动的"组合"数
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        long ans = 1;
        for (int x = n, y = 1; y < m; x++, y++) {
            ans = ans * x / y;
        }
        return (int) ans;
    }
}
