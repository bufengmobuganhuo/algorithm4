package com.mengyu.algs4.exercise.leetcode.string;

import java.util.List;

/**
 * @author yu zhang
 */
public class Ex2828 {
    public boolean isAcronym(List<String> words, String s) {
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(word.charAt(0));
        }
        return sb.toString().equals(s);
    }
}
