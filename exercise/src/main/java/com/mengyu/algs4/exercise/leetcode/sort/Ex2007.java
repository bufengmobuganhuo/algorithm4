package com.mengyu.algs4.exercise.leetcode.sort;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * @author yu zhang
 */
public class Ex2007 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Ex2007().findOriginalArray(new int[]{1, 3, 4, 2, 6, 8})));
    }

    public int[] findOriginalArray(int[] changed) {
        int n = changed.length;
        if (n % 2 != 0) {
            return new int[]{};
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int change : changed) {
            map.put(change, map.getOrDefault(change, 0) + 1);
        }
        int[] ans = new int[n / 2];
        for (int i = 0; i < n / 2; i++) {
            int min = map.firstKey();
            if ((min == min * 2 && map.getOrDefault(min, 0) >= 2) || (min != min * 2 && map.containsKey(min * 2))) {
                ans[i] = min;
                map.computeIfPresent(min, (key, val) -> {
                    if (val - 1 <= 0) {
                        return null;
                    } else {
                        return val - 1;
                    }
                });
                map.computeIfPresent(min * 2, (key, val) -> {
                    if (val - 1 <= 0) {
                        return null;
                    } else {
                        return val - 1;
                    }
                });
            } else {
                return new int[]{};
            }
        }
        return ans;
    }
}
