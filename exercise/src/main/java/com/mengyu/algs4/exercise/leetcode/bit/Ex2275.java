package com.mengyu.algs4.exercise.leetcode.bit;

/**
 * @author yu zhang
 */
public class Ex2275 {
    public int largestCombination(int[] candidates) {
        int[] bitmap = new int[32];
        for (int candidate : candidates) {
            int idx = 0;
            while (candidate != 0) {
                int bit = candidate & 1;
                candidate >>= 1;
                if (bit == 1) {
                    bitmap[idx]++;
                }
                idx++;
            }
        }
        int ans = 0;
        for (int bitCnt : bitmap) {
            ans = Math.max(ans, bitCnt);
        }
        return ans;
    }
}
