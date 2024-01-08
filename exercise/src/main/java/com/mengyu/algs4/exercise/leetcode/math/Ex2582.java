package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yu zhang
 */
public class Ex2582 {
    public int passThePillow(int n, int time) {
        int divided = time / (n - 1);
        int mod = time % (n - 1);
        if (divided % 2 == 0) {
            return mod + 1;
        } else {
            return n - mod;
        }
    }
}
