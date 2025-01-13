package com.mengyu.algs4.exercise.leetcode.array;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex2274 {
    public int maxConsecutive(int bottom, int top, int[] special) {
        Arrays.sort(special);
        int max = 0, offset = 0;
        for (int i = 0; i < special.length; i++) {
            max = Math.max(max, special[i] - bottom - offset);
            bottom = special[i];
            offset = 1;
            if (i == special.length - 1) {
                max = Math.max(max, top - special[i]);
            }
        }
        return max;
    }
}
