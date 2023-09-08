package com.mengyu.algs4.exercise.leetcode.dp;

/**
 * @author yu zhang
 */
public class Ex1137 {
    public int tribonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n <= 2) {
            return 1;
        }
        int num1 = 0, num2 = 1, num3 = 1;
        int curNum = 1;
        for (int i = 3; i < n + 1; i++) {
            curNum =  num1 + num2 + num3;
            num1 = num2;
            num2 = num3;
            num3 = curNum;
        }
        return curNum;
    }
}
