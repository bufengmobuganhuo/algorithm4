package com.mengyu.algs4.exercise.leetcode.string;

/**
 * @author yu zhang
 */
public class Ex520 {
    public boolean detectCapitalUse(String word) {
        int upperCnt = 0;
        for (char chr : word.toCharArray()) {
            if (Character.isUpperCase(chr)) {
                upperCnt++;
            }
        }
        return upperCnt == word.length() || (Character.isUpperCase(word.charAt(0)) && upperCnt == 1) || upperCnt == 0;
    }
}
