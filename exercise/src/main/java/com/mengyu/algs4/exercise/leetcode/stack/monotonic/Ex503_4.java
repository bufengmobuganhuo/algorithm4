package com.mengyu.algs4.exercise.leetcode.stack.monotonic;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author yu zhang
 */
public class Ex503_4 {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 2 * n; i++) {
            int idx = i % n;
            while (!stack.isEmpty() && nums[stack.peek()] < nums[idx]) {
                int j = stack.pop();
                ans[j] = nums[idx];
            }
            stack.push(idx);
        }
        return ans;
    }
}
