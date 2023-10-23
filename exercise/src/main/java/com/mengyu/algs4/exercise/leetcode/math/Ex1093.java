package com.mengyu.algs4.exercise.leetcode.math;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex1093 {
    public double[] sampleStats(int[] count) {
        long total = Arrays.stream(count).sum();
        int minimum = Integer.MAX_VALUE, maximum = Integer.MIN_VALUE;
        int mode = 0, maxFreq = 0;
        long left = (total + 1) / 2;
        long right = (total + 2) / 2;
        double median = 0.0;
        int cnt = 0;
        long sum = 0;
        for (int i = 0; i < count.length; i++) {
            sum += (long) count[i] * i;
            if (count[i] > 0 && minimum > i) {
                minimum = i;
            }
            if (count[i] > 0 && maximum < i) {
                maximum = i;
            }
            if (maxFreq < count[i]) {
                maxFreq = count[i];
                mode = i;
            }
            if (cnt < right && cnt + count[i] >= right) {
                median += i;
            }
            if (cnt < left && cnt + count[i] >= left) {
                median += i;
            }
            cnt += count[i];
        }
        double mean = (double) sum / total;
        median /= 2;
        return new double[]{minimum, maximum, mean, median, mode};
    }
}
