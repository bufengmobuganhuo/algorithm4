package com.mengyu.algs4.exercise.leetcode.string;

/**
 * @author yu zhang
 */
public class Ex944 {
    public int minDeletionSize(String[] strs) {
        int count = 0;
        for (int i = 0; i < strs[0].length(); i++) {
            char chr = 'a' - 1;
            for (int j = 0; j < strs.length; j++) {
                if (strs[j].charAt(i) < chr) {
                    count++;
                    break;
                }
                chr = strs[j].charAt(i);
            }
        }
        return count;
    }
}
