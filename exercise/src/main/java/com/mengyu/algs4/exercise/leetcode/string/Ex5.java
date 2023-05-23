package com.mengyu.algs4.exercise.leetcode.string;

/**
 * @author yuzhang
 * @date 2021/3/10 上午10:30
 * TODO
 */
public class Ex5 {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            // 一种是奇数的情况： 1112111
            int len1 = expandFromCenter(s, i, i);
            // 一种是偶数的情况：1111 1111
            int len2 = expandFromCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + (len) / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandFromCenter(String s, int leftPtr, int rightPtr) {
        while (leftPtr >= 0 && rightPtr < s.length() && s.charAt(leftPtr) == s.charAt(rightPtr)) {
            leftPtr--;
            rightPtr++;
        }
        return rightPtr - leftPtr - 1;
    }
}
