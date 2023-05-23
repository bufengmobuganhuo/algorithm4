package com.mengyu.algs4.knowledge.chapter1_Fundamentals.chapter1_3_BagsQueuesStacks;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * 使用两个栈实现四则运算
 * 操作过程：
 *  1.将操作数压入操作数栈
 *  2.将运算符压入运算符栈
 *  3.忽略左括号
 *  4.遇到右括号时，弹出一个运算符，并弹出所需数量的操作数，并将运算结果压入操作数栈
 */
public class Evaluate {
    private final static Pattern PATTERN =Pattern.compile("^\\-?\\d+(\\.\\d+)?$");
    public static void main(String[] args) {
        Stack<Double> values=new Stack<Double>();
        Stack<String> options=new Stack<String>();
        String operators="+-*/";
        //Ctrl+d结束
        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            if (operators.contains(str)) {
                //如果是运算符，压入运算符栈
                options.push(str);
            } else if (")".equals(str)) {
                //如果是"）",则取数计算
                Double value1=values.pop();
                String op=options.pop();
                if ("-".equals(op)){
                    value1=values.pop()-value1;
                }else if("+".equals(op)){
                    value1+=values.pop();
                }else if("/".equals(op)){
                    value1=values.pop()/value1;
                }else{
                    value1*=values.pop();
                }
                values.push(value1);
            } else {
                //如果是数字，压入操作数栈
                values.push(Double.parseDouble(str));
            }
        }
        StdOut.print(values.pop());
    }
    private static boolean isNumeric(String str){
        return PATTERN.matcher(str).matches();
    }
}
