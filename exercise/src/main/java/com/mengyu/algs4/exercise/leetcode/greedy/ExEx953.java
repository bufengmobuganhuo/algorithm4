package com.mengyu.algs4.exercise.leetcode.greedy;

/**
 * @author yu zhang
 */
public class ExEx953 {
    public long numberOfWeeks(int[] milestones) {
        long longest = 0, rest = 0;
        for (int milestone : milestones) {
            longest = Math.max(longest, milestone);
            rest += milestone;
        }
        rest -= longest;
        if (longest <= rest + 1) {
            return longest + rest + 1;
        } else {
            return 2 * rest + 1;
        }
    }
}
