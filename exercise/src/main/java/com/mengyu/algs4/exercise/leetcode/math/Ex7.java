package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yuzhang
 * @date 2020/12/8 下午4:03
 * TODO
 */
public class Ex7 {
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            // 因为后面rev = rev *10 + pop,根据这个可以反推出来pop需要满足的条件
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > Integer.MAX_VALUE % 10)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < Integer.MIN_VALUE % 10)) {
                return 0;
            }
            rev = rev * 10 + pop;
        }
        return rev;
    }
}
