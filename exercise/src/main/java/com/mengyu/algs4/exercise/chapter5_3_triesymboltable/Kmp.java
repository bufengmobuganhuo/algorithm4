package com.mengyu.algs4.exercise.chapter5_3_triesymboltable;

/**
 * @author yu zhang
 */
public class Kmp {
    private int[] next;


    private int search(String txt, String pat) {
        next(pat);
        int i = 0, j = 0;
        while (i < txt.length() && j < pat.length()) {
            if (j == -1 || txt.charAt(i) == pat.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (pat.length() == j) {
            return i - j;
        }
        return -1;
    }

    private void next(String patternStr) {
        next = new int[patternStr.length()];
        next[0] = -1;
        int i = 0, j = -1;
        while (i < patternStr.length() - 1) {
            if (j == -1 || patternStr.charAt(i) == patternStr.charAt(j)) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }
    }
}
