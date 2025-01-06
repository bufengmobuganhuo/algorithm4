package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yu zhang
 */
public class Ex3280 {
    public String convertDateToBinary(String date) {
        StringBuilder ans = new StringBuilder();
        Integer year = Integer.parseInt(date.substring(0, 4));
        ans.append(Integer.toBinaryString(year)).append("-");
        Integer month = Integer.parseInt(date.substring(5, 7));
        ans.append(Integer.toBinaryString(month)).append("-");
        Integer day = Integer.parseInt(date.substring(8, 10));
        ans.append(Integer.toBinaryString(day));
        return ans.toString();
    }
}
