package com.mengyu.algs4.exercise.offer.queue;

import java.util.LinkedList;

/**
 * @author yu zhang
 */
public class Ex59 {
    // 从头到尾递减的队列
    private LinkedList<Integer> maxQue;
    // 保存所有元素
    private LinkedList<Integer> que;

    public Ex59() {
        maxQue = new LinkedList<>();
        que = new LinkedList<>();
    }

    public int max_value() {
        return maxQue.isEmpty() ? -1 : maxQue.peekFirst();
    }

    public void push_back(int value) {
        // 插入过程中如果发现value > 队尾元素，则直接删除队尾元素，直到满足条件为止
        while (!maxQue.isEmpty() && maxQue.peekLast() < value) {
            maxQue.pollLast();
        }
        maxQue.offerLast(value);
        que.offerLast(value);
    }

    public int pop_front() {
        if (que.isEmpty()) {
            return -1;
        }
        int res = que.pollFirst();
        // 如果发现要出队的元素是最大元素，则需要更新maxQue
        if (res == maxQue.peekFirst()) {
            maxQue.pollFirst();
        }
        return res;
    }
}
