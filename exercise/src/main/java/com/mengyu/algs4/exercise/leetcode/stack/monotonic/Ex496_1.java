package com.mengyu.algs4.exercise.leetcode.stack.monotonic;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author yuzhang
 * @date 2020/10/18 9:39 上午
 * TODO
 */
public class Ex496_1 {
    public static void main(String[] args) {
        int[] nums2 = {1, 3, 4, 2};
        int[] nums1 = {4, 1, 2};
        Ex496_1 ex496_1 = new Ex496_1();
        ex496_1.nextGreaterElement(nums1, nums2);
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[nums1.length];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[i] > stack.peek()) {
                map.put(stack.pop(),nums2[i]);
            }
            stack.push(nums2[i]);
        }
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.getOrDefault(nums1[i],-1);
        }
        return res;
    }
}
