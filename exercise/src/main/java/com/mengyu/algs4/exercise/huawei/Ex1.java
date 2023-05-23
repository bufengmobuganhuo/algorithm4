package com.mengyu.algs4.exercise.huawei;

import java.util.Scanner;

/**
 * @author yuzhang
 * @date 2020/7/29 2:19 下午
 * 求两个数的最小公倍数
 */
public class Ex1 {
    private static int getGreatestCommonDivisor(int num1,int num2){
        // 让较大数=num1
        if (num1<num2){
            int tmp=num1;
            num1=num2;
            num2=tmp;
        }
        int mod=num1%num2;
        while (mod!=0){
            num1=num2;
            num2=mod;
            mod=num1%num2;
        }
        return num2;
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNext()){
            int num1=scanner.nextInt();
            int num2=scanner.nextInt();
            System.out.println((num1*num2)/getGreatestCommonDivisor(num1,num2));
        }
    }
}
