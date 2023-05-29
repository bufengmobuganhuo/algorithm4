package com.mengyu.algs4.exercise.offer.queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * @author yu zhang
 */
public class Ex59_1 {

    private Deque<Integer> maxQue;

    private Queue<Integer> que;

    public Ex59_1() {
        maxQue = new ArrayDeque<>();
        que = new ArrayDeque<>();
    }

    public int max_value() {
        if (maxQue.isEmpty()) {
            return -1;
        }
        return maxQue.peekFirst();
    }

    public void push_back(int value) {
        que.offer(value);
        while (!maxQue.isEmpty() && maxQue.peekLast() < value) {
            maxQue.pollLast();
        }
        maxQue.offerLast(value);
    }

    public int pop_front() {
        if (que.isEmpty()) {
            return -1;
        }
        int val = que.poll();
        if (maxQue.peekFirst() == val) {
            maxQue.pollFirst();
        }
        return val;
    }
}
