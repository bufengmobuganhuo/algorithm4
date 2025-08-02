package com.mengyu.algs4.exercise.leetcode.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex128 {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int longest = 1;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int len = 1;
                while (set.contains(num + 1)) {
                    len++;
                    longest = Math.max(longest, len);
                    num++;
                }
            }
        }
        return longest;
    }
}
