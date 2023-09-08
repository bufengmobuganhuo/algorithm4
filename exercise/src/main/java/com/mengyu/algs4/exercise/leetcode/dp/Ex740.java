package com.mengyu.algs4.exercise.leetcode.dp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yu zhang
 */
public class Ex740 {

    public static void main(String[] args) {
        int[] nums = {8, 10, 4, 9, 1, 3, 5, 9, 4, 10};
        System.out.println(new Ex740().deleteAndEarn(nums));
    }

    public int deleteAndEarn(int[] nums) {
        Map<Integer, Integer> cntMap = new HashMap<>();
        for (int num : nums) {
            cntMap.put(num, cntMap.getOrDefault(num, 0) + 1);
        }
        List<Map.Entry<Integer, Integer>> entries =
                cntMap.entrySet().stream().sorted(Comparator.comparingInt(Map.Entry::getKey)).collect(Collectors.toList());
        List<Integer> dp = new ArrayList<>();
        dp.add(entries.get(0).getKey() * entries.get(0).getValue());
        int max = dp.get(0);
        for (int i = 1; i < entries.size(); i++) {
            int num = entries.get(i).getKey();
            int lastMax = 0;
            for (int j = i - 1; j >= 0; j--) {
                int key = entries.get(j).getKey();
                if (key == num - 1 || key == num + 1) {
                    continue;
                }
                int val = dp.get(j);
                lastMax = Math.max(val, lastMax);
            }
            int got = num * entries.get(i).getValue() + lastMax;
            dp.add(got);
            max = Math.max(max, got);
        }
        return max;
    }
}
