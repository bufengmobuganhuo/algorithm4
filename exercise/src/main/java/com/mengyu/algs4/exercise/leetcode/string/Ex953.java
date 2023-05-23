package com.mengyu.algs4.exercise.leetcode.string;

/**
 * @author yu zhang
 */
public class Ex953 {
    private int[] map;
    public boolean isAlienSorted(String[] words, String order) {
        map = new int[26];
        for (int i = 0; i < order.length(); i++) {
            map[order.charAt(i) - 'a'] = i;
        }
        for (int i = 1; i < words.length; i++) {
            String prev = words[i-1];
            String cur = words[i];
            if (compareTo(prev, cur) > 0) {
                return false;
            }
        }
        return true;
    }

    private int compareTo(String str1, String str2) {
        for (int i = 0; i < Math.min(str1.length(), str2.length()); i++) {
            int idx1 = map[str1.charAt(i)-'a'];
            int idx2 = map[str2.charAt(i)-'a'];
            if (idx1 != idx2) {
                return idx1 - idx2;
            }
        }
        if (str1.length() > str2.length()) {
            return 1;
        }
        return 0;
    }
}
