package com.mengyu.algs4.exercise.leetcode.rank.year2022.july17;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2022/7/17 10:21
 * TODO
 */
public class Ex1 {
    public int[] numberOfPairs(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int answer1 = 0, answer2 = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            answer1 += entry.getValue() / 2;
            answer2 += entry.getValue() % 2;
        }
        return new int[]{answer1, answer2};
    }
}
