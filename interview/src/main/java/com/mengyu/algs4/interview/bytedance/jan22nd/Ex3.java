package com.mengyu.algs4.interview.bytedance.jan22nd;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author yuzhang
 * @date 2021/1/22 上午10:01
 * TODO
 */
public class Ex3 {
    public static void main(String[] args) {
        int[] nums = {8,7,3,8,1,4,10,10,10,2};
        Ex3 ex3 = new Ex3();
        System.out.println(ex3.deleteAndEarn(nums));
    }

    public int deleteAndEarn(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int res = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            int earn1 = o1 -
                    (o1 - 1) * map.getOrDefault(o1 - 1, 0) -
                    (o1 + 1) * map.getOrDefault(o1 + 1, 0);
            int earn2 = o2 -
                    (o2 - 1) * map.getOrDefault(o2 - 1, 0) -
                    (o2 + 1) * map.getOrDefault(o2 + 1, 0);
            return earn2 - earn1;
        });
        for (int num : map.keySet()) {
            priorityQueue.offer(num);
        }
        while (!priorityQueue.isEmpty()) {
            int num = priorityQueue.poll();
            res += num;
            int count = map.get(num);
            if (count - 1 == 0) {
                map.remove(num);
            } else {
                map.put(num, count - 1);
                priorityQueue.offer(num);
            }
            map.remove(num - 1);
            priorityQueue.remove(num - 1);
            map.remove(num + 1);
            priorityQueue.remove(num + 1);
        }
        return res;
    }
}
