package com.mengyu.algs4.exercise.leetcode.slide_window;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author yu zhang
 */
public class Ex219 {

    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        Map<Integer, Integer> cntMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (cntMap.containsKey(nums[i]) &&  i - cntMap.get(nums[i]) <= k) {
                return true;
            }
            cntMap.put(nums[i], i);
        }
        return false;
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> visited = new HashSet<>();
        int l = 0, r = 0;
        while (r < nums.length) {
            int num = nums[r];
            if (visited.contains(num)) {
                return true;
            }
            visited.add(num);
            if (r - l == k) {
                visited.remove(nums[l]);
                l++;
            }
            r++;
        }
        return false;
    }
}
