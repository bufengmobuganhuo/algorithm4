package com.mengyu.algs4.exercise.leetcode.array;

/**
 * @author yuzhang
 * @date 2020/11/11 2:48 下午
 * TODO
 */
public class Ex849 {
    public static void main(String[] args) {
        int[] seats = {1,0,0,1,0,1};
        Ex849 ex849 = new Ex849();
        System.out.println(ex849.maxDistToClosest(seats));
    }
    public int maxDistToClosest(int[] seats) {
        int leftPtr = 0, rightPtr = 0;
        int maxDis = Integer.MIN_VALUE;
        while (leftPtr < seats.length && seats[leftPtr] != 1) {
            leftPtr++;
        }
        maxDis = leftPtr;
        rightPtr = leftPtr+1;
        while (leftPtr < seats.length) {
            // 找到右边界
            while (rightPtr < seats.length && seats[rightPtr] != 1) {
                rightPtr++;
            }
            // 边界问题
            if (rightPtr == seats.length && seats[rightPtr-1] !=1){
                maxDis = Math.max(maxDis,seats.length - 1 -leftPtr);
                break;
            }
            if ((rightPtr - leftPtr) / 2 > maxDis) {
                maxDis = (rightPtr - leftPtr) / 2;
            }
            leftPtr = rightPtr;
            rightPtr++;
        }
        return maxDis;
    }
}
