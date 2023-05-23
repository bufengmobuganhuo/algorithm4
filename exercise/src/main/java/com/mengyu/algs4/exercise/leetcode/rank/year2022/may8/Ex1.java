package com.mengyu.algs4.exercise.leetcode.rank.year2022.may8;

/**
 * @author yuzhang
 * @date 2022/5/8 10:11
 * TODO
 */
public class Ex1 {
    public static void main(String[] args) {
        System.out.println(new Ex1().largestGoodInteger("6777133339"));
    }
    public String largestGoodInteger(String num) {
        int leftPtr = 0, rightPtr = 0;
        String ans = "";
        while (rightPtr < num.length()) {
            if (num.charAt(rightPtr) == num.charAt(leftPtr)) {
                rightPtr++;
            } else {
                leftPtr = rightPtr;
            }
            if (rightPtr - leftPtr == 3) {
                String newNum = num.substring(leftPtr, rightPtr);
                if (ans.compareTo(newNum) < 0) {
                    ans = newNum;
                }
                leftPtr = rightPtr;
            }
        }
        return ans;
    }
}
