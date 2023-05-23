package com.mengyu.algs4.exercise.bytedance.jan22nd;

import java.util.Calendar;
import java.util.Stack;

/**
 * @author yuzhang
 * @date 2021/1/22 上午9:26
 * TODO
 */
public class Ex2 {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        for (char chr : s.toCharArray()) {
            if (chr == '(' || chr=='{'||chr=='[') {
                stack.push(chr);
            } else if (chr == ')') {
                if (stack.isEmpty()||stack.peek()!='('){
                    return false;
                }
                stack.pop();
            } else if (chr == ']') {
                if (stack.isEmpty()||stack.peek()!='['){
                    return false;
                }
                stack.pop();
            } else if (chr == '}') {
                if (stack.isEmpty()||stack.peek()!='{'){
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
