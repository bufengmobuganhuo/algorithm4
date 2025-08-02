package com.mengyu.algs4.exercise.leetcode.stack;

import java.util.Stack;

/**
 * @author yu zhang
 */
public class Ex155 {
    private final Stack<Integer> stack;

    private final Stack<Integer> minStack;

    public Ex155() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty()) {
            minStack.push(val);
        } else {
            minStack.push(Math.min(minStack.peek(), val));
        }
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMinIdx() {
        return minStack.peek();
    }
}
