package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yu zhang
 */
public class Ex2520 {
    public int countDigits(int num) {
        int cnt = 0;
        int tmp = num;
        while (tmp != 0) {
            int digit = tmp % 10;
            cnt = num % digit == 0 ? cnt + 1 : cnt;
            tmp /= 10;
        }
        return cnt;
    }
}
