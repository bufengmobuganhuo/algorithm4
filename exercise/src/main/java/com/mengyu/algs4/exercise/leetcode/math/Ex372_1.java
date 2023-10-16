package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yu zhang
 */
public class Ex372_1 {

    public static void main(String[] args) {
        System.out.println(new Ex372_1().superPow(2147483647, new int[]{2, 0, 0}));
    }

    private final static int mod = 1337;

    public int superPow(int a, int[] b) {
        int n = b.length;
        long res = 1;
        for (int i = 0; i < n; i++) {
            int num = b[i];
            int ret = pow(a, num);
            res *= ret;
            if (i != n - 1) {
                res  = pow(res, 10);
            }
            res %= mod;
        }
        return (int) res;
    }

    private int pow(long a, int b) {
        int ret = 1;
        while (b != 0) {
            if ((b & 1) == 1) {
                ret *= a;
                ret %= mod;
            }
            b >>= 1;
            a *= a;
            a %= mod;
        }
        return ret % mod;
    }
}
