package com.mengyu.algs4.exercise.leetcode.array;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex1672 {
    public int maximumWealth(int[][] accounts) {
        int max = Integer.MIN_VALUE;
        for (int[] account : accounts) {
            int sum = Arrays.stream(account).sum();
            max = Math.max(max, sum);
        }
        return max;
    }
}
