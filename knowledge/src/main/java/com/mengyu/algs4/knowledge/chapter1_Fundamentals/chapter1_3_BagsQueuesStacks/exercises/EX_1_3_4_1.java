package com.mengyu.algs4.knowledge.chapter1_Fundamentals.chapter1_3_BagsQueuesStacks.exercises;

import java.util.Stack;

/**
 * @author zhangyu
 * 2020/5/8 15:16
 * 练习1.3.4：第一次重复
 */
public class EX_1_3_4_1 {
    public static void main(String[] args) {
        System.out.println(isPaired("[()]{}{[()()]()}"));
        System.out.println(isPaired("[(])"));
    }
    public static boolean isPaired(String str){
        Stack<Character> stack=new Stack<>();
        for (char chr:str.toCharArray()){
            switch (chr){
                case'{':
                case '(':
                case '[':
                    stack.push(chr);
                    break;
                case '}':
                    if (!check(stack,'{')){
                        return false;
                    }
                    break;
                case ']':
                    if (!check(stack,'[')){
                        return false;
                    }
                    break;
                default:
                    if (!check(stack,'(')){
                        return false;
                    }
                    break;
            }
        }
        return stack.isEmpty();
    }
    private static boolean check(Stack<Character> stack, char chrLeft){
        if (stack.isEmpty()){
            return false;
        }
        return chrLeft==stack.pop();
    }
}
