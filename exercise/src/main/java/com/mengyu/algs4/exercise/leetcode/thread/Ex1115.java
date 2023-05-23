package com.mengyu.algs4.exercise.leetcode.thread;

import java.util.concurrent.Semaphore;

/**
 * @author yu zhang
 */
public class Ex1115 {
    public static void main(String[] args) {
        Ex1115 ex1115 = new Ex1115(5);
        new Thread(() -> {
            try {
                ex1115.foo1(() -> System.out.println("foo"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
        new Thread(() -> {

            try {
                ex1115.bar1(() -> System.out.println("bar"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private final Object lock;

    private int flag;

    private Semaphore semaphoreA;

    private Semaphore semaphoreB;

    private int n;

    public Ex1115(int n) {
        this.n = n;
        this.lock = new Object();
        semaphoreA = new Semaphore(1);
        semaphoreB = new Semaphore(0);
    }

    public void foo1(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            semaphoreA.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            semaphoreB.release();
        }
    }

    public void bar1(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n;i++) {
            semaphoreB.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            semaphoreA.release();
        }
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized (lock) {
                while (flag == 1) {
                    lock.wait();
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                flag = 1;
                lock.notifyAll();
            }

        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (lock) {
                while (flag == 0) {
                    lock.wait();
                }
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                flag = 0;
                lock.notifyAll();
            }

        }
    }
}
