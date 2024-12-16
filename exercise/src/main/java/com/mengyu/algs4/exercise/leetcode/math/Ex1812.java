package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yu zhang
 */
public class Ex1812 {
    public boolean squareIsWhite(String coordinates) {
        int i = coordinates.charAt(0) - 'a' + 1, j = coordinates.charAt(1) - '0';
        if (i % 2 != 0) {
            return j % 2 == 0;
        }
        return j % 2 != 0;
    }
}
