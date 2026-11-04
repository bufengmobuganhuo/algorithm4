package com.mengyu.algs4.exercise.leetcode.sort;

import java.util.List;
import java.util.TreeSet;
import java.util.logging.Handler;

/**
 *
 * @author yuzhang
 * @date 2026/11/4 22:46
 */
public class Ex2817 {
    public int minAbsoluteDifference(List<Integer> nums, int x) {
        TreeSet<Integer> set = new TreeSet<>();
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < nums.size(); j++) {
            if (j >= x) {
                set.add(nums.get(j - x));
            }
            // 因为是绝对值，所以先找 >=nums[j]的最小值
            Integer ceiling = set.ceiling(nums.get(j));
            if (ceiling != null) {
                min = Math.min(min, ceiling - nums.get(j));
            }
            // 再找 <= nums[j]的最大值
            Integer floor = set.floor(nums.get(j));
            if (floor != null) {
                min = Math.min(min, nums.get(j) - floor);
            }
        }
        return min;
    }
}
