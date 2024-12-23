package com.mengyu.algs4.exercise.leetcode.hash;

import java.util.Arrays;

/**
 * @author yu zhang
 */
public class Ex3138 {

    public static void main(String[] args) {
        System.out.println(new Ex3138().minAnagramLength("jjj"));
    }

    public int minAnagramLength(String s) {
        int[] cnts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int len = i + 1;
            cnts[s.charAt(i) - 'a']++;
            if (s.length() % len != 0) {
                continue;
            }
            if (check(s, cnts, len)) {
                return len;
            }
        }
        return s.length();
    }

    private boolean check(String s, int[] cnts, int len) {
        int[] cnts2 = new int[26];
        for (int i = len; i + len <= s.length(); i += len) {
            for (int j = i; j < i + len; j++) {
                cnts2[s.charAt(j) - 'a']++;
            }
            if (!Arrays.equals(cnts, cnts2)) {
                return false;
            }
            cnts2 = new int[26];
        }
        return true;
    }
}
