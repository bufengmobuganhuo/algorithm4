package com.mengyu.algs4.exercise.leetcode.string;

/**
 * @author yu zhang
 */
public class Ex5_1 {

    public String longestPalindrome2(String s) {
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1= expandFromCenter(s, i, i);
            int len2 = expandFromCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
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

    public String longestPalindrome1(String s) {
        StringBuilder t = new StringBuilder("$#");
        for (char chr : s.toCharArray()) {
            t.append(chr);
            t.append("#");
        }
        t.append("@");
        int[] p = new int[t.length()];
        int mx = 0, id = 0;
        int resCenter = 0, resLen = 0;
        for (int i = 1; i < t.length() - 1; i++) {
            p[i] = mx > i ? p[2*id - i] : 1;
            while (i - p[i] > 0 && i + p[i] < t.length() - 1 && t.charAt(i + p[i]) == t.charAt(i - p[i])) {
                p[i]++;
            }
            if (p[i] + i > mx) {
                mx = p[i];
                id = i;
            }
            if (p[i] > resLen) {
                resLen = p[i];
                resCenter = i;
            }
        }
        return s.substring((resCenter - resLen) / 2, (resCenter - resLen) / 2 + resLen - 1);
    }
}
