package com.mengyu.algs4.exercise.leetcode.queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author yu zhang
 */
public class Ex1670 {

    public static void main(String[] args) {
        Ex1670 ex1670 = new Ex1670();
        ex1670.pushFront(1);
        ex1670.pushBack(2);
        ex1670.pushMiddle(3);
        ex1670.pushMiddle(4);
        System.out.println(ex1670.popFront());
        System.out.println(ex1670.popMiddle());
        System.out.println(ex1670.popMiddle());
        System.out.println(ex1670.popBack());
        System.out.println(ex1670.popFront());
    }

    private final Deque<Integer> header;

    private final Deque<Integer> tailer;

    public Ex1670() {
        header = new LinkedList<>();
        tailer = new LinkedList<>();
    }

    public void pushFront(int val) {
        header.offerFirst(val);
        adjust();
    }

    public void pushMiddle(int val) {
        if (header.size() == tailer.size()) {
            header.offerLast(val);
        } else if (header.size() - tailer.size() >= 1) {
            tailer.offerFirst(header.pollLast());
            header.offerLast(val);
        } else {
            header.offerLast(val);
        }
    }

    public void pushBack(int val) {
        tailer.offerLast(val);
        adjust();
    }

    public int popFront() {
        if (!header.isEmpty()) {
            int val = header.pollFirst();
            adjust();
            return val;
        } else if (!tailer.isEmpty()) {
            int val = tailer.pollFirst();
            adjust();
            return val;
        }
        return -1;
    }

    public int popMiddle() {
        if (header.size() == tailer.size()) {
            return header.isEmpty() ? -1 : header.pollLast();
        } else if (header.size() - tailer.size() == 1) {
            return header.pollLast();
        } else {
            return tailer.isEmpty() ? -1 : tailer.pollFirst();
        }
    }

    public int popBack() {
        if (!tailer.isEmpty()) {
            int val = tailer.pollLast();
            adjust();
            return val;
        } else if (!header.isEmpty()) {
            int val = header.pollLast();
            adjust();
            return val;
        }
        return -1;
    }


    private void adjust() {
        if (header.size() - tailer.size() > 1) {
            tailer.offerFirst(header.pollLast());
        }
        if (tailer.size() - header.size() > 1) {
            header.offerLast(tailer.pollFirst());
        }
    }
}
