package com.mengyu.algs4.exercise.leetcode.string;

/**
 * @author yu zhang
 */
public class Ex5_2 {
    public String longestPalindrome(String s) {
        int startPtr = 0, endPtr = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandFromCenter(s, i, i);
            int len2 = expandFromCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (endPtr - startPtr + 1 < len) {
                startPtr = i - (len - 1) / 2;
                endPtr = i + len / 2;
            }
        }
        return s.substring(startPtr, endPtr + 1);
    }

    private int expandFromCenter(String s, int leftPtr, int rightPtr) {
        while (leftPtr >= 0 && rightPtr < s.length() && s.charAt(leftPtr) == s.charAt(rightPtr)) {
            leftPtr--;
            rightPtr++;
        }
        return rightPtr - leftPtr - 1;
    }
}
