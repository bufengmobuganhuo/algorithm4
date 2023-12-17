package com.mengyu.algs4.exercise.leetcode.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex1657 {
    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        Set<Character> sets = new HashSet<>();
        for (char chr : word1.toCharArray()) {
            sets.add(chr);
        }
        for (char chr : word2.toCharArray()) {
            if (!sets.contains(chr)) {
                return false;
            }
        }
        return true;
    }
}
