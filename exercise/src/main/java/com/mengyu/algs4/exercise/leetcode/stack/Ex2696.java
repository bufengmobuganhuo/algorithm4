package com.mengyu.algs4.exercise.leetcode.stack;

import java.util.LinkedList;

/**
 * @author yu zhang
 */
public class Ex2696 {
    public int minLength(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        for (char chr : s.toCharArray()) {
            stack.offerLast(chr);
            int size = stack.size();
            if (size >= 2 && ((stack.get(size - 2) == 'A' && stack.get(size - 1) == 'B') || (stack.get(size - 2) == 'C' && stack.get(size - 1)== 'D'))) {
                stack.removeLast();
                stack.removeLast();
            }
        }
        return stack.size();
    }
}
