package com.mengyu.algs4.exercise.leetcode.dp;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex467_1 {
    public int findSubstringInWraproundString(String p) {
        int[] dp = new int[26];
        int count = 1;
        char[] chrs = (" " + p).toCharArray();
        for (int i = 1; i < chrs.length; i++) {
            int idx = chrs[i] - 'a';
            if (isSuccessive(chrs[i - 1], chrs[i])) {
                count++;
            }else {
                count = 1;
            }
            dp[idx] = count;
        }
        return Arrays.stream(dp).sum();
    }

    private boolean isSuccessive(char chr1, char chr2) {
        if (chr1 == 'z' && chr2 == 'a') {
            return true;
        }
        return chr2 - chr1 == 1;
    }
}
