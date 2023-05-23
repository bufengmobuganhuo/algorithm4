package com.mengyu.algs4.exercise.leetcode.slide_window;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex1695 {
    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 2, 4, 5, 6};
        System.out.println(new Ex1695().maximumUniqueSubarray(nums));
    }

    public int maximumUniqueSubarray(int[] nums) {
        int ans = Integer.MIN_VALUE, sum = 0;
        Set<Integer> visited = new HashSet<>();
        int startIdx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!visited.contains(nums[i])) {
                sum += nums[i];
                ans = Math.max(ans, sum);
                visited.add(nums[i]);
            } else {
                while (nums[startIdx] != nums[i]) {
                    visited.remove(nums[startIdx]);
                    sum -= nums[startIdx];
                    startIdx++;
                }
                startIdx++;
            }
        }
        return ans;
    }
}
