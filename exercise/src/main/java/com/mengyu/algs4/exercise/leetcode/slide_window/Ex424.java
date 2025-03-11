package com.mengyu.algs4.exercise.leetcode.slide_window;

/**
 * @author yu zhang
 */
public class Ex424 {
    public int characterReplacement(String s, int k) {
        if (s.length() < 2) {
            return s.length();
        }
        int[] freq = new int[26];
        int maxCnt = 0, l = 0, r = 0;
        int len = s.length(), ans = 0;
        while (r < len) {
            int idx = s.charAt(r) - 'A';
            freq[idx]++;
            maxCnt = Math.max(maxCnt, freq[idx]);
            r++;
            if (r - l > maxCnt + k) {
                freq[s.charAt(l) - 'A']--;
                l++;
            }
            ans = Math.max(ans, r - l);
        }
        return ans;
    }
}
