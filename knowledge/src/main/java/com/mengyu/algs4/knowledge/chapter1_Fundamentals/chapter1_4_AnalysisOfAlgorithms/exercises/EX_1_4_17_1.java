package com.mengyu.algs4.knowledge.chapter1_Fundamentals.chapter1_4_AnalysisOfAlgorithms.exercises;


import com.mengyu.algs4.utils.ArrayUtil;
import java.util.Arrays;

/**
 * @author zhangyu
 * 2020/5/22 16:17
 * 练习1.4.17：
 */
public class EX_1_4_17_1 {
    public static void main(String[] args) {
        double[] arr= ArrayUtil.createDouble(10,15);
        System.out.println(Arrays.toString(arr));
        solution(arr);
    }
    public static void solution(double[] arr){
        if (arr==null||arr.length==0){
            return;
        }
        // 线性级别
        double min=arr[0];
        double max=arr[arr.length-1];
        for (double v : arr) {
            min = Math.min(v, min);
            max = Math.max(v, max);
        }
        System.out.println(String.format("%.2f %.2f:%.2f",min,max,Math.abs(min-max)));
    }
}
