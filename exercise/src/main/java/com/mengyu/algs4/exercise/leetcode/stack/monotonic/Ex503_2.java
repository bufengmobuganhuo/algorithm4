package com.mengyu.algs4.exercise.leetcode.stack.monotonic;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author yuzhang
 * @date 2021/3/19 上午10:53
 * TODO
 */
public class Ex503_2 {
    public int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int len = nums.length;
        int[] res = new int[len];
        Arrays.fill(res, -1);
        // 大 -> 小
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 2 * len - 1; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % len]) {
                res[stack.pop()] = nums[i % len];
            }
            stack.push(i % len);
        }
        return res;
    }
}
