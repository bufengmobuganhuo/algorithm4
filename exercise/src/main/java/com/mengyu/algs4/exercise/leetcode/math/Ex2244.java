package com.mengyu.algs4.exercise.leetcode.math;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex2244 {
    public int minimumRounds(int[] tasks) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int task : tasks) {
            map.compute(task, (key, val) -> val == null ? 1 : val+1);
        }
        int cnt = 0;
        for (int key : map.keySet()) {
            int d = map.get(key);
            if (d == 1) {
                return -1;
            } else if (d % 3 == 0) {
                cnt += d / 3;
            } else if (d % 3 == 2) {
                cnt += d / 3 + 1;
            } else {
                cnt += (d - 3) / 3 + 2;
            }
        }
        return cnt;
    }
}
