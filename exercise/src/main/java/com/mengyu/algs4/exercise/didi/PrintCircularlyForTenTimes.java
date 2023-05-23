package com.mengyu.algs4.exercise.didi;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yuzhang
 * @date 2021/4/7 下午10:07
 * 三个线程循环打印ABC，循环10次
 */
public class PrintCircularlyForTenTimes {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        Print printA = new Print(atomicInteger, 0, 'A');
        Print printB = new Print(atomicInteger, 1, 'B');
        Print printC = new Print(atomicInteger, 2, 'C');

        printA.start();
        printB.start();
        printC.start();
    }

    static class Print extends Thread {
        private final AtomicInteger synObj;
        private int count;
        private final int flag;
        private final char chr;

        public Print(AtomicInteger atomicInteger, int flag, char chr) {
            this.synObj = atomicInteger;
            this.flag = flag;
            this.chr = chr;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (synObj) {
                    if (synObj.get() % 3 == flag) {
                        System.out.println(chr);
                        synObj.addAndGet(1);
                        count++;
                        synObj.notifyAll();
                        if (count == 10) {
                            break;
                        }
                    } else {
                        try {
                            synObj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
