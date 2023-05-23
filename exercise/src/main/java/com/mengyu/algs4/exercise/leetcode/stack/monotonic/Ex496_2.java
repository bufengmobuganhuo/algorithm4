package com.mengyu.algs4.exercise.leetcode.stack.monotonic;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author yu zhang
 */
public class Ex496_2 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int num : nums2) {
            while (!stack.isEmpty() && stack.peek() < num) {
                int peek = stack.pop();
                map.put(peek, num);
            }
            stack.push(num);
        }
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.getOrDefault(nums1[i], -1);
        }
        return res;
    }
}
