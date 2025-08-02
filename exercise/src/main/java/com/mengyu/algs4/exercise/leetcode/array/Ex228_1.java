package com.mengyu.algs4.exercise.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yu zhang
 */
public class Ex228_1 {
    public List<String> summaryRanges(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        List<String> ans = new ArrayList<>();

        int start = nums[0], startIdx = 0;
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num != start + i - startIdx) {
                if (i - startIdx <= 1) {
                    ans.add(String.valueOf(start));
                } else {
                    ans.add(start + "->" + (nums[i - 1]));
                }
                start = num;
                startIdx = i;
            }
        }
        if (nums.length - 1 - startIdx <= 1) {
            ans.add(String.valueOf(start));
        } else {
            ans.add(start + "->" + (nums[nums.length - 1]));
        }
        return ans;
    }
}
