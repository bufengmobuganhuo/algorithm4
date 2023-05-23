package com.mengyu.algs4.knowledge.chapter9_Math;

/**
 * @author yu zhang
 */
public class QuickCalculate {
    public static void main(String[] args) {
        System.out.println(quickMulti(2, 3));
        System.out.println(quickMulti(1, 3));
        System.out.println(quickMulti(5, 6));
        System.out.println(quickMulti(-3, 2));
        System.out.println(quickMulti(-3, 3));
    }

    private static int mod = 10000007;

    // a^b
    private static int quickPow(int a, int b) {
        if (b == 0) {
            return 1;
        }
        int res = 1;
        while (b != 0) {
            if ((b & 1) != 0) {
                res *= a;
                res %= mod;
            }
            a *= a;
            a %= mod;
            b >>= 1;
        }
        return res % mod;
    }

    // a * b
    private static int quickMulti(int a, int b) {
        if (b == 0) {
            return 0;
        }
        int res = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                res += a;
                res %= mod;
            }
            if (b != 1) {
                a += a;
                a %= mod;
            }
            b >>= 1;
        }
        return res % mod;
    }
}
