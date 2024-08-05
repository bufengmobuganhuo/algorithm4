package com.mengyu.algs4.exercise.leetcode.greedy;

/**
 * @author yu zhang
 */
public class Ex3106 {

    public static void main(String[] args) {
        System.out.println(new Ex3106().getSmallestString("xaxcd", 4));
    }

    public String getSmallestString(String s, int k) {
        if (k == 0) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            char chr = s.charAt(i);
            if (chr == 'a') {
                continue;
            }
            int distance = Math.min(chr - 'a', 'a' + 26 - chr);
            if (distance <= k) {
                sb.setCharAt(i, 'a');
                k -= distance;
            } else {
                sb.setCharAt(i, (char) (chr - k));
                break;
            }
        }
        return sb.toString();
    }
}
