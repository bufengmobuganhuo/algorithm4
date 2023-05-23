package com.mengyu.algs4.exercise.leetcode.thread;

/**
 * @author yuzhang
 * @date 2021/4/8 上午8:58
 * TODO
 */
public class Ex1114 {
    public static void main(String[] args) {
        Foo foo = new Foo();
    }
}

class Foo {
    private int flag = 0;

    public Foo() {

    }

    public synchronized void first(Runnable printFirst) throws InterruptedException {
        while (true) {
            if (flag == 0) {
                printFirst.run();
                flag = 1;
                notifyAll();
                break;
            }
            wait();
        }
    }

    public synchronized void second(Runnable printSecond) throws InterruptedException {
        while (true) {
            if (flag == 1) {
                printSecond.run();
                flag = 2;
                notifyAll();
                break;
            }
            wait();
        }
    }

    public synchronized void third(Runnable printThird) throws InterruptedException {
        while (true) {
            if (flag==2){
                printThird.run();
                notifyAll();
                break;
            }
            wait();
        }
    }
}