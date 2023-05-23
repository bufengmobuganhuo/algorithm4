package com.mengyu.algs4.exercise.leetcode.pointer;

/**
 * @author yu zhang
 */
public class Ex1750 {
    public static void main(String[] args) {
        System.out.println(new Ex1750().minimumLength("a"));
    }
    public int minimumLength(String s) {
        int leftPtr = 0, rightPtr = s.length() - 1;
        while (leftPtr < rightPtr) {
            if (s.charAt(leftPtr) != s.charAt(rightPtr)) {
                break;
            }
            char chr = s.charAt(leftPtr);
            while (leftPtr < rightPtr && s.charAt(leftPtr) == chr) {
                leftPtr++;
            }
            if (leftPtr > rightPtr) {
                break;
            }
            while (rightPtr >= leftPtr && s.charAt(rightPtr) == chr) {
                rightPtr--;
            }
        }
        return rightPtr - leftPtr + 1;
    }
}
