package com.mengyu.algs4.exercise.leetcode.heap;

import java.util.PriorityQueue;

/**
 * @author yu zhang
 */
public class Ex3066 {
    public int minOperations(int[] nums, int k) {
        PriorityQueue<Long> queue = new PriorityQueue<>();
        for (long num : nums) {
            queue.offer(num);
        }
        int size = 0;
        while (queue.size() > 1 && queue.peek() < k) {
            long min1 = queue.poll(), min2 = queue.poll();
            queue.offer(Math.min(min1, min2) * 2 + Math.max(min1, min2));
            size++;
        }
        return size;
    }
}
