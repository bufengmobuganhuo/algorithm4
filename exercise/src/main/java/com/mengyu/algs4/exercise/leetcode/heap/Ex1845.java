package com.mengyu.algs4.exercise.leetcode.heap;

import java.util.PriorityQueue;

/**
 * @author yu zhang
 */
public class Ex1845 {

    private final PriorityQueue<Integer> que;

    public Ex1845(int n) {
        que = new PriorityQueue<>(n);
        for (int i = 1; i <= n; i++) {
            que.offer(i);
        }
    }

    public int reserve() {
        if (que.isEmpty()) {
            return -1;
        }
        return que.poll();
    }

    public void unreserve(int seatNumber) {
        que.offer(seatNumber);
    }
}
