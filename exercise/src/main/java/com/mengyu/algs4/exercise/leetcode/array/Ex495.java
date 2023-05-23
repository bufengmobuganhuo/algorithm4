package com.mengyu.algs4.exercise.leetcode.array;

/**
 * @author yuzhang
 * @date 2021/11/28 9:47 上午
 * TODO
 */
public class Ex495 {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int res = 0;
        int pre = timeSeries[0];
        for (int i = 1; i < timeSeries.length; i++) {
            res += Math.min(timeSeries[i], pre + duration) - pre;
            pre = timeSeries[i];
        }
        return res + duration;
    }
}
