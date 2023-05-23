package com.mengyu.algs4.exercise.leetcode.rank.year2021.jun20;

/**
 * @author yu zhang
 */
public class Ex1 {
    public static void main(String[] args) {
        Ex1 ex1 = new Ex1();
        System.out.println(ex1.largestOddNumber("52"));
    }

    public String largestOddNumber(String num) {
        if (num == null || num.length() == 0) {
            return "";
        }
        int idx = -1;
        for (int i = 0; i < num.length(); i++) {
            char chr = num.charAt(i);
            if ((chr - '0') % 2 == 1){
                idx = i;
            }
        }
        return idx >= 0 ? num.substring(0, idx + 1) : "";
    }
}
