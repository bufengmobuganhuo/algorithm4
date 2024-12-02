package com.mengyu.algs4.exercise.leetcode.math;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex3233 {
    /**
     * 一个数x只有2个真因数，那他的因数为1，y，x。它有奇数个因数，则x = y * y，
     * 并且y是一个质数，否则会引入其他因子。因此可以遍历[1, sqrt(r)]之间的所有质数，将它们的平方剔除
     */
    public int nonSpecialCount(int l, int r) {
        int n = (int) Math.sqrt(r) + 1;
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        int cnt = r - l + 1;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                if ((long) i * i < n) {
                    for (long j = (long) i * i; j < n; j += i) {
                        isPrime[(int) j] = false;
                    }
                }
                if (i * i <= r && i * i >= l) {
                    cnt--;
                }
            }
        }
        return cnt;
    }
}
