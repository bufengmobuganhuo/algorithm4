package com.mengyu.algs4.exercise.leetcode.rank.year2022.september11;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yuzhang
 * @date 2022/9/11 10:34
 * TODO
 */
public class Ex2 {
    public int partitionString(String s) {
        Set<Character> set = new HashSet<>();
        int rightPtr = 0;
        int count = 0;
        while (rightPtr < s.length()) {
            if (set.contains(s.charAt(rightPtr))) {
                count++;
                set.clear();
            }
            set.add(s.charAt(rightPtr));
            rightPtr++;
        }
        count++;
        return count;
    }
}
