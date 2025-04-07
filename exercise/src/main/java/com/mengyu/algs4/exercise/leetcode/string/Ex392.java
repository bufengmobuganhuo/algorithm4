package com.mengyu.algs4.exercise.leetcode.string;

/**
 * @author yu zhang
 */
public class Ex392 {
    public boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;
        for (; i < s.length() && j < t.length();) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                j++;
            }
        }
        return i == s.length();
    }
}
