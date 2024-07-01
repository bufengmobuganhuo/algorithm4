package com.mengyu.algs4.exercise.leetcode.greedy;

/**
 * @author yu zhang
 */
public class Ex2734 {
    public String smallestString(String s) {
        StringBuilder sb = new StringBuilder(s);
        int n = sb.length();
        boolean started = false;
        for (int i = 0; i < n; i++) {
            char chr = sb.charAt(i);
            char next = next(chr);
            if (chr < next && started) {
                break;
            } else if (chr > next) {
                started = true;
                sb.setCharAt(i, next);
            }
        }
        if (!started) {
            sb.setCharAt(n - 1, next(sb.charAt(n - 1)));
        }
        return sb.toString();
    }

    private char next(char chr) {
        if (chr == 'a') {
            return 'z';
        }
        return (char) (chr - 1);
    }
}
