package com.mengyu.algs4.exercise.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex1726 {
    public int tupleSameProduct(int[] nums) {
        Map<Long, Integer> cntMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                long multi = (long) nums[i] * nums[j];
                cntMap.put(multi, cntMap.getOrDefault(multi, 0) + 1);
            }
        }
        int ans = 0;
        for (Map.Entry<Long, Integer> entry : cntMap.entrySet()){
            if (entry.getValue() >= 2) {
                ans += entry.getValue() * 2 * (entry.getValue() - 1) * 2;
            }
        }
        return ans;
    }
}
