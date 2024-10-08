package com.mengyu.algs4.exercise.leetcode.prefix_sum;

/**
 * @author yu zhang
 */
public class Ex2207 {

    public static void main(String[] args) {
        System.out.println(new Ex2207().maximumSubsequenceCount("iekbksdsmuuzwxbpmcngsfkjvpzuknqguzvzik", "mp"));
    }

    public long maximumSubsequenceCount(String text, String pattern) {
        long res = 0;
        int cnt1 = 0, cnt2 = 0;
        for (char chr : text.toCharArray()) {
            if (chr == pattern.charAt(1)) {
                res += cnt1;
                cnt2++;
            }
            if (chr == pattern.charAt(0)) {
                cnt1++;
            }
        }
        return res + Math.max(cnt1, cnt2);
    }
}
