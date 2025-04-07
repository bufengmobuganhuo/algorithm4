package com.mengyu.algs4.exercise.leetcode.string;

/**
 * @author yu zhang
 */
public class Ex28 {
    public int strStr(String haystack, String needle) {
        int[] next = next(needle);
        int i = 0, j = 0;
        while (i < haystack.length() && j < needle.length()) {
            if (j == -1 || haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == needle.length()) {
            return i - j;
        }
        return -1;
    }

    private int[] next(String txt) {
        int[] next = new int[txt.length()];
        next[0] = -1;
        int i = 0, j = -1;
        while (i < txt.length() - 1) {
            if (j == -1 || txt.charAt(i) == txt.charAt(j)) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }
        return next;
    }
}
