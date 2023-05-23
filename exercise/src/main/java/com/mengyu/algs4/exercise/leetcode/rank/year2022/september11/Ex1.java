package com.mengyu.algs4.exercise.leetcode.rank.year2022.september11;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2022/9/11 10:24
 * TODO
 */
public class Ex1 {
    public int mostFrequentEven(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (num % 2 == 0) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }
        int ans = -1, ansCount = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (ansCount < entry.getValue()) {
                ans = entry.getKey();
                ansCount = entry.getValue();
            } else if (ansCount == entry.getValue() && ans > entry.getKey()) {
                ans = entry.getKey();
            }
        }
        return ans;
    }
}
