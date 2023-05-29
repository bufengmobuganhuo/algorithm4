package com.mengyu.algs4.interview.bytedance.jan13;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2021/1/13 下午4:09
 * TODO
 */
public class Ex1 {
    public int maxOperations(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() <= 0) {
                continue;
            }
            if (k - entry.getKey() == entry.getKey()) {
                count += entry.getValue() / 2;
                map.put(entry.getKey(), entry.getValue() % 2);
                continue;
            }
            if (map.containsKey(k - entry.getKey())) {
                int num = map.get(k - entry.getKey());
                int minNum = Math.min(num, entry.getValue());
                count += minNum;
                map.put(entry.getKey(), entry.getValue() - minNum);
                map.put(k - entry.getKey(), num - minNum);
            }
        }
        return count;
    }
}
