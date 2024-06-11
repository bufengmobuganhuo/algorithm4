package com.mengyu.algs4.exercise.leetcode.greedy;

/**
 * @author yu zhang
 */
public class Ex2938 {
    public long minimumSteps(String s) {
        int n = s.length();
        long cnt = 0, ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                cnt++;
            } else {
                ans += cnt;
            }
        }
        return ans;
    }
}
