package com.mengyu.algs4.exercise.leetcode.pointer;

/**
 * @author yu zhang
 */
public class Ex917 {
    public static void main(String[] args) {
        System.out.println(new Ex917().reverseOnlyLetters("ab-cd"));
    }
    public String reverseOnlyLetters(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s);
        int leftPtr = 0, rightPtr = s.length() - 1;
        while (leftPtr < rightPtr) {
            while (leftPtr < s.length() && !Character.isLetter(s.charAt(leftPtr))) {
                leftPtr++;
            }
            while (rightPtr >= 0 && !Character.isLetter(s.charAt(rightPtr))) {
                rightPtr--;
            }
            if (leftPtr >= rightPtr) {
                break;
            }
            sb.setCharAt(leftPtr, s.charAt(rightPtr));
            sb.setCharAt(rightPtr, s.charAt(leftPtr));
            leftPtr++;
            rightPtr--;
        }
        return sb.toString();
    }
}
