package com.mengyu.algs4.exercise.leetcode.stack.monotonic;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author yu zhang
 */
public class Ex503_3 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 3};
        System.out.println(Arrays.toString(new Ex503_3().nextGreaterElements(nums)));
    }

    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[len];
        Arrays.fill(ans, -1);
        for (int i = 0; i < len * 2; i++) {
            int num = nums[i % len];
            while (!stack.isEmpty() && nums[stack.peek()] < num) {
                int idx = stack.pop();
                ans[idx] = num;
            }
            stack.push(i % len);
        }
        return ans;
    }
}
