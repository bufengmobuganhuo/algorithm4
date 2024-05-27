package com.mengyu.algs4.exercise.leetcode.slide_window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex2831 {

    public static void main(String[] args) {
        System.out.println(new Ex2831().longestEqualSubarray(Arrays.asList(1, 3, 2, 3, 1, 3), 3));
    }

    public int longestEqualSubarray(List<Integer> nums, int k) {
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        for (int i = 0; i < nums.size(); i++) {
            int num = nums.get(i);
            indexMap.computeIfAbsent(num, key -> new ArrayList<>()).add(i);
        }
        int max = 0;
        for (List<Integer> indexes : indexMap.values()) {
            int n = indexes.size();
            int leftPtr = 0, rightPtr = 0;
            while (rightPtr < n) {
                int left = indexes.get(leftPtr), right = indexes.get(rightPtr);
                if ((right - left) - (rightPtr - leftPtr) <= k) {
                    max = Math.max(max, rightPtr - leftPtr + 1);
                } else {
                    while ((indexes.get(rightPtr) - indexes.get(leftPtr)) - (rightPtr - leftPtr) > k) {
                        leftPtr++;
                    }
                }
                rightPtr++;
            }
        }
        return max;
    }
}
