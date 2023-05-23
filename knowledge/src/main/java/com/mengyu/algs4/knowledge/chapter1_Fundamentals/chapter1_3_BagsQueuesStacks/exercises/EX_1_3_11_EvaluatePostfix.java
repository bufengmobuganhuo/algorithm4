package com.mengyu.algs4.knowledge.chapter1_Fundamentals.chapter1_3_BagsQueuesStacks.exercises;

import edu.princeton.cs.algs4.StdIn;

import java.util.Stack;
import java.util.regex.Pattern;

/**
 * 将输入的表达式转换成后序表达式，然后计算
 *  从左向右扫描，如果是操作数则入栈，如果是运算符，则取出相应的数计算
 */
public class EX_1_3_11_EvaluatePostfix {
    private static Pattern pattern=Pattern.compile("^[0-9]+");
    public static void main(String[] args) {
        while (!StdIn.isEmpty()){
            String exp=StdIn.readString();
            String res1=Evaluate(EX_1_3_10_InfixToPostfix.transform(exp));
            System.out.println(res1);
        }
    }
    public static String Evaluate(String exp){
        Stack<String> values=new Stack<String>();
        if (exp==null||exp.length() ==0){
            return "";
        }
        String[] params=exp.split(",");
        for (String param:params){
            if (pattern.matcher(param).matches()){
                values.push(param);
            }else{
                if (values.size()>=2){
                    Integer res;
                    Integer value1=Integer.parseInt(values.pop());
                    Integer value2=Integer.parseInt(values.pop());
                    //注意计算顺序
                    switch (param.charAt(0)){
                        case '+':
                            res=value1+value2;
                            break;
                        case '-':
                            res=value2-value1;
                            break;
                        case '*':
                            res=value1*value2;
                            break;
                        default:
                            res=value2/value1;
                            break;
                    }
                    values.push(String.valueOf(res));
                }
            }
        }
        if (!values.isEmpty()){
            return values.pop();
        }
        return "";
    }
}
