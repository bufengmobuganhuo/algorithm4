package com.mengyu.algs4.exercise.leetcode.rank.year2022.december04;

/**
 * @author yuzhang
 * @date 2022/12/4 10:03
 * TODO
 */
public class Ex1 {
    public boolean isCircularSentence(String sentence) {
        if (sentence.charAt(0) != sentence.charAt(sentence.length() - 1)) {
            return false;
        }
        char curChr = ' ';
        for (int i = 0; i < sentence.length(); i++) {
            char chr = sentence.charAt(i);
            if (chr == ' ' && curChr != sentence.charAt(i + 1)) {
                return false;
            }
            curChr = sentence.charAt(i);
        }
        return true;
    }
}
