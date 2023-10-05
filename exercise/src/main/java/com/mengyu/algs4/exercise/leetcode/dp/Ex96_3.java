package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @date 2023/10/3 16:17
 * TODO
 */
public class Ex96_3 {
    public int numTrees(int n) {
        int[] g = new int[n + 1];
        g[0] = 1;
        g[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j < i + 1; j++) {
                g[i] += g[j - 1] * g[i - j];
            }
        }
        return g[n];
    }
}
