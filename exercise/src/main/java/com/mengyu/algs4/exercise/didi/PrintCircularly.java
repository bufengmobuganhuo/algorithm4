package com.mengyu.algs4.exercise.didi;

import edu.princeton.cs.algs4.Count;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author yuzhang
 * @date 2021/4/7 下午8:22
 * 三个线程连续循环打印ABC
 */
public class PrintCircularly {
    public static void main(String[] args) {
        Print print = new Print();
        new Thread(() -> {
            try {
                print.printA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                print.printB();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                print.printC();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    static class Print {
        private int flag = 0;

        private synchronized void printA() throws InterruptedException {
            while (true) {
                if (flag == 0) {
                    System.out.println("A");
                    flag = 1;
                    notifyAll();
                }
                wait();
            }
        }

        private synchronized void printB() throws InterruptedException {
            while (true) {
                if (flag == 1) {
                    System.out.println("B");
                    flag = 2;
                    notifyAll();
                }
                wait();
            }
        }

        private synchronized void printC() throws InterruptedException {
            while (true) {
                if (flag == 2) {
                    System.out.println("C");
                    flag = 0;
                    notifyAll();
                }
                wait();
            }
        }
    }
}
