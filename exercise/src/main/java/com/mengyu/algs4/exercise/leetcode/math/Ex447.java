package com.mengyu.algs4.exercise.leetcode.math;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex447 {
    public int numberOfBoomerangs(int[][] points) {
        int ans = 0;
        for (int[] thisPoint : points) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int[] otherPoint : points) {
                int distance = (thisPoint[0] - otherPoint[0]) * (thisPoint[0] - otherPoint[0]) + (thisPoint[1] - otherPoint[1]) * (thisPoint[1] - otherPoint[1]);
                map.put(distance, map.getOrDefault(distance, 0) + 1);
            }

            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                ans += entry.getValue() * (entry.getValue() - 1);
            }
        }
        return ans;
    }
}
