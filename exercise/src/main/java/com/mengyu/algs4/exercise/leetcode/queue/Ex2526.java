package com.mengyu.algs4.exercise.leetcode.queue;

/**
 * @author yu zhang
 */
public class Ex2526 {

    private int lastIdx = -1;

    private int size = 0;

    private final int value;

    private final int k;

    public Ex2526(int value, int k) {
        this.value = value;
        this.k = k;
        this.lastIdx = k;
    }

    public boolean consec(int num) {
        if (num != value) {
            lastIdx = k;
            size = size == k ? size : size + 1;
            return false;
        } else {
            lastIdx--;
            size = size == k ? size : size + 1;
            if (lastIdx <= 0 && size == k) {
                return true;
            }
            return false;
        }
    }
}
