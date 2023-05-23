package com.mengyu.algs4.exercise.leetcode.string;

import java.util.Stack;

/**
 * @author yuzhang
 * @date 2020/11/8 1:48 下午
 * TODO
 */
public class Ex227_1 {
    public static void main(String[] args) {
        String exp = " 3+5 / 2 ";
        Ex227_1 ex227_1 = new Ex227_1();
        System.out.println(ex227_1.calculate(exp));
    }
    public int calculate(String exp) {
        if (exp == null || exp.length() == 0) {
            return 0;
        }
        //exp = exp.replaceAll(" ","");
        Stack<Integer> numStack = new Stack<>();
        boolean isNagative = false;
        for (int i = 0; i < exp.length(); i++) {
            char chr = exp.charAt(i);
            if (chr == '-'){
                isNagative = true;
            }else if (chr == '*' || chr == '/') {
                int num1 = numStack.pop();
                String num2 = findNum(exp, i + 1);
                i += num2.length();
                num2 = num2.trim();
                numStack.push(chr == '*' ? num1 * Integer.parseInt(num2) : num1 / Integer.parseInt(num2));
            } else if (Character.isDigit(chr)){
                String num = findNum(exp,i);
                i += num.length()-1;
                num = num.trim();
                numStack.push((isNagative ? -1 * Integer.parseInt(num) : Integer.parseInt(num)));
                isNagative = false;
            }
        }
        while (numStack.size() > 1){
            int num1 = numStack.pop();
            int num2 = numStack.pop();
            numStack.push(num1 + num2);
        }
        return numStack.pop();
    }

    private String findNum(String exp, int idx) {
        StringBuilder stringBuilder = new StringBuilder();
        while (idx < exp.length() && !(exp.charAt(idx) == '+' || exp.charAt(idx) == '-' || exp.charAt(idx) == '*' || exp.charAt(idx) == '/')) {
            stringBuilder.append(exp.charAt(idx++));
        }
        return stringBuilder.toString();
    }

}
