package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yu zhang
 */
public class Ex3200 {
    public int maxHeightOfTriangle(int red, int blue) {
        return Math.max(find(red, blue), find(blue, red));
    }

    private int find(int a, int b) {
        int odd = 2 * (int)(Math.sqrt(a)) - 1;
        int even = 2 * (int)((-1 + Math.sqrt(1 + 4 * b)) / 2);
        return Math.min(odd, even) + 1;
    }
}