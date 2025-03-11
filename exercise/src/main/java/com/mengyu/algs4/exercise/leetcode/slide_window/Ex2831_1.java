package com.mengyu.algs4.exercise.leetcode.slide_window;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex2831_1 {

    public static void main(String[] args) {
        System.out.println(new Ex2831_1().longestEqualSubarray(Arrays.asList(1, 2, 1), 0));
    }

    public int longestEqualSubarray(List<Integer> nums, int k) {
        int n = nums.size();
        int l = 0, r = 0;
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        while (r < n) {
            map.put(nums.get(r), map.getOrDefault(nums.get(r), 0) + 1);
            while (r - l + 1 - map.get(nums.get(l)) > k) {
                map.put(nums.get(l), map.get(nums.get(l)) - 1);
                l++;
            }
            ans = Math.max(ans, map.get(nums.get(r)));
            r++;
        }
        return ans;
    }
}
