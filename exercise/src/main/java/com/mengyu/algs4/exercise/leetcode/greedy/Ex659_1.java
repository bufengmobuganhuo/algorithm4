package com.mengyu.algs4.exercise.leetcode.greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author yu zhang
 */
public class Ex659_1 {

    public boolean isPossible2(int[] nums) {
        // <num, num剩余的个数>
        Map<Integer, Integer> countMap = new HashMap<>();
        // <num, 以num结尾的子序列的数量>
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        for (int num : nums) {
            int leftCount = countMap.getOrDefault(num, 0);
            // num没有剩余，则不处理
            if (leftCount <= 0) {
                continue;
            }
            int qty = numMap.getOrDefault(num - 1, 0);
            // 如果存在以num-1结尾的子序列
            if (qty > 0) {
                // 组成以num结尾的子序列
                numMap.put(num, numMap.getOrDefault(num, 0) + 1);
                // 使用了一个num
                countMap.put(num, leftCount - 1);
                // 以num-1结尾的少了一个
                numMap.put(num - 1, qty - 1);
                // 否则创建一个(num, num + 1, num + 2)的序列
            } else {
                int leftCount1 = countMap.getOrDefault(num + 1, 0);
                int leftCount2 = countMap.getOrDefault(num + 2, 0);
                // 如果num, num + 1, num + 2都存在才能组成
                if (leftCount1 > 0 && leftCount2 > 0) {
                    countMap.put(num, leftCount - 1);
                    countMap.put(num + 1, leftCount1 - 1);
                    countMap.put(num + 2, leftCount2 - 1);
                    numMap.put(num + 2, numMap.getOrDefault(num + 2, 0) + 1);
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isPossible(int[] nums) {
        // <x, 以x结尾的子序列的长度>
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num - 1)) {
                PriorityQueue<Integer> que = map.get(num - 1);
                int len = que.poll();
                // 如果以num - 1结尾的子序列都为空，则移除键
                if (que.isEmpty()) {
                    map.remove(num - 1);
                }
                // 以num结尾的子序列长度
                map.computeIfAbsent(num, key -> new PriorityQueue<>()).offer(len + 1);
                // 如果不包含，则创建一个新的子序列
            } else {
                map.computeIfAbsent(num, key -> new PriorityQueue<>()).offer(1);
            }
        }
        // 检查所有的子序列是否都满足长度要求
        for (int num : map.keySet()) {
            if (map.get(num).peek() < 3) {
                return false;
            }
        }
        return true;
    }
}
