package com.mengyu.algs4.exercise.leetcode.math;

/**
 * @author yu zhang
 */
public class Ex2864 {

    public static void main(String[] args) {
        System.out.println(new Ex2864().maximumOddBinaryNumber("010"));
    }

    public String maximumOddBinaryNumber(String s) {
        int oneCnt = 0, zeroCnt = 0;
        for (char chr : s.toCharArray()) {
            if (chr == '0') {
                zeroCnt++;
            } else {
                oneCnt++;
            }
        }
        StringBuilder sb = new StringBuilder();
        while (oneCnt > 1) {
            oneCnt--;
            sb.append(1);
        }
        while (zeroCnt > 0) {
            sb.append(0);
            zeroCnt--;
        }
        if (oneCnt >= 1) {
            sb.append(1);
        }
        return sb.toString();
    }
}
