package com.mengyu.algs4.exercise.leetcode.string;

import java.util.Stack;

/**
 * @author yuzhang
 * @date 2020/8/26 8:36 上午
 * TODO
 */
public class Ex227 {
    public static void main(String[] args) {
        String s="1-1-1";
        Ex227 ex227=new Ex227();
        System.out.println(ex227.calculate(s));
    }
    public int calculate(String s) {
        if (s==null||s.length()==0){
            return 0;
        }
        Stack<Integer> optNum=new Stack<>();
        for (int i = 0; i < s.length();) {
            char chr=s.charAt(i);
            // 忽略空格
            if (chr==' '){
                i++;
                continue;
            }
            if (!Character.isDigit(chr)){
                if (chr=='+'){
                    i++;
                    continue;
                }
                StringBuilder num2Str=new StringBuilder();
                // 获取下一个数
                i++;
                while(i<s.length()&&(Character.isDigit(s.charAt(i))||s.charAt(i)==' ')){
                    if (Character.isDigit(s.charAt(i))){
                        num2Str.append(s.charAt(i));
                    }
                    i++;
                }
                Integer num2=Integer.parseInt(num2Str.toString());
                // 如果是*或者/，则计算后放入栈
                if (chr=='*'||chr=='/'){
                    Integer num1=optNum.pop();
                    Integer res=chr=='/'?num1/num2:num1*num2;
                    optNum.push(res);
                    // 如果是-，则将其变为负数
                }else if (chr=='-'){
                    optNum.push(-1*num2);
                }
            }else{
                StringBuilder num2Str=new StringBuilder();
                while(i<s.length()&&Character.isDigit(s.charAt(i))){
                    num2Str.append(s.charAt(i));
                    i++;
                }
                Integer num2=Integer.parseInt(num2Str.toString());
                optNum.push(num2);
            }
        }
        int res=0;
        for (int num : optNum) {
            res+=num;
        }
        return res;
    }

}
