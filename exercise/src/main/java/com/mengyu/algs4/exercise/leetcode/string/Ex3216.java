package com.mengyu.algs4.exercise.leetcode.string;

/**
 * @author yu zhang
 */
public class Ex3216 {
    public String getSmallestString(String s) {
        StringBuilder sb = new StringBuilder(s);
        int n = s.length();
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) > s.charAt(i + 1) && (s.charAt(i) - '0' + s.charAt(i + 1) - '0') % 2 == 0) {
                char tmp = s.charAt(i);
                sb.setCharAt(i, sb.charAt(i + 1));
                sb.setCharAt(i + 1, tmp);
                return sb.toString();
            }
        }
        return s;
    }
}
