package com.mengyu.algs4.exercise.leetcode.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex575 {
    public int distributeCandies(int[] candyType) {
        int n = candyType.length;
        Set<Integer> set = new HashSet<>();
        for (int candy : candyType) {
            set.add(candy);
        }
        return Math.min(n / 2, set.size());
    }
}
