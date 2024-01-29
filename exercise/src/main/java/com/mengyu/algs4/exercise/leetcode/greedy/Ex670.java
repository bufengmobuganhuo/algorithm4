package com.mengyu.algs4.exercise.leetcode.greedy;

/**
 * @author yu zhang
 */
public class Ex670 {
    public int maximumSwap(int num) {
        char[] chrs = String.valueOf(num).toCharArray();
        int n = chrs.length;
        int maxId = n - 1, idx1 = -1, idx2 = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (chrs[i] > chrs[maxId]) {
                maxId = i;
            } else if (chrs[i] < chrs[maxId]) {
                idx1 = i;
                idx2 = maxId;
            }
        }
        if (idx1 != -1) {
            char tmp = chrs[idx1];
            chrs[idx1] = chrs[idx2];
            chrs[idx2] = tmp;
        }
        return Integer.parseInt(new String(chrs));
    }
}
