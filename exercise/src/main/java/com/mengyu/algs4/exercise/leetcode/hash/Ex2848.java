package com.mengyu.algs4.exercise.leetcode.hash;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex2848 {
    public int numberOfPoints(List<List<Integer>> nums) {
        Set<Integer> set = new HashSet<>();
        for (List<Integer> list : nums) {
            for (int i = list.get(0); i < list.get(1) + 1; i++) {
                set.add(i);
            }
        }
        return set.size();
    }
}
