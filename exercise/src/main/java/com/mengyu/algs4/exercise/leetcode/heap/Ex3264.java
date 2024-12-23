package com.mengyu.algs4.exercise.leetcode.heap;

import java.util.PriorityQueue;

/**
 * @author yu zhang
 */
public class Ex3264 {
    public int[] getFinalState(int[] nums, int k, int multiplier) {
        PriorityQueue<int[]> que = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });
        for (int i = 0; i < nums.length; i++) {
            que.offer(new int[]{nums[i], i});
        }
        for (int i = 0; i < k; i++) {
            int[] peek = que.poll();
            nums[peek[1]] = peek[0] * multiplier;
            que.offer(new int[]{nums[peek[1]], peek[1]});
        }
        return nums;
    }
}
