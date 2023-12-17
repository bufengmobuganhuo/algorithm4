package com.mengyu.algs4.exercise.leetcode.string;

/**
 * @author yu zhang
 */
public class Ex2609 {
    public int findTheLongestBalancedSubstring(String s) {
        int res = 0;
        int[] cnt = new int[2];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                cnt[1]++;
                res = Math.max(res, 2 * Math.min(cnt[0], cnt[1]));
            } else if (i == 0 || s.charAt(i - 1) == '1') {
                cnt[0] = 1;
                cnt[1] = 0;
            } else {
                cnt[0]++;
            }
        }
        return res;
    }
}
