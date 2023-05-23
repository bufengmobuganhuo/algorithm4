package com.mengyu.algs4.exercise.tonghuashun;

import com.sun.org.apache.xpath.internal.operations.String;

/**
 * @author yuzhang
 * @date 2020/8/5 1:24 下午
 * TODO
 */
public class Ex1 {
    public static void main(String[] args) {
        int[] nums={6,6,2,5};
        Ex1 ex1=new Ex1();
        System.out.println(ex1.solution(nums));
    }

    public int solution(int[] nums){
        int max=Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            max=Math.max(max,combine(nums,i));
        }
        return max;
    }

    private int combine(int[] nums,int excludeIdx){
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            if (i==excludeIdx){
                continue;
            }
            stringBuilder.append(nums[i]);
        }
        return Integer.parseInt(stringBuilder.toString());
    }
}
