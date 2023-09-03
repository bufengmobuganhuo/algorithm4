package com.mengyu.algs4.exercise.leetcode.rank.year2023.august20;

import java.util.List;

/**
 * @author yuzhang
 * @date 2023/5/28 10:29
 * TODO
 */
public class Ex1 {
    public boolean isAcronym(List<String> words, String s) {
        if (s.length() != words.size()) {
            return false;
        }
        int idx = 0;
        for (String word : words) {
            if (s.charAt(idx) != word.charAt(0)) {
                return false;
            }
            idx++;
        }
        return true;
    }
}
