package com.mengyu.algs4.exercise.leetcode.hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex954 {
    public static void main(String[] args) {
        int[] arr = {4, -2, 2, -4, 8, 4, 1, 8};
        System.out.println(new Ex954().canReorderDoubled(arr));
    }
    public boolean canReorderDoubled(int[] arr) {
        Arrays.sort(arr);
        Map<Double, Integer> map = new HashMap<>();
        for (double num : arr) {
            double divided = map.getOrDefault(num / 2, 0);
            if (divided == 1) {
                map.remove(num / 2);
                continue;
            } else if (divided > 1) {
                map.put(num / 2, (int) (divided - 1));
                continue;
            }
            double multi = map.getOrDefault(num * 2, 0);
            if (multi == 1) {
                map.remove(num * 2);
                continue;
            } else if (multi > 1) {
                map.put(num * 2, (int) (multi - 1));
                continue;
            }
            map.put(num , map.getOrDefault(num, 0) + 1);
        }
        return map.isEmpty();
    }
}
