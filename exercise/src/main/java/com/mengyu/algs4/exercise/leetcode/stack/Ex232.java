package com.mengyu.algs4.exercise.leetcode.stack;

import java.util.Stack;

/**
 * @author yu zhang
 */
public class Ex232 {
    private Stack<Integer> stack;

    private Stack<Integer> reversedStack;

    public Ex232() {
        stack = new Stack<>();
        reversedStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
    }

    public int pop() {
        if (!reversedStack.isEmpty()) {
            return reversedStack.pop();
        }
        while (!stack.isEmpty()) {
            reversedStack.push(stack.pop());
        }
        return reversedStack.pop();
    }

    public int peek() {
        if (!reversedStack.isEmpty()) {
            return reversedStack.peek();
        }
        while (!stack.isEmpty()) {
            reversedStack.push(stack.pop());
        }
        return reversedStack.peek();
    }

    public boolean empty() {
        return stack.isEmpty() && reversedStack.isEmpty();
    }


}
