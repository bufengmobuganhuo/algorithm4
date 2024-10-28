package com.mengyu.algs4.exercise.leetcode.hash;

/**
 * @author yu zhang
 */
public class Ex3185 {
    public long countCompleteDayPairs(int[] hours) {
        int[] mods = new int[24];
        long cnt = 0;
        for (int hour : hours) {
            int mod = hour % 24;
            if (mod == 0) {
                cnt += mods[0];
            } else {
                cnt += mods[24 - mod];
            }
            mods[mod]++;
        }
        return cnt;
    }
}
