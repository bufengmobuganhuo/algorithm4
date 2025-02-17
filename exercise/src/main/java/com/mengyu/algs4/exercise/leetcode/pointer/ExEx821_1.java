package com.mengyu.algs4.exercise.leetcode.pointer;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class ExEx821_1 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new ExEx821_1().shortestToChar("abaa", 'b')));
    }

    public int[] shortestToChar(String s, char c) {
        int n = s.length();
        int l = 0, r = 0;
        int[] ans = new int[n];
        while (l < n) {
            while (r < n && s.charAt(r) != c) {
                ans[r] = s.charAt(l) == c ? Math.abs(r - l) : Integer.MAX_VALUE;
                r++;
            }
            while (l < r) {
                ans[l] = Math.min(ans[l], r < n ? Math.abs(r - l) : Integer.MAX_VALUE);
                l++;
            }
            r++;
        }
        return ans;
    }
}
