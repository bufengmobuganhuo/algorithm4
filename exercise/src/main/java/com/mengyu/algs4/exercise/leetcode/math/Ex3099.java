package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yu zhang
 */
public class Ex3099 {
    public int sumOfTheDigitsOfHarshadNumber(int x) {
        int sum = 0, tmp = x;
        while (x != 0) {
            sum = sum + (x % 10);
            x /= 10;
        }
        return tmp % sum == 0 ? sum : -1;
    }
}
