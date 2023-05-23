package com.mengyu.algs4.exercise.bytedance.mar26th;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yuzhang
 * @date 2021/3/26 上午9:35
 * TODO
 */
public class Ex1 {
    public static void main(String[] args) {
        String s = "abbxxxyyz";
        Ex1 ex1 = new Ex1();
        System.out.println(ex1.largeGroupPositions(s));
    }
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }
        int leftPtr = 0, rightPtr = 1;
        while (leftPtr < s.length()) {
            while (rightPtr < s.length() && s.charAt(leftPtr) == s.charAt(rightPtr)) {
                rightPtr++;
            }
            if (rightPtr - leftPtr > 2) {
                List<Integer> span = Arrays.asList(leftPtr, rightPtr - 1);
                res.add(span);
            }
            leftPtr = rightPtr;
        }
        return res;
    }
}
