package com.mengyu.algs4.exercise.leetcode.string;

/**
 * @author yu zhang
 */
public class Ex2414 {
    public int longestContinuousSubstring(String s) {
        int cnt = 1, ans = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) + 1 == s.charAt(i)) {
                cnt++;
            } else {
                ans = Math.max(ans, cnt);
                cnt = 1;
            }
        }
        return Math.max(ans, cnt);
    }
}
