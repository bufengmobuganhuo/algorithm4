package com.mengyu.algs4.exercise.leetcode.array;

/**
 * @author yu zhang
 */
public class Ex944 {
    public int minDeletionSize(String[] strs) {
        if (strs == null || strs.length == 0){
            return 0;
        }
        int count = 0;
        int m = strs.length, n = strs[0].length();

        for (int i = 0; i < n; i++) {
            char previous = 'a';
            for (String str : strs) {
                if (str.charAt(i) < previous) {
                    count++;
                    break;
                }
                previous = str.charAt(i);
            }
        }
        return count;
    }
}
