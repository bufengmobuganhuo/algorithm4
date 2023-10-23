package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yu zhang
 */
public class Ex1823_1 {
    public int findTheWinner(int n, int k) {
        int pos = 0;
        for (int i = 1; i < n; i++) {
            pos = (pos + k) % (i + 1);
        }
        return pos + 1;
    }
}
