package com.mengyu.algs4.exercise.bytedance.mar26th;

import java.util.Stack;

/**
 * @author yuzhang
 * @date 2021/3/26 上午9:47
 * TODO
 */
public class Ex2 {
    public static void main(String[] args) {
        String s = "x";
        switch (s){
            case "x":
                System.out.println("true");
                break;
            default:
                System.out.println("false");
        }
    }
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return -1;
        }
        Stack<Integer> operationNum = new Stack<>();
        for (String token : tokens) {
            switch (token){
                case "+":
                    Integer num1 = operationNum.pop();
                    Integer num2 = operationNum.pop();
                    operationNum.push(num1 + num2);
                    break;
                case "-":
                    num1 = operationNum.pop();
                    num2 = operationNum.pop();
                    operationNum.push(num2 - num1);
                    break;
                case "*":
                    num1 = operationNum.pop();
                    num2 = operationNum.pop();
                    operationNum.push(num1 * num2);
                    break;
                case "/":
                    num1 = operationNum.pop();
                    num2 = operationNum.pop();
                    operationNum.push(num2 / num1);
                    break;
                default:
                    operationNum.push(Integer.parseInt(token));
            }
        }
        return operationNum.pop();
    }
}
