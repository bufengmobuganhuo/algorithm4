package com.mengyu.algs4.exercise.leetcode.count;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex1497 {
    public boolean canArrange(int[] arr, int k) {
        Map<Integer, Integer> mod = new HashMap<Integer, Integer>();
        for (int num : arr) {
            mod.put((num % k + k) % k, mod.getOrDefault((num % k + k) % k, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : mod.entrySet()) {
            int t = entry.getKey(), occ = entry.getValue();
            if (t > 0 && mod.getOrDefault(k - t, 0) != occ) {
                return false;
            }
        }
        return mod.getOrDefault(0, 0) % 2 == 0;
    }
}
