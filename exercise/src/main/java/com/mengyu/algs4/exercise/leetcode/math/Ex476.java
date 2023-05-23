package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yu zhang
 */
public class Ex476 {
    public int findComplement(int num) {
        int bit = 1;
        int res = 0;
        while (num != 0) {
            if ((num & 1) == 0) {
                res += bit;
            }
            num >>= 1;
            bit *= 2;
        }
        return res;
    }
}
