package com.mengyu.algs4.exercise.leetcode.queue;

import java.util.PriorityQueue;

/**
 * @author yu zhang
 */
public class Ex2656 {
    public int maximizeSum(int[] nums, int k) {
        PriorityQueue<Integer> que = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int num : nums) {
            que.offer(num);
        }
        int ans = 0;
        for (int i = 0; i < k; i++) {
            int num = que.poll();
            ans += num;
            que.offer(num + 1);
        }
        return ans;
    }
}
