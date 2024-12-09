package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yu zhang
 */
public class Ex3001 {
    public int minMovesToCaptureTheQueen(int a, int b, int c, int d, int e, int f) {
        // 白色车和黑皇后在同一行，并且二者之间没有白色象
        if (a == e && ((a != c) || (b < f && (f < d || d < b)) || (b > f && (b < d || d < f)))) {
            return 1;
        } else if (b == f && ((b != d) || (a < e && (e < c || c < a )) || (a > e && (c < e || c > a)))) {
            return 1;
        }
        // 象、皇后处在同一条对角线，且中间没有车
        if (Math.abs(c - e) == Math.abs(d - f) && ((c - e) * (b - f) != (a - e) * (d - f)
                || a < Math.min(c, e) || a > Math.max(c, e))) {
            return 1;
        }
        return 2;
    }
}
