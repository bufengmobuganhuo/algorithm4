package com.mengyu.algs4.exercise.leetcode.stack.monotonic;

import java.util.Stack;

/**
 * @author yuzhang
 * @date 2020/10/18 9:42 上午
 * TODO
 */
public class Ex503_1 {
    public int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        Stack<Integer> stack = new Stack<>();
        int len = nums.length;
        int[] res = new int[len];
        for (int i = 2 * len - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[i % len] >= stack.peek()) {
                stack.pop();
            }
            res[i % len] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i % len]);
        }
        return res;
    }
}
