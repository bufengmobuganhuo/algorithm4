package com.mengyu.algs4.exercise.leetcode.hash;

/**
 * @author yu zhang
 */
public class Ex282 {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] map = new int[26];
        for (char chr : magazine.toCharArray()) {
            map[chr - 'a']++;
        }
        for (char chr : ransomNote.toCharArray()) {
            int cnt = map[chr - 'a'];
            if (cnt <= 0) {
                return false;
            } else {
                map[chr - 'a'] = cnt - 1;
            }
        }
        return true;
    }
}
