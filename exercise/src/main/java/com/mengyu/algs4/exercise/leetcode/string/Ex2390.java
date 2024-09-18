package com.mengyu.algs4.exercise.leetcode.string;

/**
 * @author yu zhang
 */
public class Ex2390 {
    public String removeStars(String s) {
        StringBuilder sb = new StringBuilder();
        for (char chr : s.toCharArray()) {
            if (chr == '*') {
                sb.deleteCharAt(sb.length() - 1);
            } else {
                sb.append(chr);
            }
        }
        return sb.toString();
    }
}
