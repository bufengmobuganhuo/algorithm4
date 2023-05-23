package com.mengyu.algs4.exercise.lcp.binary_search;

/**
 * @author yu zhang
 */
public class Ex12 {
    public static void main(String[] args) {
        int[] time = {1, 2, 3, 3, 3};
        System.out.println(new Ex12().minTime(time, 2));
    }
    /**
     * 1. 给定一个时间和total，判断数组是否可以被分成m份，如果可以，则减小total，直到找到最小值
     */
    public int minTime(int[] time, int m) {
        int left = 0, right = Integer.MAX_VALUE;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 可以分成m块，减小total
            if (check(time, mid, m)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int[] time, int total, int m) {
        int daysCount = 0;
        // 每块的最大时间
        int maxTime = 0;
        // 是否找过场外援助
        boolean helped = false;
        // 每块的时间
        int timeCount = 0;
        for (int i = 0; i < time.length; i++) {
            // 维护每一块的最大时间
            maxTime = Math.max(maxTime, time[i]);
            // 如果当前块耗时超过total
            timeCount += time[i];
            if (timeCount > total) {
                // 如果没有找过场外援助
                if (!helped) {
                    // 去掉当前块的最大耗时
                    timeCount -= maxTime;
                    helped = true;
                    // 如果找过，则是重新开始一个新的块
                } else {
                    timeCount = 0;
                    daysCount++;
                    helped = false;
                    maxTime = 0;
                    // 当前题目没法处理，-1个
                    i--;
                }
            }
        }
        // 是否能分成m块
        return daysCount < m;
    }
}
