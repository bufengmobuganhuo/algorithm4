package com.mengyu.algs4.exercise.leetcode.prefix_sum;

/**
 * @author yu zhang
 */
public class Ex1109_1 {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        // 差分数组
        int[] diff = new int[n];
        for (int[] booking : bookings) {
            // 更新差分数组
            int start = booking[0], end = booking[1], seats = booking[2];
            diff[start - 1] += seats;
            if (end < n) {
                diff[end] -= seats;
            }
        }
        // 差分数组的前缀和就是结果
        for (int i = 1; i < n; i++) {
            diff[i] = diff[i - 1] + diff[i];
        }
        return diff;
    }
}
