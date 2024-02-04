package com.mengyu.algs4.exercise.leetcode.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex2670 {
    public int[] distinctDifferenceArray(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        Set<Integer> prefixSet = new HashSet<>();
        Set<Integer> suffixSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            prefixSet.add(nums[i]);
            prefix[i] = prefixSet.size();
            suffix[n - i - 1] = suffixSet.size();
            suffixSet.add(nums[n - i - 1]);
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = suffix[i] - prefix[i];
        }
        return ans;
    }
}
