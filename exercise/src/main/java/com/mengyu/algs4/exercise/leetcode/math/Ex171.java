package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yu zhang
 */
public class Ex171 {
    public static void main(String[] args) {

    }
    public int titleToNumber(String columnTitle) {
        int bit = 0;
        int ans = 0;
        for (int i = columnTitle.length() - 1; i >= 0; i--) {
            char chr = columnTitle.charAt(i);
            ans += (chr - 'A' + 1) * pow(26, bit++);
        }
        return ans;
    }

    private int pow(int a, int b) {
        if (b == 0) {
            return 0;
        }
        int res = 1;
        while (b != 0) {
            if ((b & 1) == 1) {
                res *= a;
            }
            a *= a;
            b = b >> 1;
        }
        return res;
    }
}
