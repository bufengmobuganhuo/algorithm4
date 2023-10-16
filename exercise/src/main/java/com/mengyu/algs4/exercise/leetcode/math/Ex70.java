package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yu zhang
 */
public class Ex70 {
    public int climbStairs(int n) {
        int[][] a = {{1, 1}, {1, 0}};
        int[][] res = pow(a, n);
        return res[0][0];
    }

    private int[][] pow(int[][] a, int b) {
        int[][] ret = {{1, 0}, {0, 1}};
        while (b != 0) {
            if ((b & 1) == 1) {
                ret = multi(ret, a);
            }
            b >>= 1;
            a = multi(a, a);
        }
        return ret;
    }

    private int[][] multi(int[][] a, int[][] b) {
        int a1 = a[0][0], a2 = a[0][1], a3 = a[1][0], a4 = a[1][1];
        int b1 = b[0][0], b2 = b[0][1], b3 = b[1][0], b4 = b[1][1];
        return new int[][]{{a1 * b1 + a2 * b2, a1 * b2 + a2 * b4},
                {a3 * b1 + a4 * b3, a3 * b2 + a4 * b4}
        };
    }
}
