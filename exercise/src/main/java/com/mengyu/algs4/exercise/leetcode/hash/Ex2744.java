package com.mengyu.algs4.exercise.leetcode.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex2744 {

    public static void main(String[] args) {
        System.out.println(new Ex2744().maximumNumberOfStringPairs(new String[]{"cd","ac","dc","ca","zz"}));
    }

    public int maximumNumberOfStringPairs(String[] words) {
        Set<String> set = new HashSet<>();
        int cnt = 0;
        for (String word : words) {
            String reversed = new StringBuilder(word).reverse().toString();
            if (set.contains(reversed)) {
                cnt++;
            }
            set.add(word);
        }
        return cnt;
    }
}
