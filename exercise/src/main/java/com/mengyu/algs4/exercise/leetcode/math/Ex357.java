package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yu zhang
 */
public class Ex357 {
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return 10;
        }
        int cnt = 9, res = 10;
        // 排列组合问题9*9*8*7...
        for (int i = 0; i < n - 1; i++) {
            cnt *= 9 - i;
            res += cnt;
        }
        return res;
    }
}
