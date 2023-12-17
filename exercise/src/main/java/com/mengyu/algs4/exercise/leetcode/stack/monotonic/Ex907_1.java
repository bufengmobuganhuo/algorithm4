package com.mengyu.algs4.exercise.leetcode.stack.monotonic;

import java.util.Stack;

/**
 * @author yu zhang
 */
public class Ex907_1 {

    public static void main(String[] args) {

    }

    private static int mod = (int) (Math.pow(10, 9) + 7);

    public int sumSubarrayMins(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        long ans = 0;
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            while (stack.peek() != -1 && num <= arr[stack.peek()]) {
                int idx = stack.pop();
                ans = (ans + (long) (idx - stack.peek()) * (i - idx) % mod * arr[idx] % mod) % mod;
            }
            stack.push(i);
        }
        while (stack.peek() != -1) {
            int idx = stack.pop();
            ans = (ans + (long) (arr.length - idx) * (idx - stack.peek()) % mod * arr[idx]) % mod;
        }
        return (int) ans;
    }
}
