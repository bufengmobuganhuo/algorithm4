package com.mengyu.algs4.exercise.leetcode.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yu zhang
 */
public class Ex225 {

    private Queue<Integer> que1;

    private Queue<Integer> que2;

    public Ex225() {
        que1 = new LinkedList<>();
        que2 = new LinkedList<>();
    }

    public void push(int x) {
        que2.offer(x);
        while (!que1.isEmpty()) {
            que2.offer(que1.poll());
        }
        Queue<Integer> tmp = que1;
        que1 = que2;
        que2 = tmp;
    }

    public int pop() {
        return que1.poll();
    }

    public int top() {
        return que1.peek();
    }

    public boolean empty() {
        return que1.isEmpty();
    }
}
