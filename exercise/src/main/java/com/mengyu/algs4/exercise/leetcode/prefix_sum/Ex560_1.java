package com.mengyu.algs4.exercise.leetcode.prefix_sum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex560_1 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1};
        System.out.println(new Ex560_1().subarraySum(nums, 2));
    }
    public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        int[] prefixSum = new int[len + 1];
        for (int i = 0; i < len; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < len + 1; i++) {
            int sum = prefixSum[i];
            int cnt = map.getOrDefault(sum - k, 0);
            count += cnt;
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
