package com.mengyu.algs4.exercise.leetcode.math;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex3164 {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 4, 12}, nums2 = {2, 4};
        System.out.println(new Ex3164().numberOfPairs(nums1, nums2, 3));
    }

    public long numberOfPairs(int[] nums1, int[] nums2, int k) {
        int max = 0;
        Map<Integer, Integer> cntMap1 = new HashMap<>();
        for (int num : nums1) {
            cntMap1.put(num, cntMap1.getOrDefault(num, 0) + 1);
            max = Math.max(max, num);
        }
        Map<Integer, Integer> cntMap2 = new HashMap<>();
        for (int num : nums2) {
            cntMap2.put(num, cntMap2.getOrDefault(num, 0) + 1);
        }
        long cnt = 0;
        for (int num2 : cntMap2.keySet()) {
            for (int num1 = num2 * k; num1 <= max; num1 += num2 * k) {
                if (cntMap1.containsKey(num1)) {
                    cnt += (long) cntMap1.get(num1) * cntMap2.get(num2);
                }
            }
        }
        return cnt;
    }
}
