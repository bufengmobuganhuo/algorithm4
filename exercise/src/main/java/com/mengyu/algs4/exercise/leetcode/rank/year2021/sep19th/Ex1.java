package com.mengyu.algs4.exercise.leetcode.rank.year2021.sep19th;

/**
 * @author yuzhang
 * @date 2021/12/19 10:30 上午
 * TODO
 */
public class Ex1 {
    public String firstPalindrome(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        String res = "";
        for (String word : words) {
            if (word.equals(new StringBuilder(word).reverse().toString())) {
                res = word;
                break;
            }
        }
        return res;
    }
}
