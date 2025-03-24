package com.mengyu.algs4.exercise.leetcode.string;

/**
 * @author yu zhang
 */
public class Ex14 {
    public String longestCommonPrefix(String[] strs) {
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            String str = strs[i];
            for (int j = 0; j < prefix.length(); j++) {
                if (j >= str.length()) {
                    prefix = str;
                    break;
                }
                if (prefix.charAt(j) == str.charAt(j)) {
                    continue;
                }
                prefix = prefix.substring(0, j);
                break;
            }
        }
        return prefix;
    }
}
