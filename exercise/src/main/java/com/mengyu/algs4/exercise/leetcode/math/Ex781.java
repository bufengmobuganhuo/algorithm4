package com.mengyu.algs4.exercise.leetcode.math;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex781 {
    /**
     * 1. 假设有x只兔子的回答都是y，那么至少有y+1只兔子颜色一样，那么有两种情况：
     * (1)x <= y + 1：则有一种颜色，并且有y+1只兔子，并且是满足条件的
     * (2)x > y + 1：则兔子的颜色不止一种：
     * x - z > 0 && z <= (y + 1)，则说明至少有2种颜色
     * x - z > 0 && (y + 1) < z <= 2(y + 1)，则说明至少有3种颜色
     *
     * 也就是说至少有 x/(y+1) 种颜色（向上取整）,每种颜色有y+1只兔子
     */
    public int numRabbits(int[] answers) {
        // 统计相同回答的个数
        Map<Integer,Integer> cntMap = new HashMap<>();
        for (int answer : answers) {
            cntMap.put(answer, cntMap.getOrDefault(answer, 0) + 1);
        }
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : cntMap.entrySet()) {
            int x = entry.getValue(), y = entry.getKey();
            count += (x + (y + 1) - 1) / (y + 1) * (y+1);
        }
        return count;
    }
}
