package com.mengyu.algs4.interview.tokensight;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @date 2025/3/2 17:52
 * TODO
 */
public class BlockedQueue {

    public static void main(String[] args) throws InterruptedException {
        BlockedQueue blockedQueue = new BlockedQueue(500, 2);
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                try {
                    blockedQueue.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 992; i++) {
                try {
                    int val = blockedQueue.get();
                    System.out.printf("got %s, size = %s%n", val, blockedQueue.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }

    private final int maxSize;

    private final int minSize;

    private static final Object lock = new Object();

    private final Queue<Integer> que;

    public BlockedQueue(int maxSize, int minSize) {
        if (minSize < 0) {
            throw new IllegalArgumentException("minSize must ge 0!");
        }
        this.maxSize = maxSize;
        this.minSize = minSize;
        que = new LinkedList<>();
    }

    public int get() throws InterruptedException {
        synchronized (lock) {
            // wait/notify 规范写法，等待的线程被唤醒后需要重新判断一遍是否满足条件
            while (que.size() < minSize) {
                lock.wait();
            }
            int val = que.poll();
            if (que.size() < maxSize) {
                lock.notify();
            }
            return val;
        }
    }

    public void put(int val) throws InterruptedException {
        synchronized (lock) {
            while (que.size() > maxSize) {
                lock.wait();
            }
            que.offer(val);
            if (que.size() > minSize) {
                lock.notify();
            }
        }
    }

    public int size() {
        synchronized (lock) {
            return que.size();
        }
    }
}
