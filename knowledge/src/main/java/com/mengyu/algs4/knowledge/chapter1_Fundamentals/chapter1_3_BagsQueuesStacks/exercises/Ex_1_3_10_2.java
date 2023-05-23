package com.mengyu.algs4.knowledge.chapter1_Fundamentals.chapter1_3_BagsQueuesStacks.exercises;

import java.util.Stack;

/**
 * @author yuzhang
 * @date 2020/9/27 10:42 上午
 * TODO
 */
public class Ex_1_3_10_2 {
    public static void main(String[] args) {
        String exp = "2*3/(2-1)+3*(4-1)";
        infixToPrefix(exp);
        infixToPostfix(exp);
    }

    /**
     * 中序变前序
     *
     * @param exp
     */
    public static void infixToPrefix(String exp) {
        if (exp == null || exp.length() == 0) {
            return;
        }

        StringBuilder res = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (int i = exp.length() - 1; i >= 0; i--) {
            char chr = exp.charAt(i);
            if (stack.isEmpty()) {
                stack.push(chr);
                // 如果是操作数，直接入栈
            } else if (Character.isDigit(chr)) {
                res.append(chr);
                // 如果是")"，直接入栈
            } else if (')' == chr) {
                stack.push(chr);
                // 如果是"("，则要和")"组成一组
            } else if ('(' == chr) {
                while (!stack.isEmpty() && ')' != stack.peek()) {
                    res.append(stack.pop());
                }
                // 如果是操作符，则看其优先级
            } else if (isHigherOrEqual(chr, stack.peek())) {
                stack.push(chr);
            } else {
                while (!stack.isEmpty() && !isHigherOrEqual(chr, stack.peek())) {
                    res.append(stack.pop());
                }
                stack.push(chr);
            }
        }
        while (!stack.isEmpty()) {
            char chr = stack.pop();
            if (')' != chr && '(' != chr) {
                res.append(chr);
            }
        }
        System.out.println(res.reverse());
    }

    private static boolean isHigherOrEqual(char opt1, char opt2) {
        if (opt1 == '*' || opt1 == '/') {
            return true;
        } else if (opt2 == '*' || opt2 == '/') {
            return false;
        }
        return true;
    }

    /**
     * 中序变后序
     *
     * @param exp
     */
    public static void infixToPostfix(String exp) {
        if (exp == null || exp.length() == 0) {
            return;
        }
        StringBuilder res = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (char chr : exp.toCharArray()) {
            if (Character.isDigit(chr)) {
                res.append(chr);
            } else if ('(' == chr) {
                stack.push(chr);
            } else if (')' == chr) {
                char popChr=stack.pop();
                while (!stack.isEmpty()&&'('!=popChr){
                    res.append(popChr);
                    popChr=stack.pop();
                }
            } else {
                while (!stack.isEmpty() && isHigherOrEqual(stack.peek(),chr) && '('!=stack.peek()) {
                    res.append(stack.pop());
                }
                stack.push(chr);
            }
        }
        while (!stack.isEmpty()) {
            char chr = stack.pop();
            if (chr != ')' && chr != '(') {
                res.append(chr);
            }
        }
        System.out.println(res.toString());
    }
}
