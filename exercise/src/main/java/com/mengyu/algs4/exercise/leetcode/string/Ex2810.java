package com.mengyu.algs4.exercise.leetcode.string;

/**
 * @author yu zhang
 */
public class Ex2810 {
    public String finalString(String s) {
        StringBuilder sb = new StringBuilder();
        for (char chr : s.toCharArray()) {
            if (chr == 'i') {
                sb.reverse();
            } else {
                sb.append(chr);
            }
        }
        return sb.toString();
    }
}
