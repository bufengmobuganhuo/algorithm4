package com.mengyu.algs4.exercise.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2020/12/3 上午9:24
 * TODO
 */
public class Ex454 {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> abCount = new HashMap<>();
        for (int value : A) {
            int sum = value;
            for (int i : B) {
                sum += i;
                abCount.put(sum, abCount.getOrDefault(sum, 0) + 1);
                sum -= i;
            }
        }
        int count=0;
        for (int c : C) {
            for (int d : D) {
                if (abCount.containsKey(-c-d)){
                    count+=abCount.get(-c-d);
                }
            }
        }
        return count;
    }
}
