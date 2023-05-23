package com.mengyu.algs4.exercise.leetcode.string;

import java.util.Stack;

/**
 * @author yuzhang
 * @date 2020/8/19 11:48 上午
 * TODO
 */
public class Ex856 {
    public static void main(String[] args) {
        Ex856 ex856=new Ex856();
        System.out.println(ex856.scoreOfParentheses("(()(()))"));
    }
    public int scoreOfParentheses(String S) {
        if (S==null||S.length()==0){
            return 0;
        }
        Stack<String> stack=new Stack<>();
        for (int i = 0; i < S.length(); i++) {
            char chr=S.charAt(i);
            if (chr=='('){
                stack.push(String.valueOf(chr));
                continue;
            }
            int sum=0;
            while (!stack.isEmpty()&&!"(".equals(stack.peek())){
                sum+=Integer.parseInt(stack.pop());
            }
            stack.pop();
            sum*=2;
            if (sum!=0){
                stack.push(String.valueOf(sum));
            }else{
                stack.push("1");
            }
        }
        int sum=0;
        while (!stack.isEmpty()){
            sum+=Integer.parseInt(stack.pop());
        }
        return sum;
    }
}
