package com.mengyu.algs4.knowledge.chapter1_Fundamentals.chapter1_3_BagsQueuesStacks.exercises;

import edu.princeton.cs.algs4.StdIn;

import java.util.Stack;

/**
 * 脱胎于Evaluate(计算四则运算结果)：
 *  1.将操作数压入操作数栈
 *  2.将运算符压入运算符栈
 *  3.遇到右括号时，弹出一个运算符，并弹出所需数量的操作数组成表达式，
 *      为表达式添加左括号，并将运算结果压入操作数栈
 */
public class EX_1_3_9 {
    public static void main(String[] args) {
        Stack<String> options=new Stack<String>();
        Stack<String> values=new Stack<String>();
        while (!StdIn.isEmpty()){
            //输入一个表达式
            String expression=StdIn.readString();
            for (int i=0;i<expression.length();i++){
                StringBuilder str=new StringBuilder();
                //如果遇到的是数字，则取出
                while (Character.isDigit(expression.charAt(i))){
                    str.append(expression.charAt(i));
                    i++;
                }
                //如果是运算符或括号，只取一个
                if (str.toString().length()==0){
                    str.append(expression.charAt(i));
                }
                if (")".equals(str.toString())){
                    String value1=values.pop();
                    String option=options.pop();
                    String exp=String.format("(%s%s%s)",values.pop(),option,value1);
                    values.push(exp);
                }else if("+".equals(str.toString())||
                        "-".equals(str.toString())||
                        "*".equals(str.toString())||
                        "/".equals(str.toString())){
                    options.push(str.toString());
                }else{
                    values.push(str.toString());
                    //上面取数字时会多加一次
                    i--;
                }
            }
            System.out.println(values.pop());
        }
    }
}
