package com.mengyu.algs4.exercise.leetcode.pointer;

/**
 * @author yu zhang
 */
public class Ex942 {
    public int[] diStringMatch(String s) {
        int minPtr = 0, maxPtr = s.length();
        int[] ans = new int[s.length() + 1];
        for (int i = 0; i < s.length(); i++) {
            char chr = s.charAt(i);
            if (chr == 'I') {
                ans[i] = minPtr;
                minPtr++;
            } else {
                ans[i] = maxPtr;
                maxPtr--;
            }
        }
        ans[s.length()] = minPtr;
        return ans;
    }
}
