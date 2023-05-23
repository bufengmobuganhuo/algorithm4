package com.mengyu.algs4.exercise.bytedance.jan12th;

import org.omg.CORBA.INTERNAL;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author yuzhang
 * @date 2021/1/12 上午10:18
 * TODO
 */
public class Ex2 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty()&&nums2[i]>stack.peek()){
                map.put(stack.pop(),nums2[i]);
            }
            stack.push(nums2[i]);
        }
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.getOrDefault(nums1[i],-1);
        }
        return res;
    }
}
