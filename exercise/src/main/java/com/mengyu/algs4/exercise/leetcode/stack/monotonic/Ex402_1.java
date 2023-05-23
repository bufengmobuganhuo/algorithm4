package com.mengyu.algs4.exercise.leetcode.stack.monotonic;

import java.util.LinkedList;

/**
 * @author yuzhang
 * @date 2020/10/20 9:37 上午
 * TODO
 */
public class Ex402_1 {
    public String removeKdigits(String num, int k) {
        LinkedList<Character> stack = new LinkedList<>();
        char[] nums = num.toCharArray();
        for (char digit : nums) {
            while (!stack.isEmpty() && k > 0 && stack.peekLast() > digit) {
                stack.removeLast();
                k--;
            }
            stack.offerLast(digit);
        }
        for (int i = 0; i < k; i++) {
            stack.removeLast();
        }
        StringBuilder res = new StringBuilder();
        // 防止出现0020的情况
        boolean leadingZero = true;
        while (!stack.isEmpty()) {
            char digit = stack.pollFirst();
            if (leadingZero && digit == '0') {
                continue;
            }
            leadingZero = false;
            res.append(digit);
        }
        return res.length() == 0 ? "0" : res.toString();
    }
}
