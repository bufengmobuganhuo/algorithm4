package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @date 2023/10/4 16:42
 * TODO
 */
public class Ex91_1 {

    public static void main(String[] args) {
        System.out.println(new Ex91_1().numDecodings("2101"));
    }

    public int numDecodings(String s) {
        if (s.charAt(0) == '0') {
            return 0;
        }
        int n = s.length();
        int a = 0, b = 1, c = 0;
        for (int i = 1; i < n + 1; i++) {
            char chr = s.charAt(i - 1);
            c = 0;
            if (chr != '0') {
                if (s.charAt(i - 1) != 0) {
                    c += b;
                }
            }
            if (i - 2 >= 0 && s.charAt(i - 2) != '0') {
                int num = (s.charAt(i - 2) - '0') * 10 + chr - '0';
                if (num >= 1 && num <= 26) {
                    c += a;
                }
            }
            a = b;
            b = c;
        }
        return c;
    }
}
