package com.mengyu.algs4.exercise.leetcode.string;

/**
 * @author yu zhang
 */
public class Ex3174 {
    public String clearDigits(String s) {
        StringBuilder sb = new StringBuilder();
        for (char chr : s.toCharArray()) {
            if (Character.isDigit(chr) && sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            } else if (!Character.isDigit(chr)){
                sb.append(chr);
            }
        }
        return sb.toString();
    }
}
