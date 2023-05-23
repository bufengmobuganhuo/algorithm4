package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yu zhang
 */
public class Ex504 {
    public String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }
        boolean isNegative = false;
        if (num < 0) {
            isNegative = true;
            num *= -1;
        }
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.append(num % 7);
            num /= 7;
        }
        return ((isNegative ? "-" : "") + sb.reverse().toString());
    }
}
