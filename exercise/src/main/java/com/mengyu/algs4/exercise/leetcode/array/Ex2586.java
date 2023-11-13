package com.mengyu.algs4.exercise.leetcode.array;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex2586 {

    private Set<Character> set = new HashSet<>();

    public Ex2586() {
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
    }

    public int vowelStrings(String[] words, int left, int right) {
        int cnt = 0;
        for (int i = left; i < right + 1; i++) {
            if (set.contains(words[i].charAt(0)) && set.contains(words[i].charAt(words[i].length() - 1))) {
                cnt++;
            }
        }
        return cnt;
    }
}
