package com.mengyu.algs4.exercise.leetcode.hash;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yu zhang
 */
public class Ex2766 {
    public List<Integer> relocateMarbles(int[] nums, int[] moveFrom, int[] moveTo) {
        Set<Integer> rocks = new HashSet<>();
        for (int num : nums) {
            rocks.add(num);
        }
        int n = moveFrom.length;
        for (int i = 0; i < n; i++) {
            if (rocks.remove(moveFrom[i])) {
                rocks.add(moveTo[i]);
            }
        }
        return rocks.stream().sorted().collect(Collectors.toList());
    }
}
