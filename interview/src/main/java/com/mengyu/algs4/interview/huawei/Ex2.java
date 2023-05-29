package com.mengyu.algs4.interview.huawei;

import java.util.Scanner;

/**
 * @author yuzhang
 * @date 2020/7/29 2:29 下午
 * 求立方根
 */
public class Ex2 {
    public static double getCubeRoot(double input){
        double min=0;
        double max=input;
        double mid=0;
        while(max-min>0.001){
            mid=(min+max)/2;
            if (mid*mid*mid>input){
                max=mid;
            }else if (mid*mid*mid<input){
                min=mid;
            }else{
                return mid;
            }
        }
        return mid;
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNext()){
            double input=scanner.nextDouble();
            System.out.printf("%.1f\n",getCubeRoot(input));
        }
    }

}
