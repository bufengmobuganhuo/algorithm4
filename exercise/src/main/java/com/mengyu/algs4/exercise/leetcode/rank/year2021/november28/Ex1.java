package com.mengyu.algs4.exercise.leetcode.rank.year2021.november28;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yuzhang
 * @date 2021/11/28 10:32 上午
 * TODO
 */
public class Ex1 {
    public List<Integer> targetIndices(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                res.add(i);
            }
        }
        return res;
    }
}
