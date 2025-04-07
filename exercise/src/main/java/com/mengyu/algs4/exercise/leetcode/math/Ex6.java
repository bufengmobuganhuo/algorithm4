package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yu zhang
 */
public class Ex6 {

    public static void main(String[] args) {
        System.out.println(new Ex6().convert("A", 1));
    }

    public String convert(String s, int numRows) {
        if (s.length() == 1 || numRows >= s.length()) {
            return s;
        }
        int period = 2 * numRows - 2;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            int idx = 0;
            while (idx + i < s.length()) {
                res.append(s.charAt(idx + i));
                idx = idx + period;
                if (i > 0 && idx - i < s.length()) {
                    res.append(s.charAt(idx - i));
                }
            }
        }
        return res.toString();
    }
}
