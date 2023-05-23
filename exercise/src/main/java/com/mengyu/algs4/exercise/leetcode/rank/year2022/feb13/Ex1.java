package com.mengyu.algs4.exercise.leetcode.rank.year2022.feb13;

/**
 * @author yuzhang
 * @date 2022/2/13 10:07 上午
 * TODO
 */
public class Ex1 {
    public int countOperations(int num1, int num2) {
        int count = 0;
        while (num1 != 0 && num2 != 0) {
            if (num1 >= num2) {
                num1 -= num2;
            } else {
                num2 -= num1;
            }
            count++;
        }
        return count;
    }
}
