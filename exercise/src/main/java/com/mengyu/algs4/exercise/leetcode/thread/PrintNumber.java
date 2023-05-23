package com.mengyu.algs4.exercise.leetcode.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yu zhang
 * 两个线程，交替打印数字1-10
 */
public class PrintNumber {
    public static void main(String[] args) {
        //solution1();
        //solution2();
        solution3();
        //solution4();
    }

    private static int i = 0;
    private static int total = 10;

    // 使用synchronized
    private static void solution1() {

        Object lock = new Object();
        Thread t1 = new Thread(() -> {
            while (i < total) {
                synchronized (lock) {
                    // 只打印奇数
                    if (i % 2 == 1) {
                        System.out.println("thread - 1: " + i++);
                        // 唤醒所有等待的线程
                        lock.notifyAll();
                    } else {
                        try {
                            // 不满足条件释放锁
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        Thread t2 = new Thread(() -> {
            while (i < total) {
                synchronized (lock) {
                    // 只打印偶数
                    if (i % 2 == 0) {
                        System.out.println("thread - 2: " + i++);
                        lock.notifyAll();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }

    // 使用条件
    private static void solution2() {
        ReentrantLock lock = new ReentrantLock();
        // t1的条件
        Condition condition1 = lock.newCondition();
        // t2的条件
        Condition condition2 = lock.newCondition();

        Thread t1 = new Thread(() -> {
            while (i < total) {
                lock.lock();
                try {
                    // 打印奇数
                    if (i % 2 == 1) {
                        System.out.println("thread - 1: " + i++);
                        // i变成了偶数，唤醒condition2的条件
                        condition2.signal();
                    } else {
                        condition1.await();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            while (i < total) {
                lock.lock();
                try {
                    if (i % 2 == 0) {
                        System.out.println("thread - 2: " + i++);
                        condition1.signal();
                    } else {
                        condition2.await();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });
        t1.start();
        t2.start();
    }

    // 使用信号量
    private static void solution3() {
        Semaphore semaphore = new Semaphore(1);
        Thread t1 = new Thread(() -> {
            while (i < total) {
                try {
                    semaphore.acquire();
                    // 有可能t1获取资源后，i已经不满足条件
                    if (i % 2 == 1 && i < total) {
                        System.out.println("thread - 1: " + i++);
                    }
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            while (i < total) {
                try {
                    semaphore.acquire();
                    if (i % 2 == 0 && i < total) {
                        System.out.println("thread - 2: " + i++);
                    }
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
    }

    // 这种方法是错的，因为这个没办法保证顺序，只能说保证++正常
    private static void solution4() {
        AtomicInteger count = new AtomicInteger(0);
        new Thread(() -> {
            while (count.get() < total) {
                System.out.println(count.get());
                count.incrementAndGet();
            }
        }).start();
        new Thread(() -> {
            while (count.get() < total) {
                System.out.println(count.get());
                count.incrementAndGet();
            }
        }).start();
    }
}
