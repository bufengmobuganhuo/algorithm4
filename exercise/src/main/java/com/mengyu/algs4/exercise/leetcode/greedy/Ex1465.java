package com.mengyu.algs4.exercise.leetcode.greedy;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex1465 {

    public static void main(String[] args) {
        System.out.println(new Ex1465().maxArea(1000000000, 1000000000, new int[]{2}, new int[]{2}));
    }

    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);

        return (int) ((maxCut(horizontalCuts, h) * maxCut(verticalCuts, w)) % 1000000007);
    }

    private long maxCut(int[] arr, int border) {
        int res = 0, pre = 0;
        for (int x : arr) {
            res = Math.max(res, x - pre);
            pre = x;
        }
        return Math.max(res, border - pre);
    }
}
